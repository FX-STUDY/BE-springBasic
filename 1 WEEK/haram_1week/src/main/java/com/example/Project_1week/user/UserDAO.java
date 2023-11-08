package com.example.Project_1week.user;

import java.sql.*;
import java.util.Scanner;

public class UserDAO {
    Scanner sc = new Scanner(System.in);

    //db 연결 Connection 객체 반환
    public Connection connect() throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("db 연결 실패");
        }

        String url = "jdbc:mysql://localhost:3306/jdbc";
        String userName = "jspuser";
        String password = "jsppass";

        Connection conn = DriverManager.getConnection(url,userName,password);

        return conn;


    }
