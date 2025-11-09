package com.example.clinicmanagement3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class AddNewPatientController {

    @FXML private TextField fullNameField;
    @FXML private TextField genderField;
    @FXML private TextField ageField;
    @FXML private TextField birthdateField;
    @FXML private TextField addressField;
    @FXML private TextField contactField;
    @FXML private TextField emergencyContactField;

    private String currentUsername;

    public void setUsername(String username) {
        this.currentUsername = username;
    }

    @FXML
    private void handleAddPatient(ActionEvent event) {
        String name = fullNameField.getText();
        String gender = genderField.getText();
        int age = Integer.parseInt(ageField.getText());
        String birthdate = birthdateField.getText();
        String address = addressField.getText();
        String contact = contactField.getText();
        String emergency = emergencyContactField.getText();

        PatientData.addPatient(new Patient(name, age, gender, birthdate, address, contact, emergency));

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

    @FXML
    private void handleBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReceptionistPatientRegistration.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("MediCore - Patient Registration");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
