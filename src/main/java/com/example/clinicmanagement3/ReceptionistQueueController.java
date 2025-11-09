package com.example.clinicmanagement3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class ReceptionistQueueController {

    @FXML private TableView<PatientQueue> queueTable;
    @FXML private TableColumn<PatientQueue, Integer> queueNoColumn;
    @FXML private TableColumn<PatientQueue, String> patientNameColumn;
    @FXML private TableColumn<PatientQueue, String> appointmentTimeColumn;
    @FXML private TableColumn<PatientQueue, String> statusColumn;
    @FXML private TableColumn<PatientQueue, Button> actionColumn;
    @FXML private Button backButton;
    @FXML private Button signOutButton;

    private ObservableList<PatientQueue> queueList;

    @FXML
    public void initialize() {
        queueNoColumn.setCellValueFactory(cellData -> cellData.getValue().queueNoProperty().asObject());
        patientNameColumn.setCellValueFactory(cellData -> cellData.getValue().patientNameProperty());
        appointmentTimeColumn.setCellValueFactory(cellData -> cellData.getValue().appointmentTimeProperty());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        actionColumn.setCellValueFactory(cellData -> cellData.getValue().actionButtonProperty());

        loadQueueData();
    }

    private void loadQueueData() {
        queueList = FXCollections.observableArrayList(
                new PatientQueue(1, "Sophia Aguila", "10:30 AM", "Waiting"),
                new PatientQueue(2, "Ms. Tuberculosis", "11:30 AM", "Waiting")
        );

        queueTable.setItems(queueList);
    }

    @FXML
    private void handleBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("receptionist_dashboard.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("MediCore - Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSignOut(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Signup.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("MediCore - Sign In");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
