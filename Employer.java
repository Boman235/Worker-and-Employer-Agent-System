package Agenecy;

public interface Employer extends User {
    void postJob(String jobTitle, double gpaRequirement, String department, int numberOfPositions);
}