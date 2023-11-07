package service;

import member.Member;
import member.Product;
import repository.MemoryMemberRepository;

public class Main {
    public static void main(String[] args) {
        MemberService memberSerive = new MemberService();
        Member member1 = new Member();
        Member member2 = new Member();

        //회원 name, pwd, grade 지정
        member1.setAll("name1", "password1", "NORMAL");
        member2.setAll("name2", "password2", "VIP");


        //회원가입
        Long id1 =  memberSerive.signUp(member1);
        Long id2 = memberSerive.signUp(member2);
        System.out.println(id1);
        System.out.println(id2);


        




    }
}
