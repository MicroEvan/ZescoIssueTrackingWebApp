package com.example.demo.issue;

import com.example.demo.issue.Issue;
import com.example.demo.issue.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class IssueController {

    @Autowired
    private IssueService issueService;

    @RequestMapping(path = "issues/info", method = RequestMethod.GET)
    public String info(){
        return "The Issues application is up...";
    }

    @RequestMapping(path = "issues/create", method = RequestMethod.POST)
    public List<String> createIssue(@RequestBody Issue issue){
        return issueService.createIssue(issue);
    }

    @RequestMapping(path = "issues/read", method = RequestMethod.GET)
    public List<Issue> readIssues(){
        return issueService.readIssues();
    }

    @RequestMapping(path = "issues/update", method = RequestMethod.PUT)
    public List<String> updateIssue(@RequestBody Issue issue){
        return issueService.updateIssue(issue);
    }

    @RequestMapping(path = "issues/delete", method = RequestMethod.DELETE)
    public List<String> deleteIssue(@RequestBody Issue issue){
        return issueService.deleteIssue(issue);
    }
}
