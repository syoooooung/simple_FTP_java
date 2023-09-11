package com.example.Proj1_2021202057.service;

import com.example.Proj1_2021202057.domain.COMMENT;
import com.example.Proj1_2021202057.domain.Img;
import com.example.Proj1_2021202057.domain.USerm;
import com.example.Proj1_2021202057.repository.URepository;
import java.util.List;

import com.example.Proj1_2021202057.repository.CmmtRepository;
import com.example.Proj1_2021202057.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import java.util.Optional;
@Service
public class CmmtService {
    @Autowired
    private CmmtRepository commentRepository;

    public void saveComment(COMMENT cmt) {
        // 이미지 저장
        commentRepository.save(cmt);
    }

    public List<COMMENT> getcomments() { // 업로드된 사진과 타이틀을 가져오는 로직

        List<COMMENT> list = commentRepository.findAll();  // User Repository에 저장된 모든 사진을 가져옴
        return list;
    }

    public void deleteComment(Long id){
        commentRepository.deleteById(id);
    }
}
