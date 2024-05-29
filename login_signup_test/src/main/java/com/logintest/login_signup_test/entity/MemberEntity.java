package com.logintest.login_signup_test.entity;

import com.logintest.login_signup_test.dto.MemberDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "member_table")
public class MemberEntity {
    // jpa ==> database를 객체처럼 사용 가능

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String memberEmail;

    @Column
    private String memberPassword;

    @Column
    private String memberName;

    @Column
    private String gender;

    @Column
    private double latitude;

    @Column
    private double longitude;

    public static MemberEntity toMemberEntity(MemberDto memberDto){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberDto.getId());
        memberEntity.setMemberEmail(memberDto.getMemberEmail());
        memberEntity.setMemberName(memberDto.getMemberName());
        memberEntity.setMemberPassword(memberDto.getMemberPassword());
        memberEntity.setGender(memberDto.getGender());
        memberEntity.setLatitude(memberDto.getLatitude());
        memberEntity.setLongitude(memberDto.getLongitude());

        return memberEntity;
    }
}
