package com.myMission.yebin;

import com.myMission.yebin.users.UserDAO;
import com.myMission.yebin.users.UserVO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class YebinApplication {

	public static void main(String[] args) {
		SpringApplication.run(YebinApplication.class, args);

		// 회원가입 test
		UserDAO dao = new UserDAO();
		dao.signUp("yebin","123","123");
		String user = dao.getUser();
		System.out.println(user);
	}
}
