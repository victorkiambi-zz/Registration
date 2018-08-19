package youth;

import java.sql.*;

public class MysqlConnection {

    public static Connection connect() {

        try {

            //TODO create database connection from user input
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/youth", "root", "");





            return conn;


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }
}

