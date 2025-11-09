// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package com.example.clinicmanagement3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TreatmentPlanController {
    private String currentUsername;

    public TreatmentPlanController() {
    }

    public void setUsername(String username) {
        this.currentUsername = username;
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        try {
            Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("SignUp.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Clinic Management");
            stage.show();
            Stage currentStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (Exception var5) {
            var5.printStackTrace();
        }

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
            stage.setTitle("Clinic Maangement");
            stage.show();
            Stage currentStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }
}
