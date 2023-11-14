package com.myMission.yebin;

import com.myMission.yebin.grade.GradeVO;
import com.myMission.yebin.item.ItemDAO;
import com.myMission.yebin.order.Order;
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
	}
}
