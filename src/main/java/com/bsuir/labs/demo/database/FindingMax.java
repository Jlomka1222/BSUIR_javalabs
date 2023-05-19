package com.bsuir.labs.demo.database;

import com.bsuir.labs.demo.models.Models;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FindingMax extends JpaRepository<Models, Integer> {}
