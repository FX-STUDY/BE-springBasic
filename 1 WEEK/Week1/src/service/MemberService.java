package service;

import member.Member;
import repository.MemberRepository;
import repository.MemoryMemberRepository;

public class MemberService {
    MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();

    public Long signUp(Member member) {
        memoryMemberRepository.save(member);
        return member.getId();
    }

    public Member findByName(Long memberId) {
        Member name = memoryMemberRepository.findById(memberId);
        return name;
    }

}
