package com.library.app.services;

import com.library.app.repository.LostBooksRepository;
import com.library.app.repository.MemberRepository;

public class MemberService {
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    public boolean checkMember(int id) {
        return this.memberRepository.checkMember(id);
    }
}
