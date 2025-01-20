package com.example.demo.repository;

import com.example.demo.domain.Cast;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CastRepository extends JpaRepository<Cast, Long> {

    List<Cast> findByMovieId(Long movieId);

}
