package com.example.clinicmanagement3;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ReceptionistAppointmentDetailsController {

    @FXML private Label patientLabel;
    @FXML private Label contactLabel;
    @FXML private Label appointmentLabel;
    @FXML private Label reasonLabel;
    @FXML private Label doctorLabel;
    @FXML private Label statusLabel;

    private ReceptionistAppointmentsController.Appointment appointment;
    private ReceptionistAppointmentsController parentController;

    public void setAppointment(ReceptionistAppointmentsController.Appointment appointment,
                               ReceptionistAppointmentsController parentController) {
        this.appointment = appointment;
        this.parentController = parentController;

        patientLabel.setText("Patient: " + appointment.getPatient());
        appointmentLabel.setText("Appointment: " + appointment.getTime());
        reasonLabel.setText("Reason: " + appointment.getReason());
        doctorLabel.setText("Doctor: " + appointment.getDoctor());
        statusLabel.setText("Status: " + appointment.getStatus());
    }

    @FXML
    private void handleBack() {
        ((Stage) patientLabel.getScene().getWindow()).close();
    }

    @FXML
    private void handleReschedule() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Reschedule Appointment");
        dialog.setHeaderText("Enter new date and time:");
        dialog.setContentText("New Date & Time:");

        dialog.showAndWait().ifPresent(newTime -> {
            boolean conflict = parentController.getAppointments().stream()
                    .anyMatch(a -> a.getTime().equalsIgnoreCase(newTime));

            if (conflict) {
                showAlert("Conflict", "There is already an appointment scheduled for " + newTime);
            } else {
                parentController.updateAppointmentTime(appointment, newTime);
                appointmentLabel.setText("Appointment: " + newTime);
            }
        });
    }

    @FXML
    private void handleCancel() {
        parentController.deleteAppointment(appointment);
        ((Stage) patientLabel.getScene().getWindow()).close();
    }

    @FXML
    private void handleMarkComplete() {
        parentController.markAppointmentComplete(appointment);
        statusLabel.setText("Status: Complete");
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
