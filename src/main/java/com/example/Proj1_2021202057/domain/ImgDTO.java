package com.example.Proj1_2021202057.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
public class ImgDTO { //UserDTO
    private String id;
    private String title;
    private String contents;
    private LocalDate createdTime;
    private byte[] image;
}
