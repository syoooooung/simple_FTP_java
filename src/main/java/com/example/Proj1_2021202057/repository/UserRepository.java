package com.example.Proj1_2021202057.repository;

import com.example.Proj1_2021202057.domain.Img;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Img, Long> {
    List<Img> findAll(Sort sort);
    // 추가적인 메소드 선언 가능
}

