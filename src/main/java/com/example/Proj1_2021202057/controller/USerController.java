package com.example.Proj1_2021202057.controller;

import com.example.Proj1_2021202057.domain.USerm;
import com.example.Proj1_2021202057.repository.URepository;
import com.example.Proj1_2021202057.service.USerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;


import org.springframework.ui.Model;

@Controller
public class USerController {
    @Autowired
    private USerService USerService;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private URepository USerRepository;

    @PostMapping("/userInfo") //회원가입창에서 아이디와 비밀번호를 입력받았을 때
    public RedirectView handleUserInfo(@ModelAttribute("usInfo") USerm usInfo, Model model) {
        try{
            List<USerm> list = USerService.getUserList();
            boolean IsExist = false;

            for (USerm user : list) {
                System.out.println(user.getUserid()+"  "+usInfo.getUserid());
                if (user.getUserid().equals(usInfo.getUserid())) {
                    IsExist = true;
                    break; // 일치하는 유저를 찾았으므로 반복문 종료
                }
            }
            if (IsExist) {
                // 아이디가 이미 존재하는 것을 입력했다면
                model.addAttribute("message", "이미 존재하는 아이디입니다.");
                return new RedirectView("/JoinMem.html");
            }

            USerService.saveUser(usInfo);
        } catch(Exception e){
            e.printStackTrace();
            // 오류 메시지 표시
            return new RedirectView("/Error.html"); //에러 페이지로 Redirection
        }
        return new RedirectView("/Login.html"); //로그인 페이지로 redirection
    }

    @PostMapping("/IsMem")  //로그인창에서 아디이와 비밀번호를 입력받았ㅇ르 때
    public RedirectView checkUserID(@ModelAttribute("usInfo") USerm usInfo){
        try{
            List<USerm> list = USerService.getUserList();

            boolean userFound = false;
            for (USerm user : list) {
                System.out.println(user.getUserid()+"  "+usInfo.getUserid());
                if (user.getUserid().equals(usInfo.getUserid()) &&
                        user.getPassword().equals(usInfo.getPassword())) {
                    userFound = true;
                    break; // 일치하는 유저를 찾았으므로 반복문 종료
                }
            }

            if (userFound) {
                // 로그인 성공 시 처리
                return new RedirectView("/Index.html");
            } else if(!userFound) {
                // 로그인 실패 시 처리
               // return new RedirectView("/Error.html");
            }


        } catch(Exception e){
            return new RedirectView("/Error.html"); //에러 페이지로 Redirection
        }
        return new RedirectView("/Index.html"); //로그인 페이지로 redirection
    }
    /*
    @PostMapping("/JoinMem.html")
    public String Joinmemm(@ModelAttribute("usInfo") USerm userm) {
        // 비밀번호 암호화 작업은 서비스에서 수행
        USerService.saveUser(userm);
        return "redirect:/login"; // 가입 후 로그인 페이지로 리다이렉트
    }
*/
}
