package com.example.Project_1week;

import com.example.Project_1week.user.UserDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class Project1weekApplication {

	public static void main(String[] args) throws SQLException {
		UserDAO userDAO = new UserDAO();
		userDAO.signUp();

	}
}
