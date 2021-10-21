package org.anufriev.spring.rest;

import org.anufriev.spring.rest.configuration.MyConfig;
import org.anufriev.spring.rest.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Communication communication = context.getBean("communication", Communication.class);
        // get all users
        List<User> allUsers = communication.getAllUsers();
        System.out.println(allUsers);
        // add new user
        User user = new User(3L,"James", "Brown", (byte)13);
        String one = communication.saveUser(user);
        // update user
        user.setName("Thomas");
        user.setLastName("Shelby");
        String two = communication.updateUser(user);
        //delete user
        String three = communication.deleteUser(3L);
        System.out.println(one+two+three);
    }
}
