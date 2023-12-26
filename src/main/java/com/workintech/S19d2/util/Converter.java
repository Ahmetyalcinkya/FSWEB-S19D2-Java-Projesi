package com.workintech.S19d2.util;

import com.workintech.S19d2.dto.MemberRequest;
import com.workintech.S19d2.dto.MemberResponse;
import com.workintech.S19d2.entity.Member;

public class Converter {

    public static MemberResponse getMember(MemberRequest memberRequest){
        MemberResponse memberResponse = new MemberResponse(memberRequest.email());
        return memberResponse;
    }

    public static MemberRequest getMemberReq(Member member){
        MemberRequest memberRequest = new MemberRequest(member.getEmail(), member.getPassword());
        return memberRequest;
    }
}
