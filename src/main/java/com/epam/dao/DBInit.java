package com.epam.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static com.epam.constant.URIConstants.*;

public class DBInit {

    private static String url;
    private static String driver;
    private static String user;
    private static String password;
    private static String script;
    private static String mode;

    public static void loadProperties() throws IOException {

        String path = Thread.currentThread().getContextClassLoader().getResource("db.properties").getPath();

        Properties properties = new Properties();

        properties.load(new FileInputStream(path));

        url = properties.getProperty(URL);
        driver = properties.getProperty(DRIVER);
        user = properties.getProperty(USER);
        password = properties.getProperty(PASSWORD);
        script = properties.getProperty(SCRIPT);
        mode = properties.getProperty(MODE);

    }

    public static Connection getConnection() {

        Connection connection = null;
        try {
            loadProperties();
            Class.forName(driver);
            connection = DriverManager.getConnection(url + mode + script, user, password);
            //System.out.println("DataBase was connected!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
