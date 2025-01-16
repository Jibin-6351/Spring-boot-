package com.example.demo.repository;

import com.example.demo.domain.Movies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface MovieRepository extends JpaRepository<Movies, Long> {

    boolean existsById(Long id);

    List<Movies> findTitleByReleaseDate(LocalDate releaseDate);

    List<Movies> findAllTitleByReleaseDateBetween(LocalDate Date1, LocalDate Date2);


}
