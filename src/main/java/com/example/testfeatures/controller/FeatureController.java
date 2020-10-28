package com.example.testfeatures.controller;

import com.example.testfeatures.dto.FeatureDto;
import com.example.testfeatures.model.Feature;
import com.example.testfeatures.model.Properties;
import com.example.testfeatures.service.FeatureService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("features")
@RequiredArgsConstructor
public class FeatureController {

    private final FeatureService service;

    @ApiOperation(value = "Get a feature by id")
    @GetMapping("/{id}")
    public FeatureDto get(@PathVariable String id) {
        return service.get(id)
            .map(FeatureDto::from)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @ApiOperation(value = "Get all features")
    @GetMapping
    public List<FeatureDto> getAll() {
        return service.getAll().stream()
            .map(FeatureDto::from)
            .collect(Collectors.toList());
    }

    @ApiOperation(value = "Get a feature's quicklook")
    @GetMapping(value = "/{id}/quicklook", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getQuicklook(@PathVariable String id) {
        String quicklookString = service.get(id)
            .map(Feature::getProperties)
            .map(Properties::getQuicklook)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return Base64Utils.decodeFromString(quicklookString);
    }
}
