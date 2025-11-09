// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package com.example.clinicmanagement3;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class LabResultController implements Initializable {
    @FXML
    private TableView<LabResult> labResultTable;
    @FXML
    private TableColumn<LabResult, String> servicesColumn;
    @FXML
    private TableColumn<LabResult, Void> resultsColumn;
    @FXML
    private Button logoutButton;
    @FXML
    private Button backButton;
    private String currentUsername;

    public LabResultController() {
    }

    public void setUsername(String username) {
        this.currentUsername = username;
    }

    public void initialize(URL location, ResourceBundle resources) {
        this.servicesColumn.setCellValueFactory(new PropertyValueFactory("service"));
        this.resultsColumn.setCellFactory((col) -> {
            return new 1(this);
        });
        this.labResultTable.getItems().addAll(new LabResult[]{new LabResult("Blood Test"), new LabResult("X-Ray"), new LabResult("Urinalysis")});
    }

    private void handleView(LabResult result) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("LabResultView.fxml"));
            Parent root = (Parent)loader.load();
            Object controllerObj = loader.getController();
            if (controllerObj instanceof LabResultController controller) {
                controller.setLabResult(result);
            }

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Lab Result Details");
            stage.show();
        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }

    public void setLabResult(LabResult result) {
        System.out.println("Selected service: " + result.getService());
    }

    @FXML
    private void handleBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PatientDashboard.fxml"));
            Parent root = (Parent)loader.load();
            PatientDashboardController controller = (PatientDashboardController)loader.getController();
            controller.setUsername(this.currentUsername);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Clinic Management");
            stage.show();
            Stage currentStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }

    @FXML
    private void handleLogout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Signup.fxml"));
            Parent root = (Parent)loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Clinic Management");
            stage.show();
            Stage currentStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }
}
