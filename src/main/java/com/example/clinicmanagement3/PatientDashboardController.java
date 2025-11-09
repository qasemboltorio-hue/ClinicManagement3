// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package com.example.clinicmanagement3;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PatientDashboardController {
    @FXML
    private Button appointmentButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button viewAppointmentsButton;
    @FXML
    private Label nameLabel;
    @FXML
    private Label ageLabel;
    @FXML
    private Label genderLabel;
    @FXML
    private Label contactLabel;
    @FXML
    private Label emergencyLabel;
    @FXML
    private Label greetingLabel;
    private String currentUsername;

    public PatientDashboardController() {
    }

    public void setUsername(String username) {
        this.currentUsername = username;
        System.out.println("Dashboard received username: " + username);
        this.loadPatientGreeting();
        this.loadPatientInfo();
    }

    @FXML
    public void handleViewAppointments(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("ViewAppointments.fxml"));
            Parent root = (Parent)loader.load();
            ViewAppointmentsController controller = (ViewAppointmentsController)loader.getController();
            controller.setUsername(this.currentUsername);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Clinic Management");
            stage.show();
        } catch (IOException var6) {
            var6.printStackTrace();
        }

    }

    @FXML
    private void handleLabResults(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("LabResult.fxml"));
            Parent root = (Parent)loader.load();
            LabResultController controller = (LabResultController)loader.getController();
            controller.setUsername(this.currentUsername);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Clinic Management");
            stage.show();
            Stage currentStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException var7) {
            var7.printStackTrace();
        }

    }

    @FXML
    protected void handleTreatmentPlanClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("TreatmentPlan.fxml"));
            Parent root = (Parent)loader.load();
            TreatmentPlanController controller = (TreatmentPlanController)loader.getController();
            controller.setUsername(this.currentUsername);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Clinic Management");
            stage.show();
        } catch (IOException var6) {
            var6.printStackTrace();
        }

    }

    @FXML
    protected void handleAppointmentClick() {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PatientAppointment.fxml"));
            Parent appointmentRoot = (Parent)loader.load();
            PatientAppointmentController appointmentController = (PatientAppointmentController)loader.getController();
            appointmentController.setUsername(this.currentUsername);
            Stage stage = (Stage)this.appointmentButton.getScene().getWindow();
            stage.setScene(new Scene(appointmentRoot));
            stage.setTitle("Clinic Management");
            stage.show();
        } catch (IOException var5) {
            var5.printStackTrace();
        }

    }

    @FXML
    protected void handleLogoutClick() {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Signup.fxml"));
            Parent signupRoot = (Parent)loader.load();
            Stage stage = (Stage)this.logoutButton.getScene().getWindow();
            stage.setScene(new Scene(signupRoot));
            stage.setTitle("Clinic Management");
            stage.show();
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }

    private void loadPatientInfo() {
        try {
            Connection conn = DBConnection.getConnection();

            try {
                String sql = "SELECT full_name, age, gender, contact_number, emergency_contact FROM user WHERE username = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, this.currentUsername);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    this.nameLabel.setText(rs.getString("full_name"));
                    this.ageLabel.setText(rs.getString("age"));
                    this.genderLabel.setText(rs.getString("gender"));
                    this.contactLabel.setText(rs.getString("contact_number"));
                    this.emergencyLabel.setText(rs.getString("emergency_contact"));
                } else {
                    this.nameLabel.setText("Unknown");
                    this.ageLabel.setText("-");
                    this.genderLabel.setText("-");
                    this.contactLabel.setText("-");
                    this.emergencyLabel.setText("-");
                }
            } catch (Throwable var6) {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Throwable var5) {
                        var6.addSuppressed(var5);
                    }
                }

                throw var6;
            }

            if (conn != null) {
                conn.close();
            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }

    private void loadPatientGreeting() {
        try {
            Connection conn = DBConnection.getConnection();

            try {
                String sql = "SELECT full_name FROM user WHERE username = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, this.currentUsername);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("full_name");
                    this.greetingLabel.setText("Welcome, " + fullName + "!");
                } else {
                    this.greetingLabel.setText("Welcome!");
                }
            } catch (Throwable var7) {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Throwable var6) {
                        var7.addSuppressed(var6);
                    }
                }

                throw var7;
            }

            if (conn != null) {
                conn.close();
            }
        } catch (Exception var8) {
            this.greetingLabel.setText("Welcome!");
            var8.printStackTrace();
        }

    }
}
