package com.example.Project_1week;

import com.example.Project_1week.item.ItemDAO;
import com.example.Project_1week.item.ItemVO;
import com.example.Project_1week.login.Login;
import com.example.Project_1week.user.UserDAO;
import com.example.Project_1week.user.UserVO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Project1weekApplication {

	public static void main(String[] args) throws SQLException {
		UserVO userVO = new UserVO();
		UserDAO userDAO = new UserDAO();
		ItemVO itemVO = new ItemVO();
		ItemDAO itemDAO = new ItemDAO();
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("1:회원가입 2:로그인 3:상품추가 4:목록확인");
			int input = sc.nextInt();
			switch (input) {
				case 1:
					userDAO.signUp();
					break;
				case 2:
					System.out.println("아이디를 입력하세요: ");
					String name = sc.next();
					System.out.println("비밀번호를 입력하세요: ");
					String password = sc.next();

					userVO.setName(name);
					userVO.setPassword(password);
					Login login = new Login();
					List<UserVO> user = login.getUser(userVO);
					if(!user.isEmpty()) {
						System.out.println("로그인 성공하였습니다.");
					}
					else {
						System.out.println("잘못된 회원정보입니다.");
					}
					break;
				case 3:
					System.out.println("추가할 상품을 입력해주세요: ");
					String itemName = sc.next();
					System.out.println("추가할 상품의 가격을 입력해주세요: ");
					int itemPrice = sc.nextInt();

					itemVO = new ItemVO();
					itemVO.setItemName(itemName);
					itemVO.setItemPrice(itemPrice);
					itemDAO = new ItemDAO();
					itemDAO.setItem(itemVO);
					userVO.setItemList(itemDAO.getItemList());
					break;
				case 4:
					itemDAO.getItemList();
					break;

				default:
					System.out.println("잘못된 입력입니다.");
					break;
			}
		}


	}
}
