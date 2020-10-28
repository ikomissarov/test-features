package com.example.testfeatures.dto;

import com.example.testfeatures.model.Acquisition;
import com.example.testfeatures.model.Feature;
import com.example.testfeatures.model.Properties;
import java.util.Date;
import java.util.Optional;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FeatureDto {

    private String id;
    private long timestamp;
    private String missionName;
    private long beginViewingDate;
    private long endViewingDate;

    public static FeatureDto from(Feature feature) {
        Optional<Properties> properties = Optional.ofNullable(feature.getProperties());
        Optional<Acquisition> acquisition = properties.map(Properties::getAcquisition);
        return FeatureDto.builder()
            .id(properties.map(Properties::getId).orElse(null))
            .timestamp(properties.map(Properties::getTimestamp).map(Date::getTime).orElse(null))
            .missionName(acquisition.map(Acquisition::getMissionName).orElse(null))
            .beginViewingDate(acquisition.map(Acquisition::getBeginViewingDate).map(Date::getTime).orElse(null))
            .endViewingDate(acquisition.map(Acquisition::getEndViewingDate).map(Date::getTime).orElse(null))
            .build();
    }
}
