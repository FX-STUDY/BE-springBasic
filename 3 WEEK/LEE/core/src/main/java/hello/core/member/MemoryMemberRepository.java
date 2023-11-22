package hello.core.member;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryMemberRepository implements MemberRepository {

    private static ConcurrentHashMap<Long,Member> store = new ConcurrentHashMap<>();
    private static long SEQUENCE = 0L;

    @Override
    public void save(Member member) {
        member.setId(++SEQUENCE);
        store.put(member.getId(),member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
