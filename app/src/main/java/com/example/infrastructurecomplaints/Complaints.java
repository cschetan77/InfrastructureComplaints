package com.example.infrastructurecomplaints;

public class Complaints {
    public String subject;
    public String description;
    public String user;
    public Complaints(String subject,String description,String user) {
        this.subject = subject;
        this.description = description;
        this.user = user;
    }
}
