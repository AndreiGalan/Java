package org.example.Homework;

class Project implements Comparable<Project> {
    private String name;

    public Project(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Project other) {
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
