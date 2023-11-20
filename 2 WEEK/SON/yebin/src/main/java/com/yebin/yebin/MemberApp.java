package com.yebin.yebin;

import com.yebin.yebin.dependency.AppConfig;
import com.yebin.yebin.member.Grade;
import com.yebin.yebin.member.Member;
import com.yebin.yebin.member.MemberService;
import com.yebin.yebin.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService =appConfig.memberService();

        Member member = new Member("memberA", Grade.VIP);

        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        System.out.println("member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());


    }
}
