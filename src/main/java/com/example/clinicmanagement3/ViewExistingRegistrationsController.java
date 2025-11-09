package com.example.clinicmanagement3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

public class ViewExistingRegistrationsController {

    @FXML private TableView<Patient> patientTable;
    @FXML private TableColumn<Patient, String> nameColumn;
    @FXML private TableColumn<Patient, Integer> ageColumn;
    @FXML private TableColumn<Patient, String> genderColumn;
    @FXML private TableColumn<Patient, String> dateColumn;
    @FXML private TableColumn<Patient, Void> actionColumn;

    @FXML private TextField searchField;
    @FXML private Button searchButton;

    private ObservableList<Patient> patients;
    private FilteredList<Patient> filteredPatients;

    @FXML
    public void initialize() {
        // Setup columns
        nameColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getName()));
        ageColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getAge()).asObject());
        genderColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getGender()));
        dateColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getRegistrationDate()));

        // Load data
        patients = FXCollections.observableArrayList(PatientData.getPatients());
        filteredPatients = new FilteredList<>(patients, p -> true);
        patientTable.setItems(filteredPatients);

        // Add action buttons
        addActionButtonsToTable();
    }

    // --- Search Function ---
    @FXML
    private void handleSearch(ActionEvent event) {
        String searchText = searchField.getText().toLowerCase().trim();
        if (searchText.isEmpty()) {
            filteredPatients.setPredicate(p -> true);
        } else {
            filteredPatients.setPredicate(patient ->
                    patient.getName().toLowerCase().contains(searchText)
            );
        }
    }

    // --- Add Action Buttons to Table ---
    private void addActionButtonsToTable() {
        Callback<TableColumn<Patient, Void>, TableCell<Patient, Void>> cellFactory = (param) -> new TableCell<>() {
            private final Button viewButton = new Button("View");
            private final Button editButton = new Button("Edit");

            {
                viewButton.setStyle("-fx-background-color: #1E88E5; -fx-text-fill: white; -fx-background-radius: 5;");
                editButton.setStyle("-fx-background-color: #43A047; -fx-text-fill: white; -fx-background-radius: 5;");

                viewButton.setOnAction(e -> {
                    Patient patient = getTableView().getItems().get(getIndex());
                    openPatientView(e, patient, false);
                });

                editButton.setOnAction(e -> {
                    Patient patient = getTableView().getItems().get(getIndex());
                    openPatientView(e, patient, true);
                });
            }

            private final HBox pane = new HBox(10, viewButton, editButton);

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(pane);
                }
            }
        };

        actionColumn.setCellFactory(cellFactory);
    }

    // --- View/Edit Page Loader ---
    private void openPatientView(ActionEvent event, Patient patient, boolean editable) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientDetails.fxml"));
            Parent root = loader.load();

            PatientDetailsController controller = loader.getController();
            controller.setPatient(patient, editable);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(editable ? "Edit Patient" : "View Patient");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // --- Back Button ---
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
