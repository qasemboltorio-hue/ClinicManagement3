package com.example.clinicmanagement3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.sql.*;

import java.io.IOException;

public class ReceptionistAppointmentsController {

    // Table and columns
    @FXML private TableView<Appointment> appointmentsTable;
    @FXML private TableColumn<Appointment, String> timeColumn;
    @FXML private TableColumn<Appointment, String> patientColumn;
    @FXML private TableColumn<Appointment, String> reasonColumn;
    @FXML private TableColumn<Appointment, String> doctorColumn;
    @FXML private TableColumn<Appointment, String> statusColumn;

    // Form fields
    @FXML private TextField patientNameField;
    @FXML private TextField dateTimeField;
    @FXML private TextField contactField;
    @FXML private TextField reasonField;

    // Buttons
    @FXML private Button addButton;
    @FXML private Button backButton;
    @FXML private Button signOutButton;

    // Data
    private final ObservableList<Appointment> appointments = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Bind table columns
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        patientColumn.setCellValueFactory(new PropertyValueFactory<>("patient"));
        reasonColumn.setCellValueFactory(new PropertyValueFactory<>("reason"));
        doctorColumn.setCellValueFactory(new PropertyValueFactory<>("doctor"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        appointmentsTable.setItems(appointments);

        // ------------------------
        // Clickable patient names
        // ------------------------
        patientColumn.setCellFactory(col -> new TableCell<>() {
            private final Hyperlink link = new Hyperlink();

            {
                link.setUnderline(true);
                link.setOnAction(e -> {
                    Appointment appointment = getTableView().getItems().get(getIndex());
                    openAppointmentDetails(appointment);
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    link.setText(item);
                    setGraphic(link);
                }
            }
        });

        // ------------------------
        // Status color formatting
        // ------------------------
        statusColumn.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    switch (item.toLowerCase()) {
                        case "confirmed" -> setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
                        case "cancelled" -> setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                        case "complete" -> setStyle("-fx-text-fill: blue; -fx-font-weight: bold;");
                        default -> setStyle("-fx-text-fill: black;");
                    }
                }
            }
        });
    }

    // -----------------------------
    // Add Appointment button logic
    // -----------------------------
    @FXML
    private void handleAddAppointment() {
        String name = patientNameField.getText().trim();
        String dateTime = dateTimeField.getText().trim();
        String contact = contactField.getText().trim();
        String reason = reasonField.getText().trim();

        if (name.isEmpty() || dateTime.isEmpty() || reason.isEmpty()) {
            showAlert("Missing Information", "Please fill in all required fields.");
            return;
        }

        // Check for duplicate date/time
        for (Appointment appt : appointments) {
            if (appt.getTime().equalsIgnoreCase(dateTime)) {
                showAlert("Duplicate Appointment",
                        "There is already an appointment scheduled for " + dateTime + ".");
                return;
            }
        }

        // Add new appointment
        Appointment newAppt = new Appointment(dateTime, name, reason, "Unassigned", "Confirmed");
        appointments.add(newAppt);
        clearForm();

        showAlert("Appointment Added", "Appointment for " + name + " has been added successfully!");
    }

    @FXML
    private void handleBack() {
        System.out.println("Back button clicked — return to dashboard.");
    }

    @FXML
    private void handleSignOut() {
        System.out.println("Sign Out clicked — redirect to login screen.");
    }

    // -----------------------------
    // Appointment management methods
    // -----------------------------
    public ObservableList<Appointment> getAppointments() {
        return appointments;
    }

    public void updateAppointmentTime(Appointment appt, String newTime) {
        appointments.remove(appt);
        Appointment updated = new Appointment(newTime, appt.getPatient(), appt.getReason(), appt.getDoctor(), appt.getStatus());
        appointments.add(updated);
    }

    public void deleteAppointment(Appointment appt) {
        appointments.remove(appt);
    }

    public void markAppointmentComplete(Appointment appt) {
        appointments.remove(appt);
        Appointment updated = new Appointment(appt.getTime(), appt.getPatient(), appt.getReason(), appt.getDoctor(), "Complete");
        appointments.add(updated);
    }

    // -----------------------------
    // Open detail view
    // -----------------------------
    private void openAppointmentDetails(Appointment appointment) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReceptionistAppointmentDetails.fxml"));
            Parent root = loader.load();

            ReceptionistAppointmentDetailsController controller = loader.getController();
            controller.setAppointment(appointment, this);

            Stage stage = new Stage();
            stage.setTitle("Appointment Details");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // -----------------------------
    // Utility methods
    // -----------------------------
    private void clearForm() {
        patientNameField.clear();
        dateTimeField.clear();
        contactField.clear();
        reasonField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setUsername(String currentUsername) {}

    // -----------------------------
    // Appointment Model
    // -----------------------------
    public static class Appointment {
        private final String time;
        private final String patient;
        private final String reason;
        private final String doctor;
        private final String status;

        public Appointment(String time, String patient, String reason, String doctor, String status) {
            this.time = time;
            this.patient = patient;
            this.reason = reason;
            this.doctor = doctor;
            this.status = status;
        }

        public String getTime() { return time; }
        public String getPatient() { return patient; }
        public String getReason() { return reason; }
        public String getDoctor() { return doctor; }
        public String getStatus() { return status; }
    }
}
