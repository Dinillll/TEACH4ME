package edu.sliit.Teach4me.repository;

import edu.sliit.Teach4me.model.LearningPlan;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LearningPlanRepository extends MongoRepository<LearningPlan, String> {
    List<LearningPlan> findByUserId(String userId); // This must exist
}
