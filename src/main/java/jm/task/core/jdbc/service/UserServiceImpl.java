package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class UserServiceImpl implements UserService {
    UserDaoHibernateImpl u1 = new UserDaoHibernateImpl();
    public void createUsersTable() throws SQLException {
        u1.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        u1.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        u1.saveUser(name,lastName,age);
    }

    public void removeUserById(long id) throws SQLException {
        u1.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        return u1.getAllUsers();
    }

    public void cleanUsersTable() throws SQLException {
        u1.cleanUsersTable();
    }
}
