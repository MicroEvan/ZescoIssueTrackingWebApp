package com.example.demo.issue;

import com.example.demo.issue.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Integer> {

    public boolean existsById(int id);

    public List<Issue> findById(int id);

    @Query("select max(s.id) from Issue s")
    public Integer findMaxId();
}
