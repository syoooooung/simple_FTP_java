package com.example.Proj1_2021202057.repository;

import com.example.Proj1_2021202057.domain.Img;
import com.example.Proj1_2021202057.domain.USerm;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface URepository extends JpaRepository<USerm, Long> {
  //  List<USerm> findByUsername(String username);
    List<USerm> findAll(Sort sort);
}

