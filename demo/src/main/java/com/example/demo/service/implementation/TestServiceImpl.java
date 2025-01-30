package com.example.demo.service.implementation;

import com.example.demo.domain.Cast;
import com.example.demo.domain.Movies;
import com.example.demo.dto.MovieSummaryDTO;
import com.example.demo.repository.CastRepository;
import com.example.demo.repository.MovieRepository;
import com.example.demo.service.TestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class TestServiceImpl implements TestService {

    private CastRepository castRepository;
    private MovieRepository movieRepository;

    @Override
    public Optional<Movies> testMethod(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public Movies addMovie(Movies movie) {

        if (movie.getGenre() == null) {
            throw new IllegalArgumentException("Genre should not be null");
        } else {
            return movieRepository.save(movie);
        }
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
            throw new RuntimeException("No Movies found by release date " + releaseDate);
        }
        return moviesList;
    }

    @Override
    public List<Movies> getMovieByPeriod(LocalDate date1, LocalDate date2) {
        return movieRepository.findAllTitleByReleaseDateBetween(date1, date2);
    }

    @Override
    public MovieSummaryDTO getMovieDTO(Long id) {
        Optional<Movies> movie = movieRepository.findById(id);

        if (movie.isEmpty()) {
            throw new RuntimeException("Movie not found with id " + id);
        }

        List<Cast> castList = castRepository.findByMovieId(movie.get().getId());
        MovieSummaryDTO summary = new MovieSummaryDTO();
        summary.setTitle(movie.get().getTitle());
        summary.setDirector(movie.get().getDirector());
        summary.setReleaseDate(movie.get().getReleaseDate());
        summary.setCastList(castList);

        return summary;
    }

    @Override
    public List<MovieSummaryDTO> getAlldataPath() {
        return movieRepository.findAllMoviesWithFileInfo();
    }
}


