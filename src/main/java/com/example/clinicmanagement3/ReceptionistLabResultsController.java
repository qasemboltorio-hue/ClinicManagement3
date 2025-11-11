package com.example.clinicmanagement3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.sql.*;
import java.io.File;
import java.io.IOException;

public class ReceptionistLabResultsController {

    @FXML private TextField searchField;
    @FXML private TableView<LabResult> labResultsTable;
    @FXML private TableColumn<LabResult, String> colService;
    @FXML private TableColumn<LabResult, String> colRequestedBy;
    @FXML private TableColumn<LabResult, String> colPatientName;
    @FXML private TableColumn<LabResult, String> colDateRequest;
    @FXML private TableColumn<LabResult, String> colDateRelease;
    @FXML private TableColumn<LabResult, String> colStatus;
    @FXML private TableColumn<LabResult, Button> colAction;

    private ObservableList<LabResult> labResultsList;

    @FXML
    public void initialize() {
        // Sample data
        labResultsList = FXCollections.observableArrayList(
                new LabResult("Complete Blood Count (CBC)", "(example only)", "(example only)", "October 16, 2025", "October 18, 2025", "Released")
        );

        // Setup columns
        colService.setCellValueFactory(data -> data.getValue().serviceProperty());
        colRequestedBy.setCellValueFactory(data -> data.getValue().requestedByProperty());
        colPatientName.setCellValueFactory(data -> data.getValue().patientNameProperty());
        colDateRequest.setCellValueFactory(data -> data.getValue().dateRequestProperty());
        colDateRelease.setCellValueFactory(data -> data.getValue().dateReleaseProperty());
        colStatus.setCellValueFactory(data -> data.getValue().statusProperty());
        colAction.setCellValueFactory(data -> data.getValue().actionButtonProperty());

        labResultsTable.setItems(labResultsList);

        // Search filter
        searchField.textProperty().addListener((obs, oldValue, newValue) -> {
            labResultsTable.setItems(
                    FXCollections.observableArrayList(
                            labResultsList.filtered(result ->
                                    result.getPatientName().toLowerCase().contains(newValue.toLowerCase()))
                    )
            );
        });
    }

    // Handle back button to go to dashboard
    @FXML
    private void handleBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("receptionist_dashboard.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("MediCore - Dashboard");
        stage.show();
    }

    // Sign out
    @FXML
    private void handleSignOut(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Signup.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("MediCore - Sign In");
        stage.show();
    }

    // ---- Inner Class Model ----
    public static class LabResult {
        private final javafx.beans.property.SimpleStringProperty service;
        private final javafx.beans.property.SimpleStringProperty requestedBy;
        private final javafx.beans.property.SimpleStringProperty patientName;
        private final javafx.beans.property.SimpleStringProperty dateRequest;
        private final javafx.beans.property.SimpleStringProperty dateRelease;
        private final javafx.beans.property.SimpleStringProperty status;
        private final javafx.beans.property.SimpleObjectProperty<Button> actionButton;

        public LabResult(String service, String requestedBy, String patientName, String dateRequest, String dateRelease, String status) {
            this.service = new javafx.beans.property.SimpleStringProperty(service);
            this.requestedBy = new javafx.beans.property.SimpleStringProperty(requestedBy);
            this.patientName = new javafx.beans.property.SimpleStringProperty(patientName);
            this.dateRequest = new javafx.beans.property.SimpleStringProperty(dateRequest);
            this.dateRelease = new javafx.beans.property.SimpleStringProperty(dateRelease);
            this.status = new javafx.beans.property.SimpleStringProperty(status);

            Button uploadButton = new Button("Upload Result");
            uploadButton.setStyle("-fx-background-color: #4A90E2; -fx-text-fill: white; -fx-background-radius: 10;");
            uploadButton.setOnAction(e -> openFileChooser());

            this.actionButton = new javafx.beans.property.SimpleObjectProperty<>(uploadButton);
        }

        private void openFileChooser() {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Lab Result Image");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
            );
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                System.out.println("Uploaded: " + selectedFile.getAbsolutePath());
                // You can save the file or link to the record here
            }
        }

        // Property getters
        public javafx.beans.property.StringProperty serviceProperty() { return service; }
        public javafx.beans.property.StringProperty requestedByProperty() { return requestedBy; }
        public javafx.beans.property.StringProperty patientNameProperty() { return patientName; }
        public javafx.beans.property.StringProperty dateRequestProperty() { return dateRequest; }
        public javafx.beans.property.StringProperty dateReleaseProperty() { return dateRelease; }
        public javafx.beans.property.StringProperty statusProperty() { return status; }
        public javafx.beans.property.ObjectProperty<Button> actionButtonProperty() { return actionButton; }

        public String getPatientName() { return patientName.get(); }
    }
}
