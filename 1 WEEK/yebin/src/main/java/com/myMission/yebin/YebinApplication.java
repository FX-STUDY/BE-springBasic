package com.myMission.yebin;

import com.myMission.yebin.grade.GradeVO;
import com.myMission.yebin.item.ItemDAO;
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
		dao.saveUser("yebin", GradeVO.VIP);
		dao.saveUser("test2",GradeVO.Basic);
		Map<String,Long> user = dao.findAllUser();
		System.out.println(user);

		ItemDAO itemDAO = new ItemDAO();
		itemDAO.saveItem("A",100);
		itemDAO.saveItem("B",1000);
		int price = itemDAO.findItemPriceByItemName("A");
		System.out.println(price);
		//System.out.println(user);
	}
}
