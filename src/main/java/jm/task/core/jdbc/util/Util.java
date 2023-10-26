package jm.task.core.jdbc.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    //Create Connetion.open + loadProperties from application.properties
    private static final String PASSWORD_KEY = "db.password";
    private static final String USERNAME_KEY = "db.username";
    private static final String URL_KEY = "db.url";

    private Util() {
    }

    public static Connection open(){
        try {
            return DriverManager.getConnection(
                    Util.get(URL_KEY),
                    Util.get(USERNAME_KEY),
                    Util.get(PASSWORD_KEY)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    public static String get(String key)
    {
        return PROPERTIES.getProperty(key);
    }
    //inputStream
    private static void loadProperties() {
        try (var inputStream = Util.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(inputStream);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

}
