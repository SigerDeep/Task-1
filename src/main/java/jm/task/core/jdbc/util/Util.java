package jm.task.core.jdbc.util;

import java.io.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Util {
    // реализуйте настройку соеденения с БД
    Properties props = new Properties();
    String url;
    String username;
    String password;
    Connection connection;
    static SessionFactory sessionFactory;

    public Util() throws IOException, SQLException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/database.properties"));
        props.load(reader);
        url = props.getProperty("url");
        username = props.getProperty("username");
        password = props.getProperty("password");
        connection = DriverManager.getConnection(url, username, password);
    }

    public Connection getConnection() { return this.connection; }

    public static Session getSession() {
        if (sessionFactory == null){
            Configuration configuration = new Configuration().addAnnotatedClass(User.class);
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory.getCurrentSession();
    }

}
