package com.example.clinicmanagement3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ReceptionistPatientRegistrationController {

    @FXML
    private Button addNewPatientButton;
    @FXML
    private Button viewExistingRegistrationsButton;
    @FXML
    private Button backButton;

    private String currentUsername;

    public void setUsername(String username) {
        this.currentUsername = username;
    }

    @FXML
    private void handleAddNewPatient(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddNewPatient.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("MediCore - Add New Patient");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleViewExistingRegistrations(ActionEvent event) {
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("receptionist_dashboard.fxml"));
            Parent root = loader.load();

            ReceptionistDashboardController controller = loader.getController();
            controller.setUsername(currentUsername);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("MediCore - Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


