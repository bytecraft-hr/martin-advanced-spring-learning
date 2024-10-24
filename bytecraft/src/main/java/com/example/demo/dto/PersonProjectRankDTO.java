package com.example.demo.dto;

public class PersonProjectRankDTO {
    private String firstName;
    private String lastName;
    private int projectCount;
    private double percentRank;

    // Getteri i setteri
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(int projectCount) {
        this.projectCount = projectCount;
    }

    public double getPercentRank() {
        return percentRank;
    }

    public void setPercentRank(double percentRank) {
        this.percentRank = percentRank;
    }
}