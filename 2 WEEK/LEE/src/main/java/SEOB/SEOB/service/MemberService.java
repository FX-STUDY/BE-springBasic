package SEOB.SEOB.service;

import SEOB.SEOB.domain.Member;
import SEOB.SEOB.repository.MemoryMemberRepository;

public class MemberService {


    private final MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();


    public Long signUp(Member member) {
        memoryMemberRepository.save(member);
        return member.getId();
    }

    public Member findByName(Long memberId) {
        Member name = memoryMemberRepository.findById(memberId);
        return name;
    }

}
