package com.example.testfeatures.dao;

import com.example.testfeatures.model.Feature;
import com.example.testfeatures.model.FeatureCollection;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FeatureRepository {

    private Map<String, Feature> features;

    @PostConstruct
    public void init() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("source-data.json");
        JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, FeatureCollection.class);
        List<FeatureCollection> featureCollections = mapper.readValue(inputStream, type);

        features = featureCollections.stream()
            .map(FeatureCollection::getFeatures)
            .flatMap(Collection::stream)
            .collect(Collectors.toMap(f -> f.getProperties().getId(), Function.identity()));
        log.info("Loaded {} features", features.size());
    }

    public List<Feature> findAll() {
        return new ArrayList<>(features.values());
    }

    public Optional<Feature> find(String id) {
        return Optional.ofNullable(features.get(id));
    }
}
