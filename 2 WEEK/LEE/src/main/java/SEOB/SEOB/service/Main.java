package SEOB.SEOB.service;

import SEOB.SEOB.domain.GradeType;
import SEOB.SEOB.domain.Member;
import SEOB.SEOB.domain.Order;


public class Main {
    public static void main(String[] args) {
        MemberServiceImpl memberSerive = new MemberServiceImpl();

        //회원 name, grade 지정
        Member member1 = new Member("name1", GradeType.VIP);
        Member member2 = new Member("name2", GradeType.NORMAL);


        //회원가입
        Long id1 =  memberSerive.signUp(member1);
        Long id2 = memberSerive.signUp(member2);
        System.out.println(id1);
        System.out.println(id2);


        //id 검색 -> 앞서 지정한 name 출력
        Member findMember1 = memberSerive.findByName(id1);
        Member findMember2 = memberSerive.findByName(id2);
        System.out.println(findMember1.getName());
        System.out.println(findMember2.getName());


        //상품 주문
        //고객의 고유 id, 상품
        ProductService productService = new ProductService();
        Order order1 = new Order("상품1", 10000L);//상품 이름과 가격
        Order order2 = new Order("상품2", 20000L);
        productService.order(id1, order1);
        productService.order(id1, order1); //key값을 member의 id로, value에 product 주입
        productService.order(id2, order2);

        //등급별 할인된 가격 확인
        System.out.println(order1.getDiscountedPrice());
        System.out.println(order2.getDiscountedPrice());




    }
}
