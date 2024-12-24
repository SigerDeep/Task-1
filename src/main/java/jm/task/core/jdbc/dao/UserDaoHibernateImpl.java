package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = Util.getSession();

        session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS Users(" +
                "id BIGSERIAL NOT NULL PRIMARY KEY," +
                "name VARCHAR(30) NOT NULL," +
                "lastname VARCHAR(30) NOT NULL," +
                "age INTEGER NOT NULL);").addEntity(User.class).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSession();

        session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS Users").addEntity(User.class).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSession();

        session.beginTransaction();
        session.persist(new User(name, lastName, age));

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSession();

        session.beginTransaction();

        session.delete(session.get(User.class, id));

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSession();
        session.beginTransaction();
        List<User> users = session.createQuery("From User", User.class).list();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM User").executeUpdate();
        session.close();
    }
}
