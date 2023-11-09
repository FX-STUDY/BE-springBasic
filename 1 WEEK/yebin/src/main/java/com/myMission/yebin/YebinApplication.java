package com.myMission.yebin;

import com.myMission.yebin.users.UserDAO;
import com.myMission.yebin.users.UserVO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class YebinApplication {

	public static void main(String[] args) {
		SpringApplication.run(YebinApplication.class, args);

		// 회원가입 test
		UserDAO dao = new UserDAO();
		dao.signUp("yebin","123","123");
		dao.signUp("test2","123","123");
		Map<String,Long> user = dao.getAllUser();
		System.out.println(user);
		//System.out.println(user);
	}
}
