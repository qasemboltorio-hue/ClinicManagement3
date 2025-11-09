package com.example.clinicmanagement3;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Invoice {
    private final StringProperty invoiceNo;
    private final StringProperty patient;
    private final StringProperty doctor;
    private final StringProperty service;
    private final StringProperty amount;
    private final StringProperty status;

    public Invoice(String invoiceNo, String patient, String doctor, String service, String amount, String status) {
        this.invoiceNo = new SimpleStringProperty(invoiceNo);
        this.patient = new SimpleStringProperty(patient);
        this.doctor = new SimpleStringProperty(doctor);
        this.service = new SimpleStringProperty(service);
        this.amount = new SimpleStringProperty(amount);
        this.status = new SimpleStringProperty(status);
    }

    public StringProperty invoiceNoProperty() { return invoiceNo; }
    public StringProperty patientProperty() { return patient; }
    public StringProperty doctorProperty() { return doctor; }
    public StringProperty serviceProperty() { return service; }
    public StringProperty amountProperty() { return amount; }
    public StringProperty statusProperty() { return status; }

    public String getAmount() { return amount.get(); }
}
