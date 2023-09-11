package com.example.Proj1_2021202057.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
@Entity
@Getter
@Setter
@EntityListeners({AuditingEntityListener.class}) // Entity에서 특정 이벤트 수행을 위한 어노테이션
@NoArgsConstructor // 기본 생성자 생성 어노테이션
public class COMMENT {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long photoid;
    @Column
    private Long userid;
    @Column
    private String commentstring; //manager_us, new_us, normal_us
}
