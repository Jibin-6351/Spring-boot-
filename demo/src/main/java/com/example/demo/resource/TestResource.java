package com.example.demo.resource;

import com.example.demo.domain.Movies;
import com.example.demo.service.TestService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class TestResource {

    private TestService testService;

    @GetMapping("movies")
    public ResponseEntity<List<Movies>> getAllMovie() {
        return ResponseEntity.ok(testService.getAllMovies());
    }

    @GetMapping("movie")
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
        testService.deleteMovie(id);
        return ResponseEntity.ok("Deleted Successfull");
    }


    @PutMapping("movie")
    public ResponseEntity<Movies> update(@RequestBody Movies movie) {

        return ResponseEntity.ok(testService.updateMovie(movie, movie.getId()));

    }


}
