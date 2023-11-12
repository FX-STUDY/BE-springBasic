package com.example.Project_1week.login;

import com.example.Project_1week.user.UserDAO;
import com.example.Project_1week.user.UserVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Login {

    public List<UserVO> getUser(UserVO vo) throws SQLException {
        List<UserVO> userList = new ArrayList<UserVO>();
        UserDAO userDAO = new UserDAO();
        Connection conn = userDAO.connect();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String GET_USER = " SELECT * FROM fxuser WHERE name=? AND password=? ";

        pstmt = conn.prepareStatement(GET_USER);
        pstmt.setString(1,vo.getName());
        pstmt.setString(2,vo.getPassword());
        rs = pstmt.executeQuery(GET_USER);

        while(rs.next()) {
            UserVO userVO = new UserVO();
            userVO.setName(rs.getString("name"));
            userVO.setPassword(rs.getString("password"));
            userList.add(userVO);
        }

        rs.close();
        pstmt.close();
        conn.close();

        return userList;

    }

}
