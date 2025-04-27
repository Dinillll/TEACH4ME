package edu.sliit.Teach4me.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LearningPlanUpdateAddDTO {
    private String userId;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private int progress;
}
