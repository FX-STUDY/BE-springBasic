package repository;

import member.Member;

public interface MemberRepository {
    public Member save(Member member);

    public Member findById(Long memberId);

}
