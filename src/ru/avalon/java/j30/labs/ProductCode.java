package ru.avalon.java.j30.labs;

//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.util.Properties;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Класс описывает представление о коде товара и отражает соответствующую 
 * таблицу базы данных Sample (таблица PRODUCT_CODE).
 * 
 * @author Daniel Alpatov <danial.alpatov@gmail.com>
 */
public class ProductCode {
    /**
     * Код товара
     */
    private String prodCode;
    /**
     * Код скидки
     */
    private char discountCode;
    /**
     * Описание
     */
    private String description;
    /**
     * Основной конструктор типа {@link ProductCode}
     * 
     * @param code код товара
     * @param discountCode код скидки
     * @param description описание 
     */
    public ProductCode(String code, char discountCode, String description) {
        this.prodCode = code;
        this.discountCode = discountCode;
        this.description = description;
    }
    /**
     * Инициализирует объект значениями из переданного {@link ResultSet}
     * 
     * @param resultSet
     * @param set {@link ResultSet}, полученный в результате запроса, 
     * содержащего все поля таблицы PRODUCT_CODE базы данных Sample.
     * @throws java.sql.SQLException
     */    
    private ProductCode(ResultSet resultSet) throws SQLException{
            prodCode = resultSet.getString("PROD_CODE");
            discountCode = resultSet.getString("DISCOUNT_CODE").charAt(0);
            description = resultSet.getString("DESCRIPTION");
    }
    /**
     * Возвращает код товара
     * 
     * @return Объект типа {@link String}
     */
    public String getCode() {
        return prodCode;
    }
    /**
     * Устанавливает код товара
     * 
     * @param code код товара
     */
    public void setCode(String code) {
        this.prodCode = code;
    }    
    /**
     * Возвращает код скидки
     * 
     * @return Объект типа {@link String}
     */
    public char getDiscountCode() {
        return discountCode;
    }
    /**
     * Устанавливает код скидки
     * 
     * @param discountCode код скидки
     */
    public void setDiscountCode(char discountCode) {
        this.discountCode = discountCode;
    }
    /**
     * Возвращает описание
     * 
     * @return Объект типа {@link String}
     */
    public String getDescription() {
        return description;
    }
    /**
     * Устанавливает описание
     * 
     * @param description описание
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Хеш-функция типа {@link ProductCode}.
     * 
     * @return Значение хеш-кода объекта типа {@link ProductCode}
     */
    @Override
    public int hashCode() {
        //return Objects.hash(prodCode);
        return Objects.hash(prodCode, discountCode, description);
    }
    /**
     * Сравнивает некоторый произвольный объект с текущим объектом типа 
     * {@link ProductCode}
     * 
     * @param obj Объект, скоторым сравнивается текущий объект.
     * @return true, если объект obj тождественен текущему объекту. В обратном 
     * случае - false.
     */
    @Override
    public boolean equals(Object obj) { 
        if (this == obj) return true;
        if (obj instanceof ProductCode) {
            return prodCode.equals(((ProductCode) obj).prodCode)
                    & discountCode == ((ProductCode) obj).discountCode &
                    description.equals(((ProductCode) obj).description);
        }
        return false;
    }    
    /**
     * Возвращает строковое представление кода товара.
     * 
     * @return Объект типа {@link String}
     */
    @Override
    public String toString() {
        return "Код товара: " + this.prodCode + 
               "\tТип скидки: " + this.discountCode + 
               "\tОписание: " + this.description;
    }
    /**
     * Возвращает запрос на выбор всех записей из таблицы PRODUCT_CODE 
     * базы данных Sample
     * 
     * @param connection действительное соединение с базой данных
     * @return Запрос в виде объекта класса {@link PreparedStatement}
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public static PreparedStatement getSelectQuery(Connection connection) throws SQLException, IOException {
        return connection.prepareStatement(new QueryProvider().getQuery("select.product"));
        //return connection.prepareStatement("SELECT * FROM PRODUCT_CODE");
        //return connection.prepareStatement(getPropertiesQuery().getProperty("select"));
    }
    /**
     * Возвращает запрос на добавление записи в таблицу PRODUCT_CODE 
     * базы данных Sample
     * 
     * @param connection действительное соединение с базой данных
     * @return Запрос в виде объекта класса {@link PreparedStatement}
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public static PreparedStatement getInsertQuery(Connection connection) throws SQLException, IOException {
        return connection.prepareStatement("INSERT INTO PRODUCT_CODE "
                + "(PROD_CODE, DISCOUNT_CODE, DESCRIPTION) VALUES (?,?,?)");
        //return connection.prepareStatement(getPropertiesQuery().getProperty("insert"));
    }
    /**
     * Возвращает запрос на обновление значений записи в таблице PRODUCT_CODE 
     * базы данных Sample
     * 
     * @param connection действительное соединение с базой данных
     * @return Запрос в виде объекта класса {@link PreparedStatement}
     * @throws java.sql.SQLException
     */
    public static PreparedStatement getUpdateQuery(Connection connection) throws SQLException, IOException {
        return connection.prepareStatement(
           "UPDATE PRODUCT_CODE SET DISCOUNT_CODE=?, DESCRIPTION = ? WHERE PROD_CODE = ?");
        //return connection.prepareStatement(getPropertiesQuery().getProperty("update"));
    }
    /**
     * Преобразует {@link ResultSet} в коллекцию объектов типа {@link ProductCode}
     * @param set {@link ResultSet}, полученный в результате запроса, содержащего 
     * все поля таблицы PRODUCT_CODE базы данных Sample
     * @return Коллекция объектов типа {@link ProductCode}
     * @throws SQLException 
     */    
    public static Collection<ProductCode> convert(ResultSet resultSet) throws SQLException {
        ProductCode temp;
        Collection<ProductCode> prodCodList = new ArrayList<>();
            while(resultSet.next()) {
                temp = new ProductCode( resultSet.getString("PROD_CODE"),
                                        resultSet.getString("DISCOUNT_CODE").charAt(0),
                                        resultSet.getString("DESCRIPTION"));                                           
                prodCodList.add(temp);
            }                
        return prodCodList;
    }
    /**
     * Сохраняет текущий объект в базе данных. 
     * <p>
     * Если запись ещё не существует, то выполняется запрос типа INSERT.
     * <p>
     * Если запись уже существует в базе данных, то выполняется запрос типа UPDATE.
     * 
     * @param connection действительное соединение с базой данных
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public void save(Connection connection) throws SQLException, IOException {
        PreparedStatement prepStatement = getSelectQuery(connection);
        try(ResultSet result = prepStatement.executeQuery()){
            while(result.next()){
                if (this.getCode().equals(result.getString("PROD_CODE"))){
                    if  (this.equals(new ProductCode(result))){
                        System.out.println("Запись уже существует!!! " + this.toString());
                        return;
                    }
                    prepStatement = getUpdateQuery(connection);
                    prepStatement.setString(1, String.valueOf(getDiscountCode()));
                    prepStatement.setString(2, getDescription());
                    prepStatement.setString(3, getCode());
                    prepStatement.executeUpdate();
                    System.out.println("Update: " + this.toString());
                    return;
                }
            }
            prepStatement = getInsertQuery(connection);
            prepStatement.setString(1, getCode());
            prepStatement.setString(2, String.valueOf(getDiscountCode()));
            prepStatement.setString(3, getDescription());
            prepStatement.executeUpdate();
            System.out.println("Insert: " + this.toString());
        }
        prepStatement.close();
    }
    /**
     * Возвращает все записи таблицы PRODUCT_CODE в виде коллекции объектов
     * типа {@link ProductCode}
     * 
     * @param connection действительное соединение с базой данных
     * @return коллекция объектов типа {@link ProductCode}
     * @throws SQLException 
     * @throws java.io.IOException 
     */
    public static Collection<ProductCode> all(Connection connection) throws SQLException, IOException {
        try (PreparedStatement statement = getSelectQuery(connection)) {
            try (ResultSet result = statement.executeQuery()) {
                return convert(result);
            }
        }
    }
    /**
     * Возвращает параметры SQL запроса
     * @return
     * @throws FileNotFoundException
     * @throws IOException 
     */ 
    /*
    private static Properties getPropertiesQuery() throws FileNotFoundException, IOException {
        Properties prop = new Properties();
        try(FileInputStream fis = new FileInputStream("build/classes/properties/querys.properties")){
            prop.load(fis);
            return prop;
        }
    }
    */
}
