package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users" +
                    "(id MEDIUMINT NOT NULL AUTO_INCREMENT PRIMARY KEY, name TEXT NOT NULL, lastName text NOT NULL,age INT NOT NULL)").executeUpdate();

            t.commit();
        } catch (Exception ex) {
                t.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            t.commit();
        } catch (Exception ex) {
            t.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            t.commit();
        } catch (Exception ex) {
            t.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            Query query = session.createQuery("DELETE User where id= :id");
            query.setLong("id", id);
            query.executeUpdate();
            t.commit();
        } catch (Exception ex) {
            t.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = null;
        Session session = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            Query query = session.createQuery("FROM User");
            userList = query.list();
            t.commit();
        } catch (Exception ex){
            t.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            session.createSQLQuery("DELETE from users").executeUpdate();
            t.commit();
        } catch (Exception ex) {
            t.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }
}
