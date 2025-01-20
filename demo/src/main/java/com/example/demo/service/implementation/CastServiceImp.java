package com.example.demo.service.implementation;

import com.example.demo.domain.Cast;
import com.example.demo.repository.CastRepository;
import com.example.demo.service.CastService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class CastServiceImp implements CastService {

    private CastRepository castRepository;

    @Override
    public Cast createCaste(Cast cast) {
        return castRepository.save(cast);
    }
}
