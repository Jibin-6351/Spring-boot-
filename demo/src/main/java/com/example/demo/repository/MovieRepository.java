package com.example.demo.repository;

import com.example.demo.domain.Movies;
import org.springframework.data.jpa.repository.JpaRepository;



public interface MovieRepository extends JpaRepository<Movies, Long>{



}
