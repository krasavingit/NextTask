package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

import jm.task.core.jdbc.util.*;
import org.hibernate.Query;
import org.hibernate.Session;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        Session session = null;
        try{
            session = Util.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.createSQLQuery("CREATE TABLE user ( id int PRIMARY KEY AUTO_INCREMENT, firstName VARCHAR(60), lastName VARCHAR(60), age int)").executeUpdate();
            System.out.println("Таблица создана");
            session.getTransaction().commit();
        }finally {
            if(session!= null)
                session.close();
            if(Util.getSessionFactory().openSession() != null){
                Util.getSessionFactory().openSession().close();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = null;
        try{
            session = Util.getSessionFactory().openSession();
            session.getTransaction().begin();
            if(session.createSQLQuery("SHOW TABLES").list().isEmpty()){
                System.out.println("Нечего удалять");
            } else {
                session.createSQLQuery("DROP TABLE user").executeUpdate();
                session.getTransaction().commit();

            }
        }finally {
            if(session!= null){
                session.close();
            }
            if(Util.getSessionFactory().openSession() != null){
                Util.getSessionFactory().openSession().close();
            }
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age){
        Session session = null;
        try{
            session = Util.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.createSQLQuery("INSERT INTO user (firstName, lastName, age) VALUES ('"+ name +"','"+ lastName +"','"+ age +"')").executeUpdate();
            session.getTransaction().commit();
        }finally {
            if(session!= null){
                session.close();
            }
            if(Util.getSessionFactory().openSession() != null){
                Util.getSessionFactory().openSession().close();
            }
        }

    }

    @Override
    public void removeUserById(long id) {
        Session session = null;
        try{
            session = Util.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.createSQLQuery("DELETE FROM user WHERE id="+ id +"").executeUpdate();
            session.getTransaction().commit();
        }finally {
            if(session!= null){
                session.close();
            }
            if(Util.getSessionFactory().openSession() != null){
                Util.getSessionFactory().openSession().close();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = null;
        List<User> users = new ArrayList<User>();
        try{
            session = Util.getSessionFactory().openSession();
            session.getTransaction().begin();
            List<String> names = session.createSQLQuery("Select firstName from user").list();
            List<String> lastNames = session.createSQLQuery("Select lastName from user").list();
            List<Integer> ages = session.createSQLQuery("Select age from user").list();
            for (int i = 0; i < names.size() ; i++) {
                users.add(new User(names.get(i),lastNames.get(i),(byte)(int)ages.get(i)));
            }
            session.getTransaction().commit();
        }finally {
            if(session!= null){
                session.close();
            }
            if(Util.getSessionFactory().openSession() != null){
                Util.getSessionFactory().openSession().close();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = null;
        try{
            session = Util.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.createSQLQuery("DELETE FROM user").executeUpdate();
            session.getTransaction().commit();
        }finally {
            if(session!= null){
                session.close();
            }
            if(Util.getSessionFactory().openSession() != null){
                Util.getSessionFactory().openSession().close();
            }
        }
    }
}
