package com.example.Proj1_2021202057;

import com.example.Proj1_2021202057.domain.Img;
import com.example.Proj1_2021202057.domain.USerm;
import com.example.Proj1_2021202057.repository.UserRepository;
import com.example.Proj1_2021202057.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ContentDisposition;
import java.nio.charset.StandardCharsets;
import org.springframework.http.HttpStatus;
import org.springframework.core.io.InputStreamResource;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import org.springframework.util.FileCopyUtils;


@Controller
public class HomeController {
    @Autowired
    private ImgService photoService;
    @Autowired
    private UserRepository photoRepository;

    @GetMapping({"/","/Login.html"}) //홈페이지 매핑
    public String home(Model model) { //업로드된 사진 들을 가져와 모델에 추가
  //      List<Img> photos = photoService.getUploadedPhotos();
  //      model.addAttribute("photos", photos);
        return "Login"; //login.html view template 반환
    }

    @GetMapping("/JoinMem.html")
    public String Joinmem(Model model) {
        model.addAttribute("usInfo", new USerm());   //뷰 템플릿에서 usInfo라는 이름으로 USer객체에 접근할 수 있음
        return "JoinMem"; //JoinMem.html template 반환
    }

    @GetMapping("/Index.html") //인덱스 페이지 매핑
    public String Index(Model model) { //업로드된 사진 들을 가져와 모델에 추가
        List<Img> photos = photoService.getUploadedPhotos();
        model.addAttribute("photos", photos);
        return "Index"; //index.html view template 반환
    }

    @GetMapping("/Upload.html")
    public String upload(Model model) { //imgae upload 페이지 매핑
        model.addAttribute("photo", new Img());
        return "Upload"; //upload.html view template 반환
    }

    @GetMapping("/Upload.html/{id}")
    public String modify(Model model, @PathVariable Long id) {
        Img photo = photoRepository.findById(id).get();  //id에 해당하는 정보를 가져와 모델에 추가
        model.addAttribute("photo", photo);
        return "Upload"; //upload.html view tmeplate 반환
    }

    @GetMapping("/ImageView.html/{id}")
    public String viewer(Model model, @PathVariable Long id) {
        try {
            Optional<Img> photo = photoRepository.findById(id);
            if (photo.isPresent()) {
                Img photoEntity = photo.get();
                model.addAttribute("photo", photoEntity); //image 정보 모델에 추가
                return "ImageView";
            }
        }
        catch (Exception e){
            return "Error";  //에러 페이지 반환
        }

        return "redirect:/"; //홈페이지로 리다이렉ㅌㅡ
    }

    @PostMapping("/ImageView.html/{id}/delete")
    public RedirectView deletePhoto(@PathVariable Long id) {
        photoService.deletePhoto(id);  //해당 id의 사진 삭제
        return new RedirectView("/Index.html");
       // return "Index";
    }

    @ResponseBody
    @RequestMapping("/ImageView.html/{id}/download")
    public String downloadPhoto(@PathVariable Long id,HttpServletRequest request, HttpServletResponse response, String name) {
        byte[] down = null;
        Optional<Img> photo = photoRepository.findById(id);
        Img article = photo.get();

        try {
            String pathtmp = System.getProperty("user.dir") + "/src/main/resources/static";
            pathtmp = pathtmp + "/" + article.getImagePath();

            String pt = article.getImagePath();
            System.out.println(pathtmp);
            Path path = Paths.get(pathtmp);

            File file = new File(pathtmp);
            down= FileCopyUtils.copyToByteArray(file);
            String filename = new String(file.getName().getBytes(), "8859_1");

            response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
            response.setContentLength(down.length);


            System.out.println("hey1");
            System.out.println("hey2");
            System.out.println("hey3");
           // ResponseEntity<Resource> respEntity = photoService.downloadPhot(id);
            System.out.println("hey4");
        }
        catch (Exception e){
            return "Error";  //에러 페이지 반환
        }
        return "redirect:/";
    }
}