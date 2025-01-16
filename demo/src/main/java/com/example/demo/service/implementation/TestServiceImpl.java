package com.example.demo.service.implementation;

import com.example.demo.domain.Movies;
import com.example.demo.repository.MovieRepository;
import com.example.demo.service.TestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


// ADDING LOGIC


@Service
@AllArgsConstructor
public class TestServiceImpl implements TestService {

    private MovieRepository movieRepository;

    @Override
    public Optional<Movies> testMethod(Long id) {
        return movieRepository.findById(id);
    }

    @Override

    public Movies addMovie(Movies movie) {
        return movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(Long id) {

        movieRepository.deleteById(id);

    }

    @Override
    public Movies updateMovie(Movies movie, Long id) {

        Movies existingMovie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id " + id));


        existingMovie.setDirector(movie.getDirector());
        existingMovie.setTitle(movie.getTitle());
        existingMovie.setReleaseDate(movie.getReleaseDate());
        return movieRepository.save(existingMovie);

    }

    @Override
    public List<Movies> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movies> getMovieByReleaseDate(LocalDate releaseDate) {
        List<Movies> moviesList = movieRepository.findTitleByReleaseDate(releaseDate);
        if (moviesList == null) {
            throw new RuntimeException("No movies found");
        }
        return moviesList;
    }

    @Override
    public List<Movies> getMovieByPeriod(LocalDate date1, LocalDate date2) {
        return movieRepository.findAllTitleByReleaseDateBetween(date1, date2);
    }


}
