package com.myMission.yebin;

import com.myMission.yebin.grade.GradeVO;
import com.myMission.yebin.item.ItemDAO;
import com.myMission.yebin.order.Order;
import com.myMission.yebin.users.UserDAO;
import com.myMission.yebin.users.UserVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class YebinApplicationTests {

	@Test
	void saveUserTest(){
		UserDAO userDAO = new UserDAO();
		userDAO.saveUser("yebin", GradeVO.VIP);
		userDAO.saveUser("test", GradeVO.Basic);
	}

	@Test
	void findAllUser(){
		UserDAO userDAO = new UserDAO();
		userDAO.saveUser("yebin",GradeVO.Basic);
		userDAO.saveUser("test",GradeVO.VIP);

		Map<String, Long> userList = userDAO.findAllUser();
	}

	@Test
	void saveItemTest(){
		ItemDAO itemDAO = new ItemDAO();
		itemDAO.saveItem("itemA",1000);
		itemDAO.saveItem("itemB",2000);
	}

	@Test
	void findItemPriceTest(){
		ItemDAO itemDAO = new ItemDAO();
		itemDAO.saveItem("itmeA",1000);
		Integer itemPrice = itemDAO.findItemPriceByItemName("itemA");
	}

	@Test
	void OrderTest() {
		UserDAO userDAO = new UserDAO();
		ItemDAO itemDAO = new ItemDAO();

		userDAO.saveUser("yebin",GradeVO.VIP);
		itemDAO.saveItem("itemA",2000);

		Order order = new Order();
		// main에서 테스트했을땐 할인된 가격으로 1800원이 떴는데 여기서는 왜 2000원 그대로 뜨는지 모르겠어요,,
		order.ItemOrder("yebin","itemA");
	}

}
