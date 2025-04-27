package edu.sliit.Teach4me.controller;



import edu.sliit.Teach4me.model.LearningPlan;
import edu.sliit.Teach4me.service.LearningPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.ok(service.createPlan(plan));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LearningPlan>> getPlansByUser(@PathVariable String userId) {
        return ResponseEntity.ok(service.findByUserId(userId));
    }

    @PutMapping("/{planId}/milestones/{milestoneIndex}")
    public ResponseEntity<LearningPlan> updateMilestone(
            @PathVariable String planId,
            @PathVariable int milestoneIndex,
            @RequestParam String status) {
        return ResponseEntity.ok(service.updateMilestoneStatus(planId, milestoneIndex, status));
    }

    @DeleteMapping("/{planId}")
    public ResponseEntity<Void> deletePlan(@PathVariable String planId) {
        service.deletePlan(planId);
        return ResponseEntity.noContent().build();
    }
}
