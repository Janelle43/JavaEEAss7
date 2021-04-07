package iitu.kz.product.db;

import iitu.kz.product.entity.Cookie;
import iitu.kz.product.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DbManager {
    private static Connection connection;

    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/zhanelya_db?serverTimezone=UTC&useUnicode=true", "root", "");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static boolean addUser(User user){
        int rows= 0;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users(login , password , full_name , picture) " +
                            "VALUES (? , ? , ? , ?)"
            );
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullName());
            statement.setString(4, user.getPicture());
            rows = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }

    public static User getUserByLogin(String login){

        User user = null;

        try{

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE login = ?");
            statement.setString(1, login);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getString("picture")
                );
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return user;
    }

    public static boolean addCookie(Cookie cookie){
        int rows = 0;
        try{

            PreparedStatement statement = connection.prepareStatement("INSERT INTO cookies (id, name, author_id, rating, recipe, created_at, price) " +
                    "VALUES (NULL, ?, ?, ?, ?, NOW(), ?)");

            statement.setString(1, cookie.getName());
            statement.setLong(2, cookie.getAuthor().getId());
            statement.setInt(3, cookie.getRating());
            statement.setString(4, cookie.getRecipe());
            statement.setInt(5, cookie.getPrice());

            rows = statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return rows>0;
    }

    public static ArrayList<Cookie> getAllCookies(){

        ArrayList<Cookie> cookies = new ArrayList<>();

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT c.id, c.name, c.recipe, c.created_at, c.price, c.rating, c.author_id, u.full_name, u.picture " +
                    "FROM cookies c " +
                    "INNER JOIN users u ON u.id = c.author_id " +
                    "ORDER BY c.price ASC");

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){

                cookies.add(
                        new Cookie(
                                resultSet.getLong("id"),
                                resultSet.getString("name"),
                                new User(
                                        resultSet.getLong("author_id"),
                                        null, null,
                                        resultSet.getString("full_name"),
                                        resultSet.getString("picture")
                                ),
                                resultSet.getString("recipe"),
                                resultSet.getInt("rating"),
                                resultSet.getInt("price"),
                                resultSet.getDate("created_at")
                        )
                );
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return cookies;
    }

    public static Cookie getCookie(Long id){

        Cookie cookie = null;

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT c.id, c.name, c.recipe, c.created_at, c.price, c.rating, c.author_id, u.full_name, u.picture " +
                    "FROM cookies c " +
                    "INNER JOIN users u ON u.id = c.author_id " +
                    "WHERE c.id = ? ");

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                cookie = new Cookie(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        new User(
                                resultSet.getLong("author_id"),
                                null, null,
                                resultSet.getString("full_name"),
                                resultSet.getString("picture")
                        ),
                        resultSet.getString("recipe"),
                        resultSet.getInt("rating"),
                        resultSet.getInt("price"),
                        resultSet.getDate("created_at")
                );
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return cookie;
    }

    public static boolean updateUserPicture(User user){
        int rows = 0;
        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE users SET picture = ? WHERE id = ?");

            statement.setString(1, user.getPicture());
            statement.setLong(2, user.getId());

            rows = statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return rows>0;
    }

    public static boolean updateUserProfile(User user){
        int rows = 0;
        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE users SET full_name = ? WHERE id = ?");

            statement.setString(1, user.getFullName());
            statement.setLong(2, user.getId());

            rows = statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return rows>0;
    }

    public static boolean updateUserPassword(User user){
        int rows = 0;
        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE users SET password = ? WHERE id = ?");

            statement.setString(1, user.getPassword());
            statement.setLong(2, user.getId());

            rows = statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return rows>0;
    }
}
