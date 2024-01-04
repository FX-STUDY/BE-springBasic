package SEOB.SEOB.repository;


import SEOB.SEOB.domain.Member;

public interface MemberRepository {
    public Member save(Member member);

    public Member findById(Long memberId);

}
