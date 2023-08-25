package com.example.Proj1_2021202057.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EntityListeners({AuditingEntityListener.class}) // Entity에서 특정 이벤트 수행을 위한 어노테이션
@NoArgsConstructor // 기본 생성자 생성 어노테이션
public class Img {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Table Column Increase Id(Key Value)
    private Long id;
    @Column //Table Column named String
    private String title;
    @Column //Table Column named contents
    private String contents;
    @Column //Table Column named createdTime
    @CreatedDate
    private LocalDateTime createdTime;
    @Column //Table Column named image
    private String imagePath;
}