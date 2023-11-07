package repository;

import member.Member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{


    private static Map<Long, Member> store = new HashMap<>();
    private static long incrementID = 0L;

    public Member save(Member member) {
        member.setId(++incrementID); //호출 시 1씩 증가
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
