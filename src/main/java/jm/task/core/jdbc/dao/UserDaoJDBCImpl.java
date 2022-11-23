package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static jm.task.core.jdbc.util.Util.*;


public class UserDaoJDBCImpl implements UserDao {

    Connection conn;
    //bad way but ok for tutorail
    long index = 100;

    public UserDaoJDBCImpl() {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE Users " +
                "(id INTEGER not NULL, " +
                " name VARCHAR(255), " +
                " lastName VARCHAR(255), " +
                " age BYTE, " +
                " PRIMARY KEY ( id ))";

        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void dropUsersTable() {
        String sql = "DROP TABLE Users ";

        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql =  "INSERT INTO Users VALUES ("+ index++ +", '"+name+"', '"+lastName+"', "+age+")";

        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM Users " +
        "WHERE id = "+id;
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List Users = new ArrayList<User>();

        String sql =  "SELECT id, name, lastName, age FROM Users";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                User user = new User();
                //Display values
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                Users.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Users;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE Users ";
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
