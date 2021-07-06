package com.example.demo.issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Transactional
    public List<String> createIssue(Issue issue){
        try {
            if (!issueRepository.existsById(issue.getId())){
                issue.setId(null == issueRepository.findMaxId()? 0 : issueRepository.findMaxId() + 1);
                issueRepository.save(issue);
                return List.of("Issue record created successfully.");
            }else {
                return List.of("Issue already exists in the database.");
            }
        }catch (Exception e){
            throw e;
        }
    }

    public List<Issue> readIssues(){
        return issueRepository.findAll();
    }

    @Transactional
    public List<String> updateIssue(Issue issue){
        if (issueRepository.existsById(issue.getId())){
            try {
                List<Issue> issues = issueRepository.findById(issue.getId());
                issues.stream().forEach(s -> {
                    Issue issueToBeUpdate = issueRepository.findById(s.getId()).get(0);
                    issueToBeUpdate.setStatus(issue.getStatus());

                    issueRepository.save(issueToBeUpdate);
                });
                return List.of("Issue record updated.");

            }catch (Exception e){
                throw e;
            }
        }else {
            return List.of("Issue does not exists in the database.");
        }
    }

    @Transactional
    public List<String> deleteIssue(Issue issue){
        if (issueRepository.existsById(issue.getId())){
            try {
                List<Issue> roles = issueRepository.findById(issue.getId());
                roles.stream().forEach(s -> {
                    issueRepository.delete(s);
                });
                return List.of("Issue record deleted successfully.");
            }catch (Exception e){
                throw e;
            }

        }else {
            return List.of("Issue does not exist");
        }
    }
}
