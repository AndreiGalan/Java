package org.example;

public class Student implements Comparable<Student> {
    public String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Student other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
