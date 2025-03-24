package com.example.demo.domain;

import com.example.demo.domain.enums.Roles;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "casts")
@Data
@Getter
@Setter

public class Cast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)

    private Roles roles;

    @Column(name = "movie_id")
    private Long movieId;


}
