package com.example.demo.domain;

import com.example.demo.domain.enums.Genre;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "movies")
@Data
@Getter
@Setter
public class Movies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String director;
    private LocalDate releaseDate;
    private String rating;
    private String description;
    private Long views;
    private Long likemovie;
    private Long dislikemovie;
    private String movie_trailer;
//    private Genre genres;
    private String genre;
    private String duration;
    @OneToOne
    @JoinColumn(name = "file_id")
    private File file;


}
