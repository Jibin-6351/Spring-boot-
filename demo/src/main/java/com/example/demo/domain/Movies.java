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

    @Enumerated(EnumType.STRING)

    private Genre genre;


    @ManyToOne
    @JoinColumn(name = "file_id")
    private File file;



}
