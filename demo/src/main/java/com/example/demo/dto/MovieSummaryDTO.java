package com.example.demo.dto;

import com.example.demo.domain.Cast;
import com.example.demo.domain.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieSummaryDTO {

    private Long id;
    private String title;
    private String director;
    private LocalDate releaseDate;
    private String path;
    private String description;
    private String rating;
    private Long views;
    private Long likemovies;
    private Long dislikemovie;

    private List<Cast> castList;

    public MovieSummaryDTO(Long id,String title,String director,LocalDate releaseDate,String description,String rating, Long views, Long dislikemovie,Long likemovies,  String path) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.releaseDate = releaseDate;
        this.description = description;
        this.rating = rating;
        this.path = path;
        this.views=views;
        this.likemovies=likemovies;
        this.dislikemovie=dislikemovie;



    }


}
