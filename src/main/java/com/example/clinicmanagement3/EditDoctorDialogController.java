package com.example.clinicmanagement3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditDoctorDialogController {

    @FXML private TextField daysField;
    @FXML private TextField timeField;
    @FXML private ChoiceBox<String> statusChoice;

    private Doctor doctor;  // reference to the selected doctor
    private boolean saved = false;

    @FXML
    public void initialize() {
        statusChoice.getItems().addAll("On Duty", "On Leave");
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
        daysField.setText(doctor.getAvailableDays());
        timeField.setText(doctor.getTime());
        statusChoice.setValue(doctor.getStatus());
    }

    @FXML
    private void handleSave(ActionEvent event) {
        doctor.setAvailableDays(daysField.getText());
        doctor.setTime(timeField.getText());
        doctor.setStatus(statusChoice.getValue());
        saved = true;

        Stage stage = (Stage) daysField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        Stage stage = (Stage) daysField.getScene().getWindow();
        stage.close();
    }

    public boolean isSaved() {
        return saved;
    }
}
