package jm.task.core.jdbc.util;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    Properties props = new Properties();
    String url;
    String username;
    String password;
    Connection connection;

    public Util() throws IOException, SQLException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/database.properties"));
        props.load(reader);
        url = props.getProperty("url");
        username = props.getProperty("username");
        password = props.getProperty("password");
        connection = DriverManager.getConnection(url, username, password);
    }

    public Connection getConnection() { return this.connection; }



}
