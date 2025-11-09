package com.example.clinicmanagement3;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AccountTypeController implements Initializable {
    @FXML
    private ComboBox<String> accountTypeComboBox;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private Label warningLabel;

    public AccountTypeController() {
    }

    public void initialize(URL location, ResourceBundle resources) {
        this.accountTypeComboBox.getItems().addAll(new String[]{"Admin", "Doctor", "Receptionist", "Patient"});
        this.accountTypeComboBox.setPromptText("Select Account Type");
    }

    private String getSelectedAccountType() {
        String selectedType = (String)this.accountTypeComboBox.getValue();
        if (selectedType != null && !selectedType.isEmpty()) {
            return selectedType;
        } else {
            this.accountTypeComboBox.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            this.warningLabel.setText("Please select an account type.");
            this.warningLabel.setStyle("-fx-text-fill: red;");
            return null;
        }
    }

    @FXML
    protected void handleLogin() {
        String selectedType = this.getSelectedAccountType();
        if (selectedType != null) {
            try {
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("SignUp.fxml"));
                Parent loginRoot = (Parent)loader.load();
                HelloController loginController = (HelloController)loader.getController();
                loginController.setAccountType(selectedType);
                Stage stage = (Stage)this.loginButton.getScene().getWindow();
                stage.setScene(new Scene(loginRoot));
                stage.setTitle("Clinic Management");
                stage.show();
            } catch (IOException var6) {
                var6.printStackTrace();
                this.warningLabel.setText("Failed to load login screen.");
            }

        }
    }

    @FXML
    protected void handleRegister() {
        String selectedType = this.getSelectedAccountType();
        if (selectedType != null) {
            try {
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Registration.fxml"));
                Parent registerRoot = (Parent)loader.load();
                RegisterController registerController = (RegisterController)loader.getController();
                registerController.setAccountType(selectedType);
                Stage stage = (Stage)this.registerButton.getScene().getWindow();
                stage.setScene(new Scene(registerRoot));
                stage.setTitle("Clinic Management");
                stage.show();
            } catch (IOException var6) {
                var6.printStackTrace();
                this.warningLabel.setText("Failed to load registration screen.");
            }

        }
    }
}
