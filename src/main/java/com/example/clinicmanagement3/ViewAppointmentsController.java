// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package com.example.clinicmanagement3;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ViewAppointmentsController implements Initializable {
    @FXML
    private TableView<Appointment> appointmentTable;
    @FXML
    private TableColumn<Appointment, String> dateColumn;
    @FXML
    private TableColumn<Appointment, String> timeColumn;
    @FXML
    private TableColumn<Appointment, String> serviceColumn;
    @FXML
    private TableColumn<Appointment, Void> actionColumn;
    @FXML
    private Button logoutButton;
    @FXML
    private Button backButton;
    private final ObservableList<Appointment> appointments = FXCollections.observableArrayList();
    private String currentUsername;

    public ViewAppointmentsController() {
    }

    public void setUsername(String username) {
        this.currentUsername = username;
        System.out.println("Appointments received username: " + username);
        this.loadAppointments();
    }

    public void initialize(URL location, ResourceBundle resources) {
        this.dateColumn.setCellValueFactory(new PropertyValueFactory("date"));
        this.timeColumn.setCellValueFactory(new PropertyValueFactory("time"));
        this.serviceColumn.setCellValueFactory(new PropertyValueFactory("service"));
        this.addActionButtonsToTable();
    }

    private void loadAppointments() {
        this.appointments.clear();

        try {
            Connection conn = DBConnection.getConnection();

            try {
                String sql = "    SELECT a.appointment_date, a.appointment_time, a.service, u.username, u.full_name\n    FROM appointments a\n    JOIN user u ON a.user_id = u.id\n    WHERE u.username = ?\n    ORDER BY a.appointment_date ASC, a.appointment_time ASC\n";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, this.currentUsername);
                ResultSet rs = stmt.executeQuery();

                while(true) {
                    if (!rs.next()) {
                        this.appointmentTable.setItems(this.appointments);
                        break;
                    }

                    this.appointments.add(new Appointment(rs.getString("appointment_date"), rs.getString("appointment_time"), rs.getString("service"), rs.getString("username"), rs.getString("full_name")));
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
        } catch (SQLException var7) {
            System.err.println("SQL Error: " + var7.getMessage());
            var7.printStackTrace();
        }

    }

    private void addActionButtonsToTable() {
        Callback<TableColumn<Appointment, Void>, TableCell<Appointment, Void>> cellFactory = (param) -> {
            return new 1(this);
        };
        this.actionColumn.setCellFactory(cellFactory);
    }

    private void handleCancel(Appointment appointment) {
        try {
            Connection conn = DBConnection.getConnection();

            try {
                String sql = "DELETE FROM appointments\nWHERE appointment_date = ? AND appointment_time = ? AND user_id = (\nSELECT id FROM user WHERE username = ?\n    )\n";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, appointment.getDate());
                stmt.setString(2, appointment.getTime());
                stmt.setString(3, appointment.getUsername());
                stmt.executeUpdate();
                this.appointments.remove(appointment);
                this.showAlert("Success", "Appointment cancelled.");
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
        } catch (SQLException var7) {
            this.showAlert("Error", "Failed to cancel appointment.");
            var7.printStackTrace();
        }

    }

    private void handleReschedule(Appointment appointment) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PatientAppointment.fxml"));
            Parent root = (Parent)loader.load();
            PatientAppointmentController controller = (PatientAppointmentController)loader.getController();
            controller.setUsername(this.currentUsername);
            controller.loadAppointmentForReschedule(appointment);
            Stage stage = (Stage)this.appointmentTable.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Clinic Management");
            stage.show();
        } catch (IOException var6) {
            var6.printStackTrace();
            this.showAlert("Error", "Failed to load reschedule screen.");
        }

    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText((String)null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    protected void handleLogoutClick() {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Signup.fxml"));
            Parent signupRoot = (Parent)loader.load();
            Stage stage = (Stage)this.logoutButton.getScene().getWindow();
            Scene signupScene = new Scene(signupRoot);
            stage.setScene(signupScene);
            stage.show();
        } catch (IOException var5) {
            var5.printStackTrace();
        }

    }

    @FXML
    protected void handleBackButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PatientDashboard.fxml"));
            Parent dashboardRoot = (Parent)loader.load();
            PatientDashboardController controller = (PatientDashboardController)loader.getController();
            controller.setUsername(this.currentUsername);
            Stage stage = (Stage)this.backButton.getScene().getWindow();
            stage.setScene(new Scene(dashboardRoot));
            stage.show();
        } catch (IOException var5) {
            var5.printStackTrace();
        }

    }
}
