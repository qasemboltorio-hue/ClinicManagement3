package com.example.clinicmanagement3;

import javafx.beans.property.*;
import javafx.scene.control.Button;

public class PatientQueue {
    private final IntegerProperty queueNo;
    private final StringProperty patientName;
    private final StringProperty appointmentTime;
    private final StringProperty status;
    private final ObjectProperty<Button> actionButton;

    public PatientQueue(int queueNo, String patientName, String appointmentTime, String status) {
        this.queueNo = new SimpleIntegerProperty(queueNo);
        this.patientName = new SimpleStringProperty(patientName);
        this.appointmentTime = new SimpleStringProperty(appointmentTime);
        this.status = new SimpleStringProperty(status);

        Button callInButton = new Button("Call In");
        callInButton.setStyle("-fx-background-color: #4A90E2; -fx-text-fill: white; -fx-font-weight: bold;");
        callInButton.setOnAction(e -> handleCallIn());

        this.actionButton = new SimpleObjectProperty<>(callInButton);
    }

    private void handleCallIn() {
        if (status.get().equals("Waiting")) {
            status.set("Calling");
        } else {
            status.set("Waiting");
        }
    }

    // Getters for properties
    public IntegerProperty queueNoProperty() { return queueNo; }
    public StringProperty patientNameProperty() { return patientName; }
    public StringProperty appointmentTimeProperty() { return appointmentTime; }
    public StringProperty statusProperty() { return status; }
    public ObjectProperty<Button> actionButtonProperty() { return actionButton; }
}
