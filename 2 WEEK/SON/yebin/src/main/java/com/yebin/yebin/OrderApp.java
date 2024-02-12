package com.yebin.yebin;

import com.yebin.yebin.dependency.AppConfig;
import com.yebin.yebin.member.Grade;
import com.yebin.yebin.member.Member;
import com.yebin.yebin.member.MemberService;
import com.yebin.yebin.member.MemberServiceImpl;
import com.yebin.yebin.order.Order;
import com.yebin.yebin.order.OrderService;
import com.yebin.yebin.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Member member = new Member("memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(member.getId(),"itemA",10000);

        System.out.println("order = " + order);

    }
}
