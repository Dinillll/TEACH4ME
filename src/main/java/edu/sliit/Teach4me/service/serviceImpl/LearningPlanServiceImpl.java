//package com.example.PAF.service;
//
//import com.example.PAF.model.LearningPlan;
//import com.example.PAF.repository.LearningPlanRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import java.util.List;
//
//import java.util.NoSuchElementException;
//
//@Service
//public class LearningPlanService {
//    @Autowired
//    private LearningPlanRepository repository;
//
//    public List<LearningPlan> findByUserId(String userId) {
//        return repository.findByUserId(userId);
//    }
//
//    public LearningPlan createPlan(LearningPlan plan) {
//        plan.setProgress(0); // Initialize progress
//        return repository.save(plan);
//    }
//
//    public LearningPlan updateMilestoneStatus(String planId, int milestoneIndex, String status) {
//        LearningPlan plan = repository.findById(planId)
//                .orElseThrow(() -> new NoSuchElementException("Plan not found with ID: " + planId));
//
//        if (milestoneIndex >= plan.getMilestones().size()) {
//            throw new IllegalArgumentException("Invalid milestone index");
//        }
//
//        plan.getMilestones().get(milestoneIndex).setStatus(status);
//        updateProgress(plan); // Recalculate progress
//        return repository.save(plan);
//    }
//
//    private void updateProgress(LearningPlan plan) {
//        long completed = plan.getMilestones().stream()
//                .filter(m -> "COMPLETED".equals(m.getStatus()))
//                .count();
//        plan.setProgress((int) ((completed * 100) / plan.getMilestones().size()));
//    }
//
//    public void deletePlan(String planId) {
//        repository.deleteById(planId);
//    }
//}




package edu.sliit.Teach4me.service.serviceImpl;

import edu.sliit.Teach4me.model.LearningPlan;
import edu.sliit.Teach4me.repository.LearningPlanRepository;
import edu.sliit.Teach4me.service.LearningPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LearningPlanServiceImpl implements LearningPlanService {

    private final LearningPlanRepository repository;

    @Autowired
    public LearningPlanServiceImpl(LearningPlanRepository repository) {
        this.repository = repository;
    }

    @Override
    public LearningPlan createPlan(LearningPlan plan) {
        plan.setProgress(0);
        return repository.save(plan);
    }

    @Override
    public LearningPlan updateMilestoneStatus(String planId, int milestoneIndex, String status) {
        LearningPlan plan = repository.findById(planId)
                .orElseThrow(() -> new NoSuchElementException("Plan not found"));
        plan.getMilestones().get(milestoneIndex).setStatus(status);
        updateProgress(plan);
        return repository.save(plan);
    }

    @Override
    public void updateProgress(LearningPlan plan) {
        long completed = plan.getMilestones().stream()
                .filter(m -> "COMPLETED".equals(m.getStatus()))
                .count();
        plan.setProgress((int) ((completed * 100) / plan.getMilestones().size()));
    }

    @Override
    public List<LearningPlan> findByUserId(String userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public void deletePlan(String planId) {
        repository.deleteById(planId);
    }
}