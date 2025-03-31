package com.example.demo.service;

import com.example.demo.domain.Movies;
import com.example.demo.dto.MovieSummaryDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TestService {

    Optional<Movies> testMethod(Long id);

    Movies addMovie(Movies movies);

    void deleteMovie(Long id);

    Movies updateMovie(Movies movies, Long id);

    List<Movies> getAllMovies();

    List<Movies> getMovieByReleaseDate(LocalDate releaseDate);

    Page<Movies> getMovieByPeriod(LocalDate date1, LocalDate date2,int size);

    MovieSummaryDTO getMovieDTO(Long id);
    void updateView(Long id);

    Number likeMovie(Long id);

    Number dislikeMovie(Long id);

    Page<Movies> getMovieByPage(int size);
    Page<Movies> getMovieByGenre(String genre,int size);
    Page<Movies> getMovieByReleaseDateAndGenre(LocalDate date1,LocalDate date2,String genre,int size);
}
