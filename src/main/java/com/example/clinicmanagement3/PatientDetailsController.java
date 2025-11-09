package com.example.clinicmanagement3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class PatientDetailsController {

    @FXML private TextField fullNameField;
    @FXML private TextField genderField;
    @FXML private TextField ageField;
    @FXML private TextField birthdateField;
    @FXML private TextField addressField;
    @FXML private TextField contactField;
    @FXML private TextField emergencyContactField;

    private Patient currentPatient;
    private boolean isEditable;

    // Called from the previous controller
    public void setPatient(Patient patient, boolean editable) {
        this.currentPatient = patient;
        this.isEditable = editable;

        // Populate fields
        fullNameField.setText(patient.getName());
        genderField.setText(patient.getGender());
        ageField.setText(String.valueOf(patient.getAge()));
        birthdateField.setText(patient.getBirthdate());
        addressField.setText(patient.getAddress());
        contactField.setText(patient.getContact());
        emergencyContactField.setText(patient.getEmergencyContact());

        // If view mode, disable all fields
        if (!editable) {
            fullNameField.setEditable(false);
            genderField.setEditable(false);
            ageField.setEditable(false);
            birthdateField.setEditable(false);
            addressField.setEditable(false);
            contactField.setEditable(false);
            emergencyContactField.setEditable(false);
        }
    }

    @FXML
    private void handleSave(ActionEvent event) {
        if (!isEditable) return;

        try {
            currentPatient.setName(fullNameField.getText());
            currentPatient.setGender(genderField.getText());
            currentPatient.setAge(Integer.parseInt(ageField.getText()));
            currentPatient.setBirthdate(birthdateField.getText());
            currentPatient.setAddress(addressField.getText());
            currentPatient.setContact(contactField.getText());
            currentPatient.setEmergencyContact(emergencyContactField.getText());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Patient Updated");
            alert.setHeaderText(null);
            alert.setContentText("Patient details updated successfully!");
            alert.showAndWait();

            goBack(event);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Please check your entered data.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        goBack(event);
    }

    private void goBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewExistingRegistrations.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("MediCore - Existing Registrations");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
