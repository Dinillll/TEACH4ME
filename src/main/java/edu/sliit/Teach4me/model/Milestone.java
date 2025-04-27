package edu.sliit.Teach4me.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Milestone {
    private String title;
    private String description;
    private String status; // NOT_STARTED, IN_PROGRESS, COMPLETED
    private LocalDate dueDate;
}
