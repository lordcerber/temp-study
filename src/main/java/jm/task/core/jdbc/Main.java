package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService Service = new UserServiceImpl();
        Service.createUsersTable();
        Service.saveUser("One", "Voland", (byte) 99);
        Service.saveUser("Two", "Dracula", (byte) 999);
        Service.saveUser("Three", "Ivanov", (byte) 19);
        for (User user : Service.getAllUsers()) {
            System.out.println(user);
        }
        Service.cleanUsersTable();
        Service.dropUsersTable();
    }
}
