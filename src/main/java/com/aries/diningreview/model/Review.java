package com.aries.diningreview.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Review {
    @Id
    @GeneratedValue
    private Long id;

    private String submittedBy;
    private Long restaurantId;

    private Float peanutScore;
    private Float eggScore;
    private Float dairyScore;
    private String optionalCommentary;

    private ReviewStatus status;
}
