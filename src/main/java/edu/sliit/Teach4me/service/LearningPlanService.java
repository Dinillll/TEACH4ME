package edu.sliit.Teach4me.service;

import edu.sliit.Teach4me.config.ApiResponse;
import edu.sliit.Teach4me.dto.LearningPlanUpdateAddDTO;
import edu.sliit.Teach4me.dto.MilestoneRequest;
import edu.sliit.Teach4me.model.LearningPlan;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LearningPlanService {
    ResponseEntity<ApiResponse<LearningPlan>> createPlan(LearningPlan plan);
    ResponseEntity<ApiResponse<LearningPlan>> updateMilestoneStatus(String planId, int milestoneIndex, MilestoneRequest milestoneRequest);
    void updateProgress(LearningPlan plan);
    ResponseEntity<ApiResponse<List<LearningPlan>>> findByUserId(String userId);
    ResponseEntity<ApiResponse<List<Boolean>>> deletePlan(String planId);
    ResponseEntity<ApiResponse<LearningPlan>> updatePlan(String planId, LearningPlanUpdateAddDTO updateAddDTO);
    ResponseEntity<ApiResponse<LearningPlan>> deleteMilestone(String planId, int milestoneIndex);
}
