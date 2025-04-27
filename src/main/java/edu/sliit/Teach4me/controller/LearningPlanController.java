package edu.sliit.Teach4me.controller;



import edu.sliit.Teach4me.dto.LearningPlanUpdateAddDTO;
import edu.sliit.Teach4me.dto.MilestoneRequest;
import edu.sliit.Teach4me.model.LearningPlan;
import edu.sliit.Teach4me.service.LearningPlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/learning-plans")
public class LearningPlanController {

    private final LearningPlanService service;

    @Autowired
    public LearningPlanController(LearningPlanService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity<LearningPlan> createPlan(@RequestBody LearningPlan plan) {
        log.info("Attempting to create a new learning plan: {}", plan.getTitle());
        return ResponseEntity.ok(service.createPlan(plan));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LearningPlan>> getPlansByUser(@PathVariable String userId) {
        log.info("Attempting to get learning plans: {}", userId);
        return ResponseEntity.ok(service.findByUserId(userId));
    }

    @PutMapping("/{planId}/milestones/{milestoneIndex}")
    public ResponseEntity<LearningPlan> updateMilestone(
            @PathVariable String planId,
            @PathVariable int milestoneIndex,
            @RequestBody MilestoneRequest milestoneRequest) {
        log.info("Attempting to update milestone status for plan: {} at index: {}", planId, milestoneIndex);
        return ResponseEntity.ok(service.updateMilestoneStatus(planId, milestoneIndex, milestoneRequest));
    }

    @PutMapping("/{planId}/update")
    public ResponseEntity<LearningPlan> updatePlan(@PathVariable String planId, @RequestBody LearningPlanUpdateAddDTO updateAddDTO) {
        LearningPlan plan = service.updatePlan(planId, updateAddDTO);
        log.info("Attempting to update progress for learning plan: {}", planId);
        return ResponseEntity.ok(plan);
    }

    @DeleteMapping("/{planId}")
    public ResponseEntity<Void> deletePlan(@PathVariable String planId) {
        service.deletePlan(planId);
        log.info("Attempting to delete learning plan: {}", planId);
        return ResponseEntity.noContent().build();
    }

}
