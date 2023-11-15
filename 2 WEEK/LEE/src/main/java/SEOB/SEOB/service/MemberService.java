package SEOB.SEOB.service;

import SEOB.SEOB.domain.Member;

public interface MemberService {
    public Long signUp(Member member);
    public Member findByName(Long memberId);
}
