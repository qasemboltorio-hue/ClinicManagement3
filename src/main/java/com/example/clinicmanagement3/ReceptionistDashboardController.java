package com.example.clinicmanagement3;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Map;

public class ReceptionistDashboardController {

    // --- Navigation buttons ---
    @FXML private Button appointmentsPane;
    @FXML private Button registrationPane;
    @FXML private Button schedulePane;
    @FXML private Button queuePane;
    @FXML private Button billingPane;
    @FXML private Button reportsPane;
    @FXML private Button labResultsPane;

    // --- Sign out button ---
    @FXML private Button signOutButton;

    // --- Charts for Dashboard ---
    @FXML private LineChart<String, Number> patientsLineChart;
    @FXML private PieChart diagnosisPieChart;
    @FXML private LineChart<String, Number> completedAppointmentsChart;

    // --- Store logged-in username (optional) ---
    private String currentUsername;

    public void setUsername(String username) {
        this.currentUsername = username;
    }

    // ==============================
    // 🔹 Initialize Dashboard Charts
    // ==============================
    @FXML
    public void initialize() {
        System.out.println("Charts injected: " + (patientsLineChart != null));
        loadReportData();
    }

    private void loadReportData() {
        try {
            ReportsDataService reportsService = new ReportsDataService();

            // Retrieve data from reports
            Map<String, Number> patientsData = reportsService.getPatientsOverTime();
            Map<String, Number> completedData = reportsService.getCompletedAppointmentsOverTime();
            Map<String, Number> diagnosisData = reportsService.getTopDiagnoses();

            setupPatientsLineChart(patientsData);
            setupDiagnosisPieChart(diagnosisData);
            setupCompletedAppointmentsChart(completedData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupPatientsLineChart(Map<String, Number> data) {
        patientsLineChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Patients");

        data.forEach((date, count) -> series.getData().add(new XYChart.Data<>(date, count)));
        patientsLineChart.getData().add(series);
    }

    private void setupDiagnosisPieChart(Map<String, Number> data) {
        diagnosisPieChart.setData(FXCollections.observableArrayList(
                data.entrySet().stream()
                        .map(entry -> new PieChart.Data(entry.getKey(), entry.getValue().doubleValue()))
                        .toList()
        ));
    }

    private void setupCompletedAppointmentsChart(Map<String, Number> data) {
        completedAppointmentsChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Completed");

        data.forEach((date, count) -> series.getData().add(new XYChart.Data<>(date, count)));
        completedAppointmentsChart.getData().add(series);
    }

    // ==============================
    // 🔹 Navigation Handlers
    // ==============================
    @FXML
    public void handleAppointmentsClick(MouseEvent event) {
        navigateTo(event, "ReceptionistAppointments.fxml", "MediCore - Appointments");
    }

    @FXML
    public void handleRegistrationClick(MouseEvent event) {
        navigateTo(event, "ReceptionistPatientRegistration.fxml", "MediCore - Patient Registration");
    }

    @FXML
    public void handleScheduleClick(MouseEvent event) {
        navigateTo(event, "ReceptionistSchedule.fxml", "MediCore - Doctor Schedule");
    }

    @FXML
    public void handleQueueClick(MouseEvent event) {
        navigateTo(event, "ReceptionistQueue.fxml", "MediCore - Queue Management");
    }

    @FXML
    public void handleBillingClick(MouseEvent event) {
        navigateTo(event, "ReceptionistBilling.fxml", "MediCore - Billing");
    }

    @FXML
    public void handleReportsClick(MouseEvent event) {
        navigateTo(event, "ReceptionistReports.fxml", "MediCore - Reports");
    }

    @FXML
    public void handleLabResultsClick(MouseEvent event) {
        navigateTo(event, "ReceptionistLabResults.fxml", "MediCore - Laboratory Results");
    }

    // ==============================
    // 🔹 Sign Out Handler
    // ==============================
    @FXML
    protected void handleSignOut(ActionEvent event) {
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

    // ==============================
    // 🔹 Helper Method for Navigation
    // ==============================
    private void navigateTo(MouseEvent event, String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            // Pass username only if target controller supports it
            Object controller = loader.getController();
            if (controller instanceof ReceptionistAppointmentsController rac) {
                rac.setUsername(currentUsername);
            } else if (controller instanceof ReceptionistDashboardController rdc) {
                rdc.setUsername(currentUsername);
            }

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
