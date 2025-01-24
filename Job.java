package Agenecy;

public class Job {
    private String title;
    private double gpaRequirement;
    private String department;
    private int numberOfPositions;

    public Job(String title, double gpaRequirement, String department, int numberOfPositions) {
        this.title = title;
        this.gpaRequirement = gpaRequirement;
        this.department = department;
        this.numberOfPositions = numberOfPositions;
    }

    public String getTitle() {
        return title;
    }

    public double getGpaRequirement() {
        return gpaRequirement;
    }

    public String getDepartment() {
        return department;
    }

    public int getNumberOfPositions() {
        return numberOfPositions;
    }

    public void setNumberOfPositions(int numberOfPositions) { // Setter method
        this.numberOfPositions = numberOfPositions;
    }
}