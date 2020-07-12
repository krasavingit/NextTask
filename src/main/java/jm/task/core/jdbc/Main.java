package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;

import java.sql.*;
import java.util.Iterator;


public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        UserDaoHibernateImpl u2 = new UserDaoHibernateImpl();
        u2.dropUsersTable();
        u2.createUsersTable();
        u2.saveUser("Vasya","Vasiev",(byte)23);
        u2.saveUser("Petr","Petrov",(byte)33);
        u2.saveUser("Ivan","Ivanov",(byte)55);
        u2.saveUser("test","testov",(byte)4);
        u2.removeUserById(1);
        for (User user : u2.getAllUsers()) {
            System.out.println(user);
        }
        //u2.removeUserById(1);
        //u2.cleanUsersTable();


    }
}
