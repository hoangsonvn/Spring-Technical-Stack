package com.example.replication.concurrentcy.completableFuture;

public class Student {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "completableFuture.Student{" +
                "name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }

    private String subject;
}
