package com.example.testfeatures.model;

import java.util.Date;
import lombok.Data;

@Data
public class Properties {

    private String id;
    private Date timestamp;
    private Acquisition acquisition;
    private String quicklook;
}
