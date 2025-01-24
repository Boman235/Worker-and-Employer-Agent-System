package Agenecy;

import java.util.ArrayList;
import java.util.List;

public class Company implements Employer, Feedback {
    private String name;
    private List<String> feedbackList;
    private List<Job> jobs; // List to hold job details

    public Company(String name) {
        this.name = name;
        this.feedbackList = new ArrayList<>();
        this.jobs = new ArrayList<>(); // Initialize jobs list
    }

    @Override
    public void postJob(String jobTitle, double gpaRequirement, String department, int numberOfPositions) {
        Job newJob = new Job(jobTitle, gpaRequirement, department, numberOfPositions); // Ensure Job class can handle this
        jobs.add(newJob);
        System.out.println(name + " posted job: " + jobTitle + " with " + numberOfPositions + " positions available.");
    }

    public List<Job> getJobs() {
        return jobs; // Getter for jobs
    }

    @Override
    public void manageWorkers(User user) {
        throw new UnsupportedOperationException("Company cannot manage workers.");
    }

    @Override
    public void manageEmployers(User user) {
        throw new UnsupportedOperationException("Company cannot manage employers.");
    }

    public String getName() {
        return name; // Getter for company name
    }

    @Override
    public void giveFeedback(String feedback) {
        feedbackList.add(feedback); // Add feedback to the list
        System.out.println(name + " gave feedback: " + feedback);
    }
    

    @Override
    public String viewFeedback() {
        return feedbackList.isEmpty() ? "No feedback given." : feedbackList.toString(); // Return feedback or a message
    }

    public boolean isJobAvailableForFreelancer(Freelancer freelancer) {
        // Check if there is a job that matches the freelancer's work type and GPA requirement
        for (Job job : jobs) {
            if (job.getTitle().equalsIgnoreCase(freelancer.getWorkType()) && freelancer.getGpa() >= job.getGpaRequirement()) {
                return true; // Job available if work type matches and GPA requirement is met
            }
        }
        return false; // No matching job found
    }
}