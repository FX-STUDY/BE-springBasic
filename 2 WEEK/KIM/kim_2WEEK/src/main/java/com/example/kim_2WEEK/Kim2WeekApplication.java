package com.example.kim_2WEEK;

import com.example.kim_2WEEK.item.ItemDAO;
import com.example.kim_2WEEK.item.ItemVO;
import com.example.kim_2WEEK.user.UserDAO;
import com.example.kim_2WEEK.user.UserVO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
public class Kim2WeekApplication {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("1:회원가입 2:상품주문 3:상품목록 조회(개인) 4:상품목록 조회(전체) 5:회원목록 조회");
			int switchNum = sc.nextInt();
			UserVO userVO = new UserVO();
			UserDAO userDAO = new UserDAO();
			ItemVO itemVO = new ItemVO();
			ItemDAO itemDAO = new ItemDAO();
			switch(switchNum) {
				case 1:
					userDAO.signUp();
					break;
				case 2:
					Map<UserVO, List<ItemVO>> userList = userDAO.getUserList();
					boolean flag = false;
					while(true) {
						System.out.println("상품을 추가할 회원의 이름을 입력해주세요: ");
						String name = sc.next();
						System.out.println("상품의 이름을 입력해주세요: ");
						String itemName = sc.next();
						System.out.println("상품의 가격을 입력해주세요: ");
						int itemPrice = sc.nextInt();

						if(name != "" && itemName != "" && itemPrice != 0) {
							for(UserVO user : userList.keySet()) {
								if(user.getName().equals(name)) {
									itemDAO.addItem(name,itemName,itemPrice);
									flag = true;
									break;
								}
							}
							if(flag) {
								break;
							}
							System.out.println("잘못된 회원 이름입니다.");
						}
					}
					break;
				case 3:

					break;
				case 4:
					itemDAO.printAllItemList();
				case 5:
					userDAO.printUserList();
					break;

				default:
					System.out.println("올바르지 않은 번호입니다.");
					break;
			}
		}
	}

}
