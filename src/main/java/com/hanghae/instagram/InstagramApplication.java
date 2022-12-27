package com.hanghae.instagram;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanghae.instagram.member.dto.RequestSignupMemberDto;
import com.hanghae.instagram.member.entity.Member;
import com.hanghae.instagram.member.mapper.MemberMapper;
import com.hanghae.instagram.member.repository.MemberRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;

@EnableJpaAuditing
@SpringBootApplication
public class InstagramApplication {
    public static void main(String[] args) {
        SpringApplication.run(InstagramApplication.class, args);
    }
    @Resource
    private MemberRepository memberRepository;
    @Resource
    private MemberMapper memberMapper;
//    @Bean
//    public ApplicationRunner applicationRunner() {
//        return args -> {
//            InputStream jsonMember = this.getClass().getClassLoader().getResourceAsStream("json/memberData.json");
//            List<RequestSignupMemberDto> requestSignupMemberDtoList = new ObjectMapper().readValue(jsonMember, new TypeReference<>() {
//            });
//            for (int i = 0; i < requestSignupMemberDtoList.size(); i++) {
//                Member member = memberMapper.toEntity(requestSignupMemberDtoList.get(i));
//                memberRepository.save(member);
//            }
//        };
//    }
}
