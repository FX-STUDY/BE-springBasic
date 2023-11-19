package SEOB.SEOB.service;

import SEOB.SEOB.discount.FixDiscountPolicy;
import SEOB.SEOB.discount.RateDiscountPolicy;
import SEOB.SEOB.domain.GradeType;
import SEOB.SEOB.domain.Member;
import SEOB.SEOB.domain.Order;


public class Main {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();

        //회원 name, grade 지정
        Member member1 = new Member("name1", GradeType.VIP);
        Member member2 = new Member("name2", GradeType.NORMAL);
        Member member3 = new Member("name3", GradeType.VIP);


        //회원가입
        Long id1 = memberService.signUp(member1);
        Long id2 = memberService.signUp(member2);
        Long id3 = memberService.signUp(member3);
        System.out.println(id1); //1
        System.out.println(id2); //2
        System.out.println(id3); //3


        //id 검색 -> 앞서 지정한 name 출력
        Member findMember1 = memberService.findByName(id1);
        Member findMember2 = memberService.findByName(id2);
        Member findMember3 = memberService.findByName(id3);
        System.out.println(findMember1.getName()); //name1
        System.out.println(findMember2.getName()); //name2
        System.out.println(findMember3.getName()); //name3


        //상품 주문
        //고객의 고유 id, 상품, 가격
        OrderService orderServiceFix = new OrderServiceImpl(new FixDiscountPolicy());
        Order order1 = orderServiceFix.createOrder(member1.getId(), "itemA", 10000); //id1
        Order order2 = orderServiceFix.createOrder(member2.getId(), "itemB", 20000); //id2

        //등급별 할인 가격 확인
        System.out.println(order1.getDiscountPrice()); // 1000
        System.out.println(order2.getDiscountPrice()); // 0


        //RateDiscountPolicy 주입 확인
        OrderService orderServiceRate = new OrderServiceImpl(new RateDiscountPolicy());
        Order order3 = orderServiceRate.createOrder(member3.getId(), "itemC", 10000); //id


        System.out.println(order3.getDiscountPrice()); // 30
        System.out.println(order3.calculatePrice()); // 7000
    }
}
