package com.example.demo.service.implementation;

import com.example.demo.domain.Cast;
import com.example.demo.domain.Movies;
import com.example.demo.dto.MovieDTO;
import com.example.demo.dto.MovieSummaryDTO;
import com.example.demo.repository.CastRepository;
import com.example.demo.repository.MovieRepository;
import com.example.demo.service.TestService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

        Optional<Movies> movies = movieRepository.findById(id);

        return movies;

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
    public Page<Movies> getMovieByPeriod(LocalDate date1, LocalDate date2, int size) {
        Pageable pageable=PageRequest.of(size,9);
        return movieRepository.findAllTitleByReleaseDateBetween(date1, date2,pageable);
    }

    @Override
    public MovieSummaryDTO getMovieDTO(Long id) {
        Optional<Movies> movie = movieRepository.findById(id);

        if (movie.isEmpty()) {
            throw new RuntimeException("Movie not found with id " + id);
        }

        List<Cast> castList = castRepository.findByMovieId(movie.get().getId());
        MovieSummaryDTO summary = new MovieSummaryDTO();
        summary.setId(movie.get().getId());
        summary.setDescription(movie.get().getDescription());
//        summary.setGenre(movie.get().getGenre());
        summary.setRating(movie.get().getRating());
        summary.setTitle(movie.get().getTitle());
        summary.setDirector(movie.get().getDirector());
        summary.setReleaseDate(movie.get().getReleaseDate());
        summary.setCastList(castList);

        return summary;
    }
    @Override
    public void updateView(Long id) {
        Movies existingMovie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie Not found" + id));
        existingMovie.setViews(existingMovie.getViews() + 1);
        movieRepository.save(existingMovie);
    }

    @Override
    public Number likeMovie(Long id) {
        Movies existingMovie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie Not found" + id));
        existingMovie.setLikemovie(existingMovie.getLikemovie() + 1);
        movieRepository.save(existingMovie);
        return existingMovie.getLikemovie();

    }

    @Override
    public Number dislikeMovie(Long id) {

        Movies existingMovie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie Not found" + id));
        existingMovie.setDislikemovie(existingMovie.getDislikemovie() + 1);
        movieRepository.save(existingMovie);
        return existingMovie.getDislikemovie();
    }

    @Override
    public Page<Movies> getMovieByPage(int size) {
        Pageable pageable = PageRequest.of(size, 9);
        return movieRepository.findAll(pageable);
    }

    @Override
    public Page<Movies> getMovieByGenre(String genre,int size) {
        Pageable pageable=PageRequest.of(size,9);
        return movieRepository.findMoviesByGenre(genre,pageable);
    }

    @Override
    public Page<Movies> getMovieByReleaseDateAndGenre(LocalDate date1, LocalDate date2, String genre,int size) {
        Pageable pageable=PageRequest.of(size,9);
        return movieRepository.findMoviesByReleaseDateAndGenre(date1,date2,genre,pageable);
    }
}


