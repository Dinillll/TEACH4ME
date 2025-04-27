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

import edu.sliit.Teach4me.config.ApiResponse;
import edu.sliit.Teach4me.dto.LearningPlanUpdateAddDTO;
import edu.sliit.Teach4me.dto.MilestoneRequest;
import edu.sliit.Teach4me.model.LearningPlan;
import edu.sliit.Teach4me.repository.LearningPlanRepository;
import edu.sliit.Teach4me.service.LearningPlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
public class LearningPlanServiceImpl implements LearningPlanService {

    private final LearningPlanRepository repository;

    @Autowired
    public LearningPlanServiceImpl(LearningPlanRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<ApiResponse<LearningPlan>> createPlan(LearningPlan plan) {
        try {
            plan.setProgress(0);
            LearningPlan learningPlan = repository.save(plan);
            return ApiResponse.successResponse("Plan created successfully", learningPlan);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public ResponseEntity<ApiResponse<LearningPlan>> updateMilestoneStatus(String planId, int milestoneIndex, MilestoneRequest milestoneRequest) {
        try{
            LearningPlan plan = repository.findById(planId)
                    .orElseThrow(() -> new NoSuchElementException("Plan not found"));

            if(milestoneRequest.getStatus() != null){
                plan.getMilestones().get(milestoneIndex).setStatus(milestoneRequest.getStatus());
            }

            if(milestoneRequest.getDescription() != null){
                plan.getMilestones().get(milestoneIndex).setDescription(milestoneRequest.getDescription());
            }

            if(milestoneRequest.getTitle() != null){
                plan.getMilestones().get(milestoneIndex).setTitle(milestoneRequest.getTitle());
            }

            if(milestoneRequest.getDueDate() != null){
                plan.getMilestones().get(milestoneIndex).setDueDate(milestoneRequest.getDueDate());
            }

            log.info("Updated milestone status for plan: {} at index: {} with status: {}", planId, milestoneIndex, milestoneRequest.getStatus());
            return ApiResponse.successResponse("Plan updated successfully", repository.save(plan));

        } catch (NoSuchElementException e) {
            log.error("Plan not found: {}", planId, e);
            return ApiResponse.errorResponse("Plan not found", HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            log.error("Invalid milestone index: {} for plan: {}", milestoneIndex, planId, e);
            return ApiResponse.errorResponse("Invalid milestone index", HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            log.error("Error updating milestone status for plan: {} at index: {}", planId, milestoneIndex, e);
            return ApiResponse.errorResponse("Error updating milestone status", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void updateProgress(LearningPlan plan) {
        long completed = plan.getMilestones().stream()
                .filter(m -> "COMPLETED".equals(m.getStatus()))
                .count();
        plan.setProgress((int) ((completed * 100) / plan.getMilestones().size()));
    }

    @Override
    public  ResponseEntity<ApiResponse<List<LearningPlan>>> findByUserId(String userId) {
       try{
           List<LearningPlan> learningPlans =  repository.findByUserId(userId);
              if(learningPlans.isEmpty()){
                return ApiResponse.successResponse("No plans found for user: " + userId, null);
              }

              return ApiResponse.successResponse("Plans found for user: " + userId, learningPlans);
       } catch (Exception e) {
              log.error("Error finding plans for user: {}", userId, e);
              return ApiResponse.errorResponse("Error finding plans for user: " + userId, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @Override
    public ResponseEntity<ApiResponse<List<Boolean>>> deletePlan(String planId) {
       try{
           repository.deleteById(planId);
           log.info("Deleted plan with ID: {}", planId);
           return ApiResponse.successResponse("Plan deleted successfully", null);
       } catch (Exception e) {
           log.error("Error deleting plan: {}", planId, e);
           return ApiResponse.errorResponse("Error deleting plan", HttpStatus.INTERNAL_SERVER_ERROR);

       }
    }

    @Override
    public ResponseEntity<ApiResponse<LearningPlan>> updatePlan( String planId, LearningPlanUpdateAddDTO updateAddDTO) {
       try{
           LearningPlan plan = repository.findById(planId)
                   .orElseThrow(() -> new NoSuchElementException("Plan not found"));

           if (updateAddDTO.getTitle() != null) {
               plan.setTitle(updateAddDTO.getTitle());
           }
           if (updateAddDTO.getDescription() != null) {
               plan.setDescription(updateAddDTO.getDescription());
           }
           if (updateAddDTO.getStartDate() != null) {
               plan.setStartDate(updateAddDTO.getStartDate());
           }
           if (updateAddDTO.getEndDate() != null) {
               plan.setEndDate(updateAddDTO.getEndDate());
           }
           return  ApiResponse.successResponse("Plan updated successfully", repository.save(plan));
       } catch (Exception e) {
              log.error("Error updating plan: {}", planId, e);
              return ApiResponse.errorResponse("Error updating plan", HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @Override
    public ResponseEntity<ApiResponse<LearningPlan>> deleteMilestone(String planId, int milestoneIndex) {
        try{
            LearningPlan plan = repository.findById(planId)
                    .orElseThrow(() -> new NoSuchElementException("Plan not found"));

            if (milestoneIndex >= 0 && milestoneIndex < plan.getMilestones().size()) {
                plan.getMilestones().remove(milestoneIndex);
                updateProgress(plan); // Recalculate progress
                return  ApiResponse.successResponse("Milestone deleted successfully", repository.save(plan));
            } else {
                throw new IllegalArgumentException("Invalid milestone index");
            }
        } catch (NoSuchElementException e) {
            log.error("Plan not found: {}", planId, e);
            return ApiResponse.errorResponse("Plan not found", HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            log.error("Invalid milestone index: {} for plan: {}", milestoneIndex, planId, e);
            return ApiResponse.errorResponse("Invalid milestone index", HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            log.error("Error deleting milestone at index: {} for plan: {}", milestoneIndex, planId, e);
            return ApiResponse.errorResponse("Error deleting milestone", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}