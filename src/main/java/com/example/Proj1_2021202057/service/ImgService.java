package com.example.Proj1_2021202057.service;

import com.example.Proj1_2021202057.domain.Img;
import com.example.Proj1_2021202057.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ImgService {
    @Autowired
    private UserRepository photoRepository;

    public void savePhoto(Img photo) {
        // 이미지 저장
        photoRepository.save(photo);
    }

    public List<Img> getUploadedPhotos() { // 업로드된 사진과 타이틀을 가져오는 로직

        Sort sort = Sort.by(Sort.Direction.DESC, "createdTime");

        List<Img> list = photoRepository.findAll(sort);  // User Repository에 저장된 모든 사진을 가져옴
        return list;
    }

    public void deletePhoto(Long id){
        photoRepository.deleteById(id);
    }

    public void downloadPhoto(Long id){}
}
