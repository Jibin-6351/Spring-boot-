package com.example.demo.repository;

import com.example.demo.domain.Movies;
import com.example.demo.dto.MovieSummaryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;


public interface MovieRepository extends JpaRepository<Movies, Long> {


    @Query("SELECT new com.example.demo.dto.MovieSummaryDTO(m.id, m.title, m.director, m.releaseDate, m.description, m.rating,m.views,m.likemovie,m.dislikemovie, f.path) " +
            "FROM Movies m " +
            "INNER JOIN File f ON f.id = m.id")
    List<MovieSummaryDTO> findAllMoviesWithFileInfo();
    boolean existsById(Long id);
    List<Movies> findTitleByReleaseDate(LocalDate releaseDate);
    List<Movies> findAllTitleByReleaseDateBetween(LocalDate date1, LocalDate date2);


}
