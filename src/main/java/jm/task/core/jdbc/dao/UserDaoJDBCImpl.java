package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.Main;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
            try {
                Util.getCon().execute("CREATE TABLE users ( id int PRIMARY KEY AUTO_INCREMENT, firstName VARCHAR(60), lastName VARCHAR(60), age int)");
                System.out.println("Таблица users успешно создана");
            } catch (SQLException e) {
                System.out.println("Таблица уже существует");
                e.printStackTrace();
            }
    }


    public void dropUsersTable() throws SQLException {
        ResultSet rs = Util.getCon().executeQuery("SHOW TABLES");
        if (rs.next()) {
            String getTableName = rs.getString("Tables_in_new_schema");
            if (getTableName.contains("users")) {
                Util.getCon().execute("DROP TABLE users");
                System.out.println("Таблица users удалена");
            }
        } else {
            System.out.println("Нечего удалять");
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        Util.getCon().execute(
                "INSERT INTO users (name, lastName, age) VALUES ('"+ name +"','"+ lastName +"','"+ age +"')");
        System.out.println("User с именем " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) throws SQLException {
        Util.getCon().execute("DELETE FROM users where id = '"+ id +"'");
    }

    public List<User> getAllUsers() throws SQLException {
        ResultSet rs = Util.getCon().executeQuery("SELECT * FROM users");
        List<User> list = new ArrayList<>();
        Supplier<User> supplier = () -> {
            String name = null;
            String lastName = null;
            byte age = 0;
            try {
                name = rs.getString("firstName");
                lastName = rs.getString("lastName");
                age = (byte) rs.getInt("age");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return new User(name, lastName, age);
        };
        while (rs.next()) {
            list.add(supplier.get());
        }
        return list;
    }

    public void cleanUsersTable() throws SQLException {
        if(getAllUsers().size() == 0){
            System.out.println("Ни одного пользователя не существует");
        } else {
            Util.getCon().execute("DELETE FROM users");
        }
    }
}
