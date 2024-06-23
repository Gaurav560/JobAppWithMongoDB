package com.telusko.service;

import com.telusko.model.JobPost;
import com.telusko.repo.JobPostRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobPostService {

    //constructor injection instead of field injection
    private final JobPostRepo jobPostRepo;

    public JobPostService(JobPostRepo jobPostRepo) {
        this.jobPostRepo = jobPostRepo;
    }

    public List<JobPost> returnAllJobPosts() {
        return jobPostRepo.findAll();
    }

    public Optional<JobPost> returnJobPostById(int id) {
        return jobPostRepo.findById(id);
    }

    public JobPost addJobPost(JobPost jobPost) {
        return jobPostRepo.save(jobPost);
    }

    public JobPost updateJobPostById(int id, JobPost updatedJobPost) {
        Optional<JobPost> existingJobPostOptional = jobPostRepo.findById(id);

        if (existingJobPostOptional.isPresent()) {
            JobPost existingJobPost = existingJobPostOptional.get();
            existingJobPost.setPostProfile(updatedJobPost.getPostProfile());
            existingJobPost.setPostDesc(updatedJobPost.getPostDesc());
            existingJobPost.setReqExperience(updatedJobPost.getReqExperience());
            existingJobPost.setPostTechStack(updatedJobPost.getPostTechStack());
            // Update other fields as needed

            return jobPostRepo.save(existingJobPost);
        } else {
            return null;
        }
    }

    public String deleteJobPostById(int id) {
        if (jobPostRepo.existsById(id)) {
            jobPostRepo.deleteById(id);
            return "Job post deleted successfully.";
        } else {
            return "Job post not found.";
        }
    }


    public List<JobPost> searchByReqExperience(int reqExperience) {
        return jobPostRepo.findByReqExperience(reqExperience);
    }


    public List<JobPost> wildcardSearch(String searchTerm) {
        return jobPostRepo.findBySearchTerm(searchTerm);
    }
}