
package ru.avalon.java.j30.labs;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

//Класс реализующий соединение с базой данных
public class DBConnectionProvider {
    private static final String CONFIG = "properties/connection.properties";
    private final Properties configProperties = new Properties();
            
    public DBConnectionProvider() throws IOException{ 
        ClassLoader classLoader = getClass().getClassLoader();
        try(InputStream inStream = classLoader.getResourceAsStream(CONFIG)){
            configProperties.load(inStream);            
        }
    }    
    /**
     * Возвращает URL, описывающий месторасположение базы данных
     * 
     * @return URL в виде объекта класса {@link String}
     */
    public String getUrl(){
        /*
        return configProperties.getProperty("driver") + "://" +
               configProperties.getProperty("host") + ":" +
               configProperties.getProperty("port") + "/" +
               configProperties.getProperty("name");
        */
        return configProperties.getProperty("database.url");
    }
    /**
     * Возвращает соединение с базой данных Sample
     * 
     * @return объект типа {@link Connection}
     * @throws SQLException 
     */
    public Connection getConnection() throws SQLException{
        String url = configProperties.getProperty("database.url");
        String login = configProperties.getProperty("database.login");
        String pass = configProperties.getProperty("database.password");        
        return DriverManager.getConnection(url, login, pass);
    }
    /**
     * Возвращает параметры соединения
     * 
     * @return Объект класса {@link Properties}, содержащий параметры user и 
     * password
     */
    public Properties getPropDBConnection(){
        return configProperties;
    }
}
