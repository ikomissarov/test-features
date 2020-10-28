package com.example.testfeatures.model;

import java.util.Date;
import lombok.Data;

@Data
public class Acquisition {

    private String missionName;
    private Date beginViewingDate;
    private Date endViewingDate;
}
