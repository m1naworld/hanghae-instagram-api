package com.hanghae.instagram.security.userdetails;


import com.hanghae.instagram.member.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private final Member member;
    private final String email;

    public UserDetailsImpl(Member member, String email) {
        this.member = member;
        this.email = email;
    }

    public Member getMember() {
        return member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("member");
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(simpleGrantedAuthority);

        return authorities;
    }


    public String getEmail() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return null;
    }


    public Long getId() {
        return this.member.getId();
    }

    public String getNickname() {
        return this.member.getNickname();
    }

    @Override
    public String getUsername() {
        return this.member.getUsername();
    }


    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
