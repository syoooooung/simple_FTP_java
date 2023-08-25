package com.example.Proj1_2021202057.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class USerDTO {
    private String id;
    private String userid;
    private String password;
    private String Nick_name;
    private String user_class;
 //   private LocalDate createdTime;
}
