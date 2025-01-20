package com.example.demo.domain;

import com.example.demo.domain.enums.Genre;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

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

    @Enumerated(EnumType.STRING)

    private Genre genre;



}
