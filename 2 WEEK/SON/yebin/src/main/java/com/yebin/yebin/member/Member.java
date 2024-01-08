package com.yebin.yebin.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
    private Long id;
    private String name;
    private Grade grade;

    public Member( String name, Grade grade) {
        this.name = name;
        this.grade = grade;
    }



}
