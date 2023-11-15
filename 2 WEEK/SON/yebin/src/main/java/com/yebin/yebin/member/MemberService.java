package com.yebin.yebin.member;

public interface MemberService {
    void join(Member member);

    Member findMember(Long memberId);
}
