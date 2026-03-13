
import model.Job;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
public class JobController {

    private ArrayList<Job> jobsList;

    @PostConstruct
    public void init() {
        Job job1 = new Job("1", "Software Engineer", "Develop software applications", LocalDate.now());
        Job job2 = new Job("2", "Data Scientist", "Analyze data and develop models", LocalDate.now());
        jobsList = new ArrayList<Job>();
        jobsList.add(job1);
        jobsList.add(job2);
    }

    @GetMapping("/jobs")
    public ArrayList<Job> getJobs() {
        for (Job job : jobsList) {
            job.setLastAccessDate(LocalDateTime.now());
            job.addNumViews();
        }
        return jobsList;
    }
}
