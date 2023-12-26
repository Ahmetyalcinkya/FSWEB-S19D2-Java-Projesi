package com.workintech.S19d2.service;

import com.workintech.S19d2.entity.Member;
import com.workintech.S19d2.entity.Role;
import com.workintech.S19d2.repository.MemberRepository;
import com.workintech.S19d2.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthenticationService {

    private MemberRepository memberRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(MemberRepository memberRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Member register(String email, String password){

        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        if(optionalMember.isPresent()){
            throw new RuntimeException("EXCEPTIONS WILL BE ADDED!!!");
        }

        String encodedPassword = passwordEncoder.encode(password);
        Optional<Role> optionalRole = roleRepository.findByRole("USER");
        if(optionalRole.isPresent()){
            Role userRole = optionalRole.get();
            Set<Role> roles = new HashSet<>();
            roles.add(userRole);

            Member member = new Member();
            member.setEmail(email);
            member.setPassword(encodedPassword);
            member.setAuthorities(roles);
            return memberRepository.save(member);
        }
        throw new RuntimeException("EXCEPTIONS WILL BE ADDED!!!");
    }
}
