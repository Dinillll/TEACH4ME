package edu.sliit.Teach4me.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MilestoneRequest {
    private String title;
    private String description;
    private String status; // NOT_STARTED, IN_PROGRESS, COMPLETED
    private LocalDate dueDate;
}
