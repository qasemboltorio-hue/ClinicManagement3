package com.example.clinicmanagement3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class ReceptionistReportsController {

    @FXML
    private StackPane chartArea;

    // ====================================================
    // 🔹 HANDLERS for each analytics button
    // ====================================================

    @FXML
    private void showTotalPatients() {
        LineChart<String, Number> lineChart = createLineChart("Monthly Total Patients", "Month", "Patients");
        lineChart.getData().add(createSampleSeries("Monthly Average Patients",
                new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul"},
                new double[]{65, 60, 80, 81, 56, 55, 40}));
        chartArea.getChildren().setAll(lineChart);
    }

    @FXML
    private void showCompletedAppointments() {
        BarChart<String, Number> barChart = createBarChart("Completed Appointments", "Month", "Appointments");
        barChart.getData().add(createSampleSeries("Completed",
                new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun"},
                new double[]{120, 140, 130, 150, 160, 155}));
        chartArea.getChildren().setAll(barChart);
    }

    @FXML
    private void showNewPatients() {
        PieChart pieChart = new PieChart();
        pieChart.setTitle("New Patients by Department");
        pieChart.getData().addAll(
                new PieChart.Data("Pediatrics", 25),
                new PieChart.Data("General Practitioner", 35),
                new PieChart.Data("Obstetrician", 20),
                new PieChart.Data("Family Doctor", 10)
        );
        chartArea.getChildren().setAll(pieChart);
    }

    @FXML
    private void showTopDiagnoses() {
        BarChart<String, Number> barChart = createBarChart("Top 5 Diagnoses", "Diagnosis", "Count");
        barChart.getData().add(createSampleSeries("Frequency",
                new String[]{"Flu", "Diabetes", "Hypertension", "Asthma", "Allergy"},
                new double[]{50, 45, 40, 30, 25}));
        chartArea.getChildren().setAll(barChart);
    }

    @FXML
    private void showPrescriptions() {
        LineChart<String, Number> lineChart = createLineChart("Prescriptions Issued", "Month", "Count");
        lineChart.getData().add(createSampleSeries("Prescriptions",
                new String[]{"Jan", "Feb", "Mar", "Apr", "May"},
                new double[]{100, 110, 120, 130, 140}));
        chartArea.getChildren().setAll(lineChart);
    }

    @FXML
    private void showRevenue() {
        LineChart<String, Number> lineChart = createLineChart("Monthly Total Revenue", "Month", "Revenue ($)");
        lineChart.getData().add(createSampleSeries("Revenue",
                new String[]{"Jan", "Feb", "Mar", "Apr", "May"},
                new double[]{5000, 7000, 9000, 8500, 9500}));
        chartArea.getChildren().setAll(lineChart);
    }

    // ====================================================
    // 🔹 NAVIGATION HANDLERS
    // ====================================================

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

    // ====================================================
    // 🔧 HELPER METHODS (Charts)
    // ====================================================

    private LineChart<String, Number> createLineChart(String title, String xLabel, String yLabel) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel(xLabel);
        yAxis.setLabel(yLabel);
        LineChart<String, Number> chart = new LineChart<>(xAxis, yAxis);
        chart.setTitle(title);
        chart.setLegendVisible(true);
        chart.setAnimated(true);
        return chart;
    }

    private BarChart<String, Number> createBarChart(String title, String xLabel, String yLabel) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel(xLabel);
        yAxis.setLabel(yLabel);
        BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
        chart.setTitle(title);
        chart.setLegendVisible(true);
        chart.setAnimated(true);
        return chart;
    }

    private XYChart.Series<String, Number> createSampleSeries(String name, String[] xValues, double[] yValues) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(name);
        for (int i = 0; i < xValues.length; i++) {
            series.getData().add(new XYChart.Data<>(xValues[i], yValues[i]));
        }
        return series;
    }
}
