package com.example.clinicmanagement3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class ReceptionistScheduleController {

    @FXML private TableView<Doctor> doctorTable;
    @FXML private TableColumn<Doctor, String> colDoctor;
    @FXML private TableColumn<Doctor, String> colSpecialty;
    @FXML private TableColumn<Doctor, String> colDays;
    @FXML private TableColumn<Doctor, String> colTime;
    @FXML private TableColumn<Doctor, String> colStatus;

    private ObservableList<Doctor> doctorList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colDoctor.setCellValueFactory(new PropertyValueFactory<>("doctor"));
        colSpecialty.setCellValueFactory(new PropertyValueFactory<>("specialty"));
        colDays.setCellValueFactory(new PropertyValueFactory<>("availableDays"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        doctorList.addAll(
                new Doctor("Dr. Emily Santos", "General Practitioner", "Mon - Fri", "9:30 AM - 6:30 PM", "On Duty"),
                new Doctor("Dr. Jonathan Reyes", "General Practitioner", "Tue - Fri", "10:30 AM - 7:30 PM", "On Duty"),
                new Doctor("Dr. Stella Corpuz", "Pediatrician", "Mon - Thurs", "10:30 AM - 4:30 PM", "On Duty"),
                new Doctor("Dr. Toni Fowler", "Obstetrician", "Wed - Sat", "9:30 AM - 4:30 PM", "On Duty"),
                new Doctor("Dr. Cardo Dalisay", "Pediatrician", "Wed - Sun", "10:30 AM - 5:30 PM", "On Duty"),
                new Doctor("Dr. Miles Morales", "General Practitioner", "Sat - Sun", "9:30 AM - 7:30 PM", "On Leave")
        );

        doctorTable.setItems(doctorList);
    }

    @FXML
    private void handleEdit(ActionEvent event) throws IOException {
        Doctor selectedDoctor = doctorTable.getSelectionModel().getSelectedItem();
        if (selectedDoctor == null) {
            System.out.println("⚠️ Please select a doctor to edit.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditDoctorDialog.fxml"));
        Parent root = loader.load();

        EditDoctorDialogController controller = loader.getController();
        controller.setDoctor(selectedDoctor);

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit Doctor Schedule");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(((Node) event.getSource()).getScene().getWindow());
        dialogStage.setScene(new Scene(root));
        dialogStage.showAndWait();

        if (controller.isSaved()) {
            doctorTable.refresh(); // updates the display
        }
    }

    @FXML
    private void handleBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("receptionist_dashboard.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("MediCore - Dashboard");
        stage.show();
    }

    @FXML
    private void handleSignOut(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Signup.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("MediCore - Sign In");
        stage.show();
    }
}
