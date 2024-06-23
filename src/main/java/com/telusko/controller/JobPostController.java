package com.telusko.controller;

import com.telusko.model.JobPost;
import com.telusko.service.JobPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class JobPostController {


    private final JobPostService jobPostService;

    public JobPostController(JobPostService jobPostService) {
        this.jobPostService = jobPostService;
    }


    //getting all job posts
    @CrossOrigin
    @GetMapping("/JobPost")
    public ResponseEntity<List<JobPost>> getAllJobPosts() {

        return new ResponseEntity<List<JobPost>>(jobPostService.returnAllJobPosts(), HttpStatus.OK);

    }





    // get a Job Post By id

    @GetMapping("/JobPost/{id}")
    public ResponseEntity<JobPost> getJobPostById(@PathVariable int id) {
        Optional<JobPost> jobPostOptional = jobPostService.returnJobPostById(id);
        return jobPostOptional.map(jobPost -> new ResponseEntity<>(jobPost, HttpStatus.FOUND)).orElse(null);
    }





    //add a job post
    @PostMapping("/JobPost")
    public ResponseEntity<JobPost> addJobPost(@RequestBody JobPost jobPost) {
        return new ResponseEntity<JobPost>(jobPostService.addJobPost(jobPost), HttpStatus.CREATED);
    }





    // update a job post by id

    @PutMapping("/JobPost/{id}")
    public ResponseEntity<JobPost> updateJobPost(@PathVariable int id, @RequestBody JobPost jobPost) {
        Optional<JobPost> jobPostOptional = jobPostService.returnJobPostById(id);
        if (jobPostOptional.isPresent()) {
            return new ResponseEntity<JobPost>(jobPostService.updateJobPostById(id, jobPost), HttpStatus.CREATED);
        }
        return null;
    }





    //delete a JobPost by id

    @DeleteMapping("/JobPost/{id}")
    public ResponseEntity<String> deleteJobPostById(@PathVariable int id) {
        Optional<JobPost> jobPostOptional = jobPostService.returnJobPostById(id);
        if (jobPostOptional.isPresent()) {
            return new ResponseEntity<String>(jobPostService.deleteJobPostById(id), HttpStatus.ACCEPTED);

        }
        return null;
    }


//to search with any word or letter
    @GetMapping("/JobPost/search/{text}")
    public ResponseEntity<List<JobPost>> searchJobPosts(@PathVariable String text) {
        List<JobPost> jobPosts = jobPostService.wildcardSearch(text);
        if (!jobPosts.isEmpty()) {
            return new ResponseEntity<>(jobPosts, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    //search by experience
    @GetMapping("/JobPost/searchByExp")
    public ResponseEntity<List<JobPost>> searchByReqExperience(@RequestParam int reqExperience) {
        List<JobPost> jobPosts = jobPostService.searchByReqExperience(reqExperience);
        if (!jobPosts.isEmpty()) {
            return new ResponseEntity<>(jobPosts, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}