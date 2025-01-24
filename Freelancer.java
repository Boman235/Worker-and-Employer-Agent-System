package Agenecy;

import java.util.ArrayList;
import java.util.List;

public class Freelancer implements Worker, Feedback {
    private String name;
    private String workType;
    private int age;
    private double gpa;
    private String department;
    private String email;
    private String sex;
    private List<String> feedbackList;

    public Freelancer(String name) {
        this.name = name;
        this.feedbackList = new ArrayList<>();
    }

    @Override
    public void applyForJob(String jobTitle) {
        System.out.println(name + " applied for job: " + jobTitle);
    }

    @Override
    public void manageWorkers(User user) {
        throw new UnsupportedOperationException("Freelancer cannot manage workers.");
    }

    @Override
    public void manageEmployers(User user) {
        throw new UnsupportedOperationException("Freelancer cannot manage employers.");
    }

    public String getName() {
        return name;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public int getAge() {
        return age; // Getter for age
    }

    public void setAge(int age) {
        this.age = age; // Setter for age
    }

    public double getGpa() {
        return gpa; // Getter for GPA
    }

    public void setGpa(double gpa) {
        this.gpa = gpa; // Setter for GPA
    }

    public String getDepartment() {
        return department; // Getter for department
    }

    public void setDepartment(String department) {
        this.department = department; // Setter for department
    }

    public String getEmail() {
        return email; // Getter for email
    }

    public void setEmail(String email) {
        this.email = email; // Setter for email
    }

    public String getSex() {
        return sex; // Getter for sex
    }

    public void setSex(String sex) {
        this.sex = sex; // Setter for sex
    }

    @Override
    public void giveFeedback(String feedback) {
        feedbackList.add(feedback);
        System.out.println(name + " gave feedback: " + feedback);
    }

    @Override
    public String viewFeedback() {
        return feedbackList.isEmpty() ? "No feedback given." : feedbackList.toString();
    }
    
}