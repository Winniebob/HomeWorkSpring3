package homebook.springbootlesson3.repository;

import homebook.springbootlesson3.entity.Issue;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;

@Repository
public class IssueRepository {
    private List<Issue> list = new ArrayList<>();

    public void createIssue(Issue issue) {
        list.add(issue);
    }

    public Issue findById(long id) {
        return list.stream().filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public String getInfo() {
        StringBuilder info = new StringBuilder();
        list.forEach(issue -> info.append(issue.getId()).append(": ").append(issue.getDescription()).append("; "));
        return info.toString();
    }

    public void save(Issue issue) {
        list.add(issue);
    }

    public void delete(long id) {
        list.removeIf(issue -> issue.getId() == id);
    }
}
