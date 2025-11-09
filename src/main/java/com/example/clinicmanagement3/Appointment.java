package com.example.clinicmanagement3;

import java.time.LocalDateTime;

public class Appointment {

    private String patientName;
    private String doctor;
    private LocalDateTime appointmentDateTime;
    private String reason;
    private String status;

    public Appointment(String patientName, String doctor, LocalDateTime appointmentDateTime, String reason, String status) {
        this.patientName = patientName;
        this.doctor = doctor;
        this.appointmentDateTime = appointmentDateTime;
        this.reason = reason;
        this.status = status;
    }

    public String getPatientName() { return patientName; }
    public String getDoctor() { return doctor; }
    public LocalDateTime getAppointmentDateTime() { return appointmentDateTime; }
    public String getReason() { return reason; }
    public String getStatus() { return status; }

    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) { this.appointmentDateTime = appointmentDateTime; }
    public void setStatus(String status) { this.status = status; }

    public String getAppointmentDateTimeString() {
        return appointmentDateTime.toString().replace("T", " ");
    }
}
