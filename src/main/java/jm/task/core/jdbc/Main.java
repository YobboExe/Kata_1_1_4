package jm.task.core.jdbc;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Steve", "Minecraft", (byte) 12);
        userService.saveUser("Sus", "Amongasovich", (byte) 126);
        userService.saveUser("Alexey", "Alexov", (byte) 27);
        userService.saveUser("Newbie", "Human", (byte) 0);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
//        userService.dropUsersTable();

    }
}
