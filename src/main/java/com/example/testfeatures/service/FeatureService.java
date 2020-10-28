package com.example.testfeatures.service;

import com.example.testfeatures.dao.FeatureRepository;
import com.example.testfeatures.model.Feature;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeatureService {

    private final FeatureRepository repository;

    public List<Feature> getAll() {
        return repository.findAll();
    }

    public Optional<Feature> get(String id) {
        return repository.find(id);
    }
}
