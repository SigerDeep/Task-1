package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("John", "Doe", (byte) 35);
        userService.saveUser("Sara", "Conor", (byte) 40);
        userService.saveUser("Stephan", "Mayer", (byte) 21);
        userService.saveUser("Stew", "Romano", (byte) 25);

        userService.getAllUsers().forEach(System.out::println);

        userService.removeUserById(1);

        userService.getAllUsers().forEach(System.out::println);

        userService.cleanUsersTable();

        userService.getAllUsers().forEach(System.out::println);

        userService.dropUsersTable();
    }
}
