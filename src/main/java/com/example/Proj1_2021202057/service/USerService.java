package com.example.Proj1_2021202057.service;

import com.example.Proj1_2021202057.domain.Img;
import com.example.Proj1_2021202057.domain.USerm;
import com.example.Proj1_2021202057.repository.URepository;
import java.util.List;

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
public class USerService {
    @Autowired
    private URepository userRepository;

    public void saveUser(USerm us) {
        System.out.println("PLZ print me hueueu");
        String str_Manager = "syoung";
        if(str_Manager.equals(us.getUserid())){
            us.setUser_class("manager_us"); //매니저는 syoung
        }
        else{
            us.setUser_class("new_us"); //매니저를 제외한 아이디는 새로운 유저
        }

        // 유저 저장
        userRepository.save(us);

        String fileName = "UserData.txt";
        try {

            File file = new File("UserData.txt");
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("it seems to doesnt have a file");
            }
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(us.getUserid());
            fileWriter.write(" "+us.getPassword());
            fileWriter.write(" "+us.getNick_name());
            fileWriter.flush();
            fileWriter.close();
            System.out.println("hmm it me success for write ahah"+us.getPassword()+us.getUserid()+us.getNick_name());
        } catch (IOException e) {
            System.out.println("Its my error that write oru " + e.getMessage());
        }
    }

    public List<USerm> getUserList() { // 업로드된 사진과 타이틀을 가져오는 로직
//        Sort sort = Sort.by(Sort.Direction.DESC, "createdTime");
        List<USerm> list = userRepository.findAll();  // User Repository에 저장된 모든 사진을 가져옴
        int sz = list.size();
        System.out.println("List size: " + sz);
        return list;
    }

    public USerm getUSERINFOById(Long id){
        Optional<USerm> us = userRepository.findById(id);  //optional은 만약 해당 id에 해당하는 것이 없을경우 null을 반환
        return us.orElse(null);
    }

    public void deleteUSer(Long id){
        userRepository.deleteById(id);
    }
    @Transactional
    public void upgradeUSer(Long id){
        Optional<USerm> us = userRepository.findById(id);
        USerm uus = us.get();
        uus.setUser_class("normal_us");
    }
}
