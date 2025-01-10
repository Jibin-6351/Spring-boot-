package com.example.demo.service;

import com.example.demo.domain.Movies;

import java.util.List;
import java.util.Optional;

public interface TestService {

    Optional<Movies> testMethod(Long id);

    Movies addMovie(Movies movies);

    void deleteMovie(Long id);

    Movies updateMovie(Movies movies, Long id);

    List<Movies> getAllMovies();


}
