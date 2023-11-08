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

        String url = "jdbc:mysql://localhost:3306/jspdb";
        String userName = "jspuser";
        String password = "jsppass";

        Connection conn = DriverManager.getConnection(url,userName,password);

        return conn;


    }
    public void signUp() throws SQLException {
        UserVO userVO = new UserVO();
        String password = null;
        String name = null;
        String grade = null;
        while(true) {
            System.out.println("비밀번호를 입력하세요: ");
            password = sc.next();
            if(password == null) {
                System.out.println("비밀번호는 빈칸일 수 없습니다.");
            }
            else {
                break;
            }
        }
        while(true) {
            System.out.println("이름을 입력하세요: ");
            name = sc.next();
            if(name == null) {
                System.out.println("이름은 빈칸일 수 없습니다.");
            }
            else {
                break;
            }
        }
        userVO.setPassword(password);
        userVO.setGrade("normal");
        userVO.setName(name);

        try {
            connect();
        }
        catch (SQLException e) {
            System.out.println("실행 오류");
        }
        String sql = " INSERT INTO fxuser(password, name, grade) "
                + " VALUES(?, ?, ?) ";


        Connection conn = connect();
        PreparedStatement preparedstatement = null;
        try {
            preparedstatement = conn.prepareStatement(sql);

            preparedstatement.setString(1,userVO.getPassword());
            preparedstatement.setString(2,userVO.getName());
            preparedstatement.setString(3,userVO.getGrade());
            preparedstatement.executeUpdate();

            System.out.println("회원 생성 완료");
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("데이터 입력 도중 연결 오류");
        }
        finally {
            try{
                if(preparedstatement != null) {
                    preparedstatement.close();
                }
                if(conn != null) {
                    conn.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
