package SEOB.SEOB.service;

import SEOB.SEOB.domain.GradeType;
import SEOB.SEOB.domain.Member;
import SEOB.SEOB.domain.Order;


public class Main {
    public static void main(String[] args) {
        MemberServiceImpl memberService = new MemberServiceImpl();

        //회원 name, grade 지정
        Member member1 = new Member("name1", GradeType.VIP);
        Member member2 = new Member("name2", GradeType.NORMAL);


        //회원가입
        Long id1 =  memberService.signUp(member1);
        Long id2 = memberService.signUp(member2);
        System.out.println(id1);
        System.out.println(id2);


        //id 검색 -> 앞서 지정한 name 출력
        Member findMember1 = memberService.findByName(id1);
        Member findMember2 = memberService.findByName(id2);
        System.out.println(findMember1.getName());
        System.out.println(findMember2.getName());


        //상품 주문
        //고객의 고유 id, 상품, 가격
        OrderService orderService = new OrderServiceImpl();
        Order order1 = orderService.createOrder(member1.getId(), "itemA", 10000); //id1
        Order order2 = orderService.createOrder(member2.getId(), "itemB", 20000); //id2


        //등급별 할인 가격 확인
        System.out.println(order1.getDiscountedPrice()); // 1000
        System.out.println(order2.getDiscountedPrice()); // 0



    }
}
