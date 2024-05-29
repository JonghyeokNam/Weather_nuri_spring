package com.logintest.login_signup_test.service;


import com.logintest.login_signup_test.dto.MemberDto;
import com.logintest.login_signup_test.entity.MemberEntity;
import com.logintest.login_signup_test.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void save(MemberDto memberDto) {
        // repository의 save 메서드 호출
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDto);
        memberRepository.save(memberEntity);
        //Repository의 save메서드 호출 (조건: entity객체를 넘겨줘야 함)
    }

    public MemberDto login(MemberDto memberDto) {
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDto.getMemberEmail());
        if (byMemberEmail.isPresent()) {
            // 조회 결과가 있다
            MemberEntity memberEntity = byMemberEmail.get(); // Optional에서 꺼냄
            if (memberEntity.getMemberPassword().equals(memberDto.getMemberPassword())) {
                // 비밀번호 일치
                // entity -> dto 변환 후 리턴
                MemberDto dto = MemberDto.toMemberDto(memberEntity);
                return dto;
            }
            else {
                // 비밀번호 불일치
                return null;
            }
        }
        else {
            // 조회 결과가 없음
            return null;
        }
    }
}
