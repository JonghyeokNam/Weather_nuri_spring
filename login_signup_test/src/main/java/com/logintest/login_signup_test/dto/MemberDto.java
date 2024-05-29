package com.logintest.login_signup_test.dto;

import com.logintest.login_signup_test.entity.MemberEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MemberDto {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String gender;
    private double latitude;
    private double longitude;

    public static MemberDto toMemberDto(MemberEntity memberEntity) {
        MemberDto memberDto = new MemberDto();
        memberDto.setId(memberEntity.getId());
        memberDto.setMemberEmail(memberEntity.getMemberEmail());
        memberDto.setMemberPassword(memberEntity.getMemberPassword());
        memberDto.setMemberName(memberEntity.getMemberName());
        memberDto.setGender(memberEntity.getGender());
        memberDto.setLatitude(memberEntity.getLatitude());
        memberDto.setLongitude(memberEntity.getLongitude());

        return memberDto;
    }

}
