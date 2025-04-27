package edu.sliit.Teach4me.controller;



import edu.sliit.Teach4me.config.ApiResponse;
import edu.sliit.Teach4me.dto.LearningPlanUpdateAddDTO;
import edu.sliit.Teach4me.dto.MilestoneRequest;
import edu.sliit.Teach4me.model.LearningPlan;
import edu.sliit.Teach4me.service.LearningPlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/learning-plans")
public class LearningPlanController {

    private final LearningPlanService learningPlanService;

    @Autowired
    public LearningPlanController(LearningPlanService service) {
        this.learningPlanService = service;
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<LearningPlan>> createPlan(@RequestBody LearningPlan plan) {
        log.info("Attempting to create a new learning plan: {}", plan.getTitle());
        return learningPlanService.createPlan(plan);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<LearningPlan>>> getPlansByUser(@PathVariable String userId) {
        log.info("Attempting to get learning plans: {}", userId);
        return learningPlanService.findByUserId(userId);
    }

    @PutMapping("/{planId}/milestones/{milestoneIndex}")
    public ResponseEntity<ApiResponse<LearningPlan>> updateMilestone(
            @PathVariable String planId,
            @PathVariable int milestoneIndex,
            @RequestBody MilestoneRequest milestoneRequest) {
        log.info("Attempting to update milestone status for plan: {} at index: {}", planId, milestoneIndex);
        return learningPlanService.updateMilestoneStatus(planId, milestoneIndex, milestoneRequest);
    }


    @DeleteMapping("/{planId}/milestones/{milestoneIndex}")
    public ResponseEntity<ApiResponse<LearningPlan>> deleteMilestone(
            @PathVariable String planId,
            @PathVariable int milestoneIndex) {
        log.info("Attempting to delete milestone at index: {} for plan: {}", milestoneIndex, planId);
        return learningPlanService.deleteMilestone(planId, milestoneIndex);
    }

    @PutMapping("/{planId}/update")
    public ResponseEntity<ApiResponse<LearningPlan>> updatePlan(@PathVariable String planId, @RequestBody LearningPlanUpdateAddDTO updateAddDTO) {
        log.info("Attempting to update progress for learning plan: {}", planId);
        return learningPlanService.updatePlan(planId, updateAddDTO);
    }

    @DeleteMapping("/{planId}")
    public ResponseEntity<ApiResponse<Boolean>> deletePlan(@PathVariable String planId) {
        try {
            log.info("Attempting to delete learning plan: {}", planId);
            learningPlanService.deletePlan(planId);
           return ApiResponse.successResponse("Plan deleted successfully",true);
        } catch (Exception e) {
            log.error("Error deleting learning plan: {}", e.getMessage());
            return ApiResponse.errorResponse("Failed to delete plan", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
