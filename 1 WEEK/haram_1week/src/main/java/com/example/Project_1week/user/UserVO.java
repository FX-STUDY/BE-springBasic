package com.example.Project_1week.user;

import com.example.Project_1week.item.ItemVO;

import java.util.List;

public class UserVO {
    private int seq;
    private String name;
    private String id;
    private String password;
    private String grade;
    private List<ItemVO> itemList;

    public List<ItemVO> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemVO> itemList) {
        this.itemList = itemList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
