package org.example.Bonus;

import java.util.List;

public class Student implements Comparable<Student>{
    private String name;
    private Project assignedProject;
    private List<Project> preferences;

    public Student(String name) {
        this.name = name;
    }

    public void setAssignedProject(Project project)
    {
            this.assignedProject = project;
    }

    public Project getAssignedProject() {
        return assignedProject;
    }

    public void setPreferences(List<Project> preferences) {
        this.preferences = preferences;
    }

    public List<Project> getPreferences() {
        return preferences;
    }

    @Override
    public int compareTo(Student other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

}
