package SEOB.SEOB.service;

import SEOB.SEOB.domain.Member;
import SEOB.SEOB.repository.MemberRepository;
import SEOB.SEOB.repository.MemoryMemberRepository;

public class MemberServiceImpl {


    private final MemberRepository memberRepository = new MemoryMemberRepository();


    public Long signUp(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

    public Member findByName(Long memberId) {
        Member name = memberRepository.findById(memberId);
        return name;
    }

}
