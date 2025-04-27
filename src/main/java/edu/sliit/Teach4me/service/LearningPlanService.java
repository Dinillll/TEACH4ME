package edu.sliit.Teach4me.service;

import edu.sliit.Teach4me.model.LearningPlan;

import java.util.List;

public interface LearningPlanService {
    LearningPlan createPlan(LearningPlan plan);
    public LearningPlan updateMilestoneStatus(String planId, int milestoneIndex, String status);
    void updateProgress(LearningPlan plan);
    List<LearningPlan> findByUserId(String userId);
     void deletePlan(String planId);
}
