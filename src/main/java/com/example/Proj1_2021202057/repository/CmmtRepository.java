package com.example.Proj1_2021202057.repository;


import com.example.Proj1_2021202057.domain.COMMENT;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CmmtRepository extends JpaRepository<COMMENT, Long> {
    //  List<USerm> findByUsername(String username);
    List<COMMENT> findAll();
}

