package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "movies")
@Data
public class Movies {

    @Id
    private Long id;

    private String title;

    private String director;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
