package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Loading data...");

        roleRepository.save(new Role("APPLICANT"));
        roleRepository.save(new Role("ADMIN"));
        roleRepository.save(new Role("EMPLOYER"));

        Role adminRole = roleRepository.findByRole("APPLICANT");

        Role applicantRole = roleRepository.findByRole("ADMIN");
        Role employerRole = roleRepository.findByRole("EMPLOYER");
    }}

        // Add user roles
//        User user1 = new User("EMPLOYER", "password", "employer", "password", true, "bob");
//        user1.setRoles(Arrays.asList(userRole));
//        userRepository.save(user1);
//
//        User user2 = new User("", "password", "Jane", "Virgin", true, "jane");
//        user2.setRoles(Arrays.asList(userRole));
//        userRepository.save(user2);
//
//        // Add admin roles
//        User user3 = new User("admin@secure.com", "password", "Admin", "User", true, "admin");
//        user3.setRoles(Arrays.asList(adminRole));
//        userRepository.save(user3);
//
//        User user4 = new User("clark@kent.com", "password", "Clark", "Kent", true, "clark");
//        user4.setRoles(Arrays.asList(userRole, adminRole));
//        userRepository.save(user4);
//    }
//}
//        Role role = new Role();
//        role.setRoleName("EMPLOYER");
//        roleRepository.save(role);
//
//        role = new Role();
//        role.setRoleName("APPLICANT");
//        roleRepository.save(role);
//
//        role = new Role();
//        role.setRoleName("ADMIN");
//        roleRepository.save(role);
//
//    }

        //Add test data for users

//        User user = new User();
//        user.setUsername("applicant");
//        user.setPassword("password");
//        user.setFirstName("John Jacob Jingleheimer");
//        user.setLastName("Schmidt");
//        user.setEmail("jjjschmidt@gmail.com");
//        user.setImage("http://www.nurseryrhymes.org/nursery-rhymes-styles/images/john-jacob-jingleheimer-schmidt.jpg");
//        user.addRole(roleRepository.findByRole("APPLICANT"));
//        userRepository.save(user);
//
//    }}