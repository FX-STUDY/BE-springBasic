package SEOB.SEOB.repository;


import SEOB.SEOB.domain.Member;

import java.util.concurrent.ConcurrentHashMap;

public class MemoryMemberRepository implements MemberRepository{


    private static ConcurrentHashMap<Long, Member> store = new ConcurrentHashMap<>();
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
