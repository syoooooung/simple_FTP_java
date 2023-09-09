package com.example.Proj1_2021202057.service;

import com.example.Proj1_2021202057.domain.Img;
import com.example.Proj1_2021202057.repository.UserRepository;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ContentDisposition;
import java.nio.charset.StandardCharsets;
import org.springframework.http.HttpStatus;
import org.springframework.core.io.InputStreamResource;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

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

    public ResponseEntity<Resource> downloadPhot(Long id){
        byte[] down = null;
        Optional<Img> photo = photoRepository.findById(id);
        Img article = photo.get();

        try {
            String pathtmp = System.getProperty("user.dir") + "/src/main/resources/static";
            pathtmp = pathtmp + "/" + article.getImagePath();

            String pt = article.getImagePath();
            System.out.println(pathtmp);
            Path path = Paths.get(pathtmp);
            String ContentType = Files.probeContentType(path);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDisposition(ContentDisposition.builder("attachment")
                    .filename(article.getTitle(), StandardCharsets.UTF_8).build());
            headers.add(HttpHeaders.CONTENT_TYPE, ContentType);
            Resource resource = new InputStreamResource(Files.newInputStream(path));

            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Transactional
    public void plus_viewcount(Long id){
        Optional<Img> photo = photoRepository.findById(id);
        Img article = photo.get();
        int precount = article.getViewcount();
        precount=precount+1;
        article.setViewcount(precount);
    }


}
