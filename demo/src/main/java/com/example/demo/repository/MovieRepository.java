package com.example.demo.repository;

import com.example.demo.domain.Movies;
import com.example.demo.dto.MovieSummaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;


public interface MovieRepository extends JpaRepository<Movies, Long> {

    boolean existsById(Long id);
    List<Movies> findTitleByReleaseDate(LocalDate releaseDate);
    Page<Movies> findAllTitleByReleaseDateBetween(LocalDate date1, LocalDate date2,Pageable pageable);
    @Query("SELECT m FROM Movies m WHERE m.genre LIKE %:genre%")
    Page<Movies> findMoviesByGenre(String genre, Pageable pageable);
    @Query("SELECT m FROM Movies m WHERE m.releaseDate BETWEEN :startDate AND :endDate AND m.genre LIKE %:genre%")
    Page<Movies> findMoviesByReleaseDateAndGenre(LocalDate startDate, LocalDate endDate, String genre,Pageable pageable);


}
