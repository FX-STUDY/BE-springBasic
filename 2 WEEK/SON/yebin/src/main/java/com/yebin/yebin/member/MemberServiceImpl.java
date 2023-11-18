package com.yebin.yebin.member;

import com.yebin.yebin.dependency.DependencyInjection;

public class MemberServiceImpl implements MemberService{
    private final DependencyInjection dependencyInjection = new DependencyInjection();
    private final MemberRepository memberRepository = dependencyInjection.memberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
