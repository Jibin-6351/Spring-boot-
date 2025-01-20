package com.example.demo.resource;

import com.example.demo.domain.Cast;
import com.example.demo.repository.CastRepository;
import com.example.demo.service.CastService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class CastResource {

    private final CastRepository castRepository;
    private CastService castService;


    @PostMapping("addcast")
    public ResponseEntity<Cast> createCast(@RequestBody Cast cast) {

        return ResponseEntity.ok(castService.createCaste(cast));


    }


}
