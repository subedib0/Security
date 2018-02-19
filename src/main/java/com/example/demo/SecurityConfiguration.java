package com.example.demo;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.hibernate.criterion.Restrictions.and;



@Configuration
@EnableWebSecurity

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    //WebSecruityConfigurerAdapter: http portocol to close off routes
    @Autowired
    private SSUserDetailsService userDetailsService;
//    These people have access from our database

    @Autowired
    private UserRepository userRepository;

//    Overriding Spring security and passing in Service to look for userrepository database
//    Get results of current user and what thier rights are

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return new SSUserDetailsService(userRepository);
    }

    //    HttpSecurity: tells us which routes people are allowed to acesses includes methods to restict or alllow access
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .authorizerequest user must be authorized to access these requests
                .authorizeRequests()
//                .antmatchers: if you have a route you want to block off
//                .permitall: dont need access pages everyone one can acees this route example:register
                .antMatchers("/","/h2-console/**","/register").permitAll()







//                .access("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
                .antMatchers("/admin").access("hasAuthority('ADMIN')")
                .antMatchers("/index").access("hasAuthority('APPLICANT')")
                .antMatchers("/secure").access("hasAuthority('EMPLOYER')")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(
                        new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").permitAll().permitAll()
                .and()
                .httpBasic();

        http
                .csrf().disable();

        http
                .headers().frameOptions().disable();
    }
//.httpBasic() This means that the user can avoid a login prompt by putting his/her login details in the request.
//    This can be used for testing, but should be removed before the application goes live.

//            configure() This overrides the default configure method, configures users who can access the application. By
//    default, Spring Boot will provide a new random password assigned to the user "user" when it starts up, if you
//do not include this method.
//
//    Once you include this method, you will be able to log in with the users configured here. At this point, the
//    configuration is for a single in-memory user. Multiple users can be configured here, as you wi\\ see when you
//    remove the comments in the additional code. This is also the method in which you can configure how users are granted access to the appliaction if their details are stored in a database.



    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception{
        auth.inMemoryAuthentication().
                withUser("user").password("password").authorities("APPLICANT").
                and().
                withUser("bishnu").password("password").authorities("EMPLOYER")
                .and().
                withUser("admin").password("password").authorities("ADMIN");



//        Database Authentication must come after in memory authentication
        auth
                .userDetailsService(userDetailsServiceBean());

    }




}


/*@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication().
                withUser("dave").password("beastmaster").authorities("ADMIN")
                .and()
                .withUser("User").password("password").authorities("USER");
        ;
    }
} //*/