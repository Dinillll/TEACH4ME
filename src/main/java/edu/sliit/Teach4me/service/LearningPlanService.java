package edu.sliit.Teach4me.service;

import edu.sliit.Teach4me.dto.MilestoneRequest;
import edu.sliit.Teach4me.model.LearningPlan;

import java.util.List;

public interface LearningPlanService {
    LearningPlan createPlan(LearningPlan plan);
    LearningPlan updateMilestoneStatus(String planId, int milestoneIndex, MilestoneRequest milestoneRequest);
    void updateProgress(LearningPlan plan);
    List<LearningPlan> findByUserId(String userId);
    void deletePlan(String planId);
}
