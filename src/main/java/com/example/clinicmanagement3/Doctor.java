package com.example.clinicmanagement3;

public class Doctor {
    private String doctor;
    private String specialty;
    private String availableDays;
    private String time;
    private String status;

    public Doctor(String doctor, String specialty, String availableDays, String time, String status) {
        this.doctor = doctor;
        this.specialty = specialty;
        this.availableDays = availableDays;
        this.time = time;
        this.status = status;
    }

    public String getDoctor() { return doctor; }
    public void setDoctor(String doctor) { this.doctor = doctor; }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    public String getAvailableDays() { return availableDays; }
    public void setAvailableDays(String availableDays) { this.availableDays = availableDays; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
