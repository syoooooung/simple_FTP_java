package com.example.Proj1_2021202057.service;

import com.example.Proj1_2021202057.domain.Img;
import com.example.Proj1_2021202057.domain.USerm;
import com.example.Proj1_2021202057.repository.URepository;
import java.util.List;

import com.example.Proj1_2021202057.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
        // 이미지 저장
        userRepository.save(us);

        String fileName = "UserData.txt";
        try {

            File file = new File("UserData.txt");
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("it seems to doesnt have a file");
            }

            // 3. Buffer를 사용해서 File에 write할 수 있는 BufferedWriter 생성
            FileWriter fw = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fw);

            // 4. 파일에 쓰기
            writer.write("안녕하세요");

            // 5. BufferedWriter close
            writer.close();


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
}
