package com.example.clinicmanagement3; // ← adjust this if your package name is different

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Load your FXML file (adjust the file name if needed)
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("receptionist_dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);


        stage.setTitle("ClinicManagement3"); // 👈 updated window title
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
