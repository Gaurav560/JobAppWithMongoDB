package com.telusko.repo;

import com.telusko.model.JobPost;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface JobPostRepo extends MongoRepository<JobPost, Integer> {


    List<JobPost> findByReqExperience(int reqExperience);

    @Query("{ '$or': [ { 'postProfile': { $regex: ?0, $options: 'i' } }, { 'postDesc': { $regex: ?0, $options: 'i' } }, { 'postTechStack': { $regex: ?0, $options: 'i' } } ] }")
    List<JobPost> findBySearchTerm(String searchTerm);
}