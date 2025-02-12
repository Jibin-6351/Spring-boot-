package com.example.demo.resource;

import com.example.demo.Exception.ErrorObj;
import com.example.demo.Jsonconvert;
import com.example.demo.domain.Movies;
import com.example.demo.dto.MovieSummaryDTO;
import com.example.demo.repository.MovieRepository;
import com.example.demo.service.TestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/movie")
public class TestResource {

    private final MovieRepository movieRepository;
    private TestService testService;

    @GetMapping("movie")
    public ResponseEntity<List<Movies>> getAllMovie() {
        return ResponseEntity.ok(testService.getAllMovies());
    }

    @GetMapping("{id}")
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

    @GetMapping("movie/cast/{id}")

    public ResponseEntity<MovieSummaryDTO> getMovieDto(@PathVariable Long id) {
        return ResponseEntity.ok(testService.getMovieDTO(id));
    }

    @GetMapping("/path")
    public ResponseEntity<List<MovieSummaryDTO>> getPath() {
        return ResponseEntity.ok(testService.getAlldataPath());
    }


    @PutMapping("views/{id}")
    public void updateview(@PathVariable Long id) {
        testService.updateView(id);


    }


    @PutMapping("like/{id}")
    public ResponseEntity<Jsonconvert> like(@PathVariable Long id) {
        testService.likeMovie(id);
        Jsonconvert jsonconvert = new Jsonconvert("Like Added");
        return new ResponseEntity<>(jsonconvert, HttpStatus.OK);
    }


    @PutMapping("dislike/{id}")
    public ResponseEntity<Jsonconvert> dislike(@PathVariable Long id) {
        testService.dislikeMovie(id);
        Jsonconvert jsonconvert = new Jsonconvert("Dislike Added");
        return new ResponseEntity<>(jsonconvert, HttpStatus.OK);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorObj> runTimeException(Exception e) {

        ErrorObj errorObj = new ErrorObj();
        errorObj.setError(e.getMessage());
        errorObj.setStatusCode(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorObj, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorObj> illegalArgumentException(IllegalArgumentException e) {
        ErrorObj errorObj = new ErrorObj();
        errorObj.setError(e.getMessage());
        errorObj.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorObj, HttpStatus.BAD_REQUEST);
    }


}
