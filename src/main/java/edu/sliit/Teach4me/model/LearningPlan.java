package edu.sliit.Teach4me.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "learning_plans")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LearningPlan {
    @Id
    private String id;
    private String userId;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Milestone> milestones;
    private int progress; // Percentage (0-100)
    private List<String> resourceLinks; // URLs to external resources
}

