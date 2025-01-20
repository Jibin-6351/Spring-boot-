package com.example.demo.dto;

import com.example.demo.domain.Cast;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class MovieSummaryDTO {


    private String title;
    private String director;
    private LocalDate date;
    private List<Cast> castList;


}
