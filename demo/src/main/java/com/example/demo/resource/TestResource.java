package com.example.demo.resource;

import com.example.demo.domain.Movies;
import com.example.demo.repository.MovieRepository;
import com.example.demo.service.TestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class TestResource {

    private final MovieRepository movieRepository;
    private TestService testService;

    @GetMapping("movies")
    public ResponseEntity<List<Movies>> getAllMovie() {
        return ResponseEntity.ok(testService.getAllMovies());
    }

    @GetMapping("movie/{id}")
    public ResponseEntity<Optional<Movies>> run(@PathVariable Long id) {

        Optional<Movies> movie = testService.testMethod(id);
        if (movie.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(movie);
        }

    }


    @PostMapping("movie")
    public ResponseEntity<Movies> run(@RequestBody Movies movies) {


        return ResponseEntity.ok(testService.addMovie(movies));
    }


    @DeleteMapping("movie/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        if (movieRepository.existsById(id)) {
            testService.deleteMovie(id);
            return ResponseEntity.ok("Deleted Successfull");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND...");
        }

    }

    @PutMapping("movie")
    public ResponseEntity<Movies> update(@RequestBody Movies movie) {

        return ResponseEntity.ok(testService.updateMovie(movie, movie.getId()));


    }

    @GetMapping("/movies/{releaseDate}")
    public ResponseEntity<List<Movies>> getMovieByReleaseYear(@PathVariable LocalDate releaseDate) {
        List<Movies> movies = testService.getMovieByReleaseDate(releaseDate);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("movie/release")
    public ResponseEntity<List<Movies>> getMovieByTimePeriod(@RequestParam LocalDate date1, @RequestParam LocalDate date2) {
        List<Movies> movies = testService.getMovieByPeriod(date1, date2);

        return ResponseEntity.ok(movies);
    }


}
