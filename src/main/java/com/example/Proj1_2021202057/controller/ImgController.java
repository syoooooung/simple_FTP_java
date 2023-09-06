package com.example.Proj1_2021202057.controller;

import com.example.Proj1_2021202057.domain.Img;
import com.example.Proj1_2021202057.repository.UserRepository;
import com.example.Proj1_2021202057.service.ImgService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Optional;


@Controller
public class ImgController {

    @Autowired
    private ImgService photoService;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private UserRepository photoRepository;

    @PostMapping("/Photo")
    public RedirectView handleFileUpload(@ModelAttribute("photo") Img photo,
                                         @RequestParam("photoFile") MultipartFile multipartfile) {
        try {
            if (!multipartfile.isEmpty()) {
                String path = System.getProperty("user.dir") + "/src/main/resources/static";
                File file = new File(path);
                // 해당 위치에 디렉토리가 없다면
                if(!file.exists()){
                    // mkdir 함수로 생성
                    file.mkdirs();
                }
                // 확자자명이 없다면 error

                String contentType = multipartfile.getContentType();
                if (ObjectUtils.isEmpty(contentType)){return new RedirectView("/Error.html");}
                else {
                    if (contentType.matches("image/(jpeg|png|jpg|JPEG|PNG|JPG)")) {
                       // return new RedirectView("/Error.html");
                        photo.setIsImg("true"); //이미지라면 true
                    }
                    else{ //나머지 파일은 false로 저장해줌
                        photo.setIsImg("false");
                    }
                }
                photo.setCreatedTime(LocalDateTime.now(Clock.systemDefaultZone())); // create 시간 저장

                String new_file_name = multipartfile.getOriginalFilename(); // 파일 이름 생성 해주기
                file = new File(path + "/" + new_file_name); // 파일 경로에 저장 해주기
                multipartfile.transferTo(file);
                photo.setImagePath(new_file_name); // image path 해당 경로에 저장 해주기
                System.out.println(file.getAbsolutePath());


                photoService.savePhoto(photo); // image save


            }
        } catch (IOException e) { //exception 예외 처리
            e.printStackTrace();
            // 오류 메시지 표시
            return new RedirectView("/Error.html"); //에러 페이지로 Redirection
        }
        return new RedirectView("/Index.html"); //index 로 redirection
    }


    @PostMapping("/Photo/{id}")
    public RedirectView updatePhoto(@ModelAttribute("photo") Img updatedPhoto,
                                    @PathVariable Long id,
                                    @RequestParam("modiFile") MultipartFile modiFile) throws IOException {
        Optional<Img> photo = photoRepository.findById(id);
        try {
            if (photo.isPresent()) {
                Img photoEntity = photo.get();
                photoEntity.setTitle(updatedPhoto.getTitle());
                photoEntity.setContents(updatedPhoto.getContents());
                // Handle image file upload if provided
                if (!modiFile.isEmpty()) {
                    String path = System.getProperty("user.dir") + "/src/main/resources/static";
                    File file = new File(path);
                    // 해당 위치에 디렉토리가 없다면
                    if (!file.exists()) {
                        // mkdir 함수로 생성
                        file.mkdirs();
                    }
                    // 확자자명이 없다면 error

                    String contentType = modiFile.getContentType();
                    if (ObjectUtils.isEmpty(contentType)) {
                        return new RedirectView("/Error.html");
                    } else {

                    }
                    photoEntity.setCreatedTime(LocalDateTime.now(Clock.systemDefaultZone()));
                    String new_file_name = modiFile.getOriginalFilename(); // 파일 이름 생성
                    file = new File(path + "/" + new_file_name); // 파일 저장
                    modiFile.transferTo(file);
                    photoEntity.setImagePath(new_file_name); // 이미지 경로 저장
                    System.out.println(file.getAbsolutePath());
                    photoService.savePhoto(photoEntity); // 사진 저장
                }
                photoRepository.save(photoEntity);
            }
            return new RedirectView("/Index.html");

        }
        catch (Exception e){
            return new RedirectView("/Error");
        }
    }


}
