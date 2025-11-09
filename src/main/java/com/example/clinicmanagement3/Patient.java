package com.example.clinicmanagement3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Patient {
    private String name;
    private int age;
    private String gender;
    private String birthdate;
    private String address;
    private String contact;
    private String emergencyContact;
    private String registrationDate;

    public Patient(String name, int age, String gender, String birthdate, String address, String contact, String emergencyContact) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.birthdate = birthdate;
        this.address = address;
        this.contact = contact;
        this.emergencyContact = emergencyContact;
        this.registrationDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
    }

    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getRegistrationDate() { return registrationDate; }
    // Add these to Patient.java
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setGender(String gender) { this.gender = gender; }
    public void setBirthdate(String birthdate) { this.birthdate = birthdate; }
    public void setAddress(String address) { this.address = address; }
    public void setContact(String contact) { this.contact = contact; }
    public void setEmergencyContact(String emergencyContact) { this.emergencyContact = emergencyContact; }

    public String getBirthdate() { return birthdate; }
    public String getAddress() { return address; }
    public String getContact() { return contact; }
    public String getEmergencyContact() { return emergencyContact; }

}
