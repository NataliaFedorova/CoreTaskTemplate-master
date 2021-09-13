package jm.task.core.jdbc;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {

    private static UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        userService.createUsersTable();

        userService.saveUser("Makar", "Makarov", (byte) 25);
        userService.saveUser("Petr", "Petrov", (byte) 21);
        userService.saveUser("Sidor", "Sidorov", (byte) 33);
        userService.saveUser("Semen", "Semenov", (byte) 45);

        userService.removeUserById(1);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

        usedConnectionClose();
    }

    public static void usedConnectionClose () {
        try {
            UserDaoJDBCImpl.getUsedConnection().close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
