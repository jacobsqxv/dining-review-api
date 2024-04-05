package com.aries.diningreview.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, updatable = false)
    private String displayName;
    private String city;
    private String state;
    @Column(length = 5)
    private String zipCode;

    private Boolean peanutWatch;
    private Boolean eggWatch;
    private Boolean dairyWatch;
}
