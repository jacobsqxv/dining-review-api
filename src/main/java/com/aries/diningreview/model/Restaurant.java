package com.aries.diningreview.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Restaurant {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String city;
    private String zipCode;
    private String state;

    private Float eggScore;
    private Float peanutScore;
    private Float dairyScore;
    private Float overallScore;
}
