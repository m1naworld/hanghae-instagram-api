package com.hanghae.instagram.posting.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagExctractor {
    public List<String> extractMemberTags(String contents) {
        List<String> result = new ArrayList<>();
        String[] splitted = contents.split(" ");
        for (String s : splitted) {
            if(s.charAt(0) == '@'){
                result.add(s.substring(1));
            }
        }
        return result;
    }

    public List<String> extractHashTags(String contents) {
        List<String> result = new ArrayList<>();
        String[] splitted = contents.split(" ");
        for (String s : splitted) {
            if(s.charAt(0) == '#'){
                result.add(s.substring(1));
            }
        }
        return result;
    }
}
