package com.makarov.rest_api_stub.Modules;

import java.sql.*;

public class JDBCApp {

    public JDBCApp() {

    }
    public int updateUserRecord(User user) throws SQLException {
        return updateByUser(user);
    }

    public User fetchUserByLogin(String login) throws SQLException {
        return getUserByLogin(login);
    }
        private static final String url = "jdbc:postgresql://192.168.0.146:5432/mydatabase";
        private static final String loginDB = "myuser";
        private static final String passwordDB = "mypassword";

    private User getUserByLogin(String login) throws SQLException {
        Connection conn=null;
        User user_get = null;
        String query = "SELECT t1.login, t1.password,t1.date,t2.email " +
                "FROM table_one t1 " +
                "JOIN table_two t2 ON t1.login = t2.login " +
                "WHERE t1.login = '" + login + "'"; // Использую обычный Statement
        ResultSet rs = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection(url, loginDB, passwordDB);
            System.out.println("Подключение к базе данных установлено!");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                user_get = new User(rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getTimestamp("date"));
                System.out.println(user_get.toString());
            } else {
                System.out.println("запрос не дал результатов");
            }

        } catch (SQLException e) {
            e.printStackTrace(); }
      finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        return user_get;
    }
    private int updateByUser(User user) throws SQLException {
        int count = 1;
        String query = "INSERT INTO table_one (login, password, date) VALUES (?,?,?); \n INSERT INTO table_two (login, email) VALUES (?,?);";
           try (Connection conn = DriverManager.getConnection(url, loginDB, passwordDB);
            PreparedStatement prstmt = conn.prepareStatement(query)){
            prstmt.setString(1, user.getLogin());
            prstmt.setString(2,user.getPassword());
            prstmt.setTimestamp(3, user.getDate());
            //устанавливаю параметры для 2 запроса
            prstmt.setString(4,user.getLogin());
            prstmt.setString(5,user.getEmail());

            // Добавляю запросы в пакет
            count = count + prstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Количество обновленных строк: " + count);
        return count;
    }

}
