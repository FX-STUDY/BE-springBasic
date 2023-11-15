package com.yebin.yebin.member;

public interface MemberRepository {
    void save(Member member);

    Member findById(Long memberId);
}
