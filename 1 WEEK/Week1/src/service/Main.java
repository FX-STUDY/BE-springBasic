package service;

import member.GradeType;
import member.Member;
import member.Product;
import repository.MemoryMemberRepository;

public class Main {
    public static void main(String[] args) {
        MemberService memberSerive = new MemberService();

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
        Product product1 = new Product("상품1", 10000L);//상품 이름과 가격
        Product product2 = new Product("상품2", 20000L);
        productService.order(id1, product1);
        productService.order(id1, product1); //key값을 member의 id로, value에 product 주입
        productService.order(id2, product2);

        //등급별 할인된 가격 확인
        System.out.println(product1.getDiscountedPrice());
        System.out.println(product2.getDiscountedPrice());




    }
}
