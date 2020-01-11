package ru.avalon.java.j30.labs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.sql.DriverManager;
//import java.util.Properties;
import static ru.avalon.java.j30.labs.ProductCode.getSelectQuery;

/**
 * Лабораторная работа №3
 * <p>
 * Курс: "DEV-OCPJP. Подготовка к сдаче сертификационных экзаменов серии Oracle Certified Professional Java Programmer"
 * <p>
 * Тема: "JDBC - Java Database Connectivity" 
 *
 * @author Daniel Alpatov <danial.alpatov@gmail.com>
 */
public class Main {
    /**
     * Точка входа в приложение
     * 
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws SQLException, IOException {
        /*
         * TODO #01 Подключите к проекту все библиотеки, необходимые для соединения с СУБД.
         */
        //Объект реализующий соединение с базой данных
        DBConnectionProvider provider = new DBConnectionProvider();
        
        System.out.println(provider.getUrl()+"\n");
        try (Connection connection = provider.getConnection()) {
            try (PreparedStatement prepStatement = getSelectQuery(connection)) {
                try (ResultSet result = prepStatement.executeQuery()) {
                    while(result.next()) {
                        System.out.print("Код товара: "+result.getString("PROD_CODE"));                 
                        System.out.print("\tТип скидки: "+result.getString("DISCOUNT_CODE").charAt(0));
                        System.out.println("\tОписание: "+result.getString("DESCRIPTION"));                         
                    }                            
                }                
            }
            System.out.println("");
            ProductCode code = new ProductCode("CN", 'H', "Connector");  //MO
            code.save(connection);
            //printAllCodes(connection);
            
            //code.setCode("MV");
            //code.save(connection);
            System.out.println("");
            printAllCodes(connection);    
            
        }
    }    
    /**
     * Выводит в кодсоль все коды товаров
     * 
     * @param connection действительное соединение с базой данных
     * @throws SQLException 
     */   
    private static void printAllCodes(Connection connection) throws SQLException, IOException {
        Collection<ProductCode> codes = ProductCode.all(connection);
        //for (ProductCode code : codes) {
        //    System.out.println(code);
        //}        
        codes.forEach((code) -> {
            System.out.println(code);
        }); 
    }
    
    
    
    
    
    
    
    
    /**
     * Возвращает URL, описывающий месторасположение базы данных
     * 
     * @return URL в виде объекта класса {@link String}
     */
    /*
    //Реализован в классе DBConnectionProvider
    private static String getUrl() throws IOException {        
        Properties prop = getProperties();
        return prop.getProperty("url");
    }
    */
    /**
     * Возвращает параметры соединения
     * 
     * @return Объект класса {@link Properties}, содержащий параметры user и 
     * password
     */
    /*
    //Реализован в классе DBConnectionProvider
    private static Properties getProperties() throws FileNotFoundException, IOException {
        
        Properties prop = new Properties();
        try(FileInputStream fis =  new FileInputStream("build/classes/properties/connection.properties")){
            prop.load(fis);        
            return prop;    
        }
    }
    */
    /**
     * Возвращает соединение с базой данных Sample
     * 
     * @return объект типа {@link Connection}
     * @throws SQLException 
     */
    /*
    //Реализован в классе DBConnectionProvider
    private static Connection getConnection() throws SQLException, FileNotFoundException, IOException {
       
        Properties prop = getProperties();
        String url = prop.getProperty("url");
        String login = prop.getProperty("login");
        String pass = prop.getProperty("password");
        return DriverManager.getConnection(url, login, pass);    
       
    }
    */
}
