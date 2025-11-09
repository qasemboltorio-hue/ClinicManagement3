package com.example.clinicmanagement3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Random;
import javafx.scene.layout.GridPane;


public class ReceptionistBillingController {

    @FXML private Label totalIncomeLabel;
    @FXML private Label patientsBilledLabel;
    @FXML private Label pendingPaymentsLabel;
    @FXML private Label invoicesGeneratedLabel;
    @FXML private TableView<Invoice> invoiceTable;
    @FXML private TableColumn<Invoice, String> colInvoiceNo;
    @FXML private TableColumn<Invoice, String> colPatient;
    @FXML private TableColumn<Invoice, String> colDoctor;
    @FXML private TableColumn<Invoice, String> colService;
    @FXML private TableColumn<Invoice, String> colAmount;
    @FXML private TableColumn<Invoice, String> colStatus;

    private ObservableList<Invoice> invoiceList = FXCollections.observableArrayList();

    private double totalIncome = 0;
    private int patientsBilled = 0;
    private int pendingPayments = 0;
    private int invoicesGenerated = 0;

    @FXML
    public void initialize() {
        colInvoiceNo.setCellValueFactory(cellData -> cellData.getValue().invoiceNoProperty());
        colPatient.setCellValueFactory(cellData -> cellData.getValue().patientProperty());
        colDoctor.setCellValueFactory(cellData -> cellData.getValue().doctorProperty());
        colService.setCellValueFactory(cellData -> cellData.getValue().serviceProperty());
        colAmount.setCellValueFactory(cellData -> cellData.getValue().amountProperty());
        colStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        invoiceTable.setItems(invoiceList);
    }

    @FXML
    public void handleNewInvoice() {
        Dialog<Invoice> dialog = new Dialog<>();
        dialog.setTitle("New Invoice");
        dialog.setHeaderText("Enter Invoice Details");

        // Form fields
        TextField patientField = new TextField();
        TextField doctorField = new TextField();
        TextField serviceField = new TextField();
        TextField amountField = new TextField();

        ComboBox<String> statusBox = new ComboBox<>();
        statusBox.getItems().addAll("Paid", "Pending");
        statusBox.setValue("Paid"); // default

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("Patient Name:"), 0, 0);
        grid.add(patientField, 1, 0);
        grid.add(new Label("Doctor:"), 0, 1);
        grid.add(doctorField, 1, 1);
        grid.add(new Label("Service:"), 0, 2);
        grid.add(serviceField, 1, 2);
        grid.add(new Label("Amount:"), 0, 3);
        grid.add(amountField, 1, 3);
        grid.add(new Label("Status:"), 0, 4);
        grid.add(statusBox, 1, 4);

        dialog.getDialogPane().setContent(grid);

        ButtonType addButtonType = new ButtonType("Add Invoice", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                try {
                    String invoiceNo = String.valueOf(new Random().nextInt(9999999));
                    double amount = Double.parseDouble(amountField.getText());
                    return new Invoice(
                            invoiceNo,
                            patientField.getText(),
                            doctorField.getText(),
                            serviceField.getText(),
                            "₱" + amount,
                            statusBox.getValue()
                    );
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Invalid Amount");
                    alert.setContentText("Please enter a valid numeric amount.");
                    alert.showAndWait();
                }
            }
            return null;
        });

        dialog.showAndWait().ifPresent(invoice -> {
            invoiceList.add(invoice);
            invoicesGenerated++;

            if (invoice.statusProperty().get().equalsIgnoreCase("Paid")) {
                totalIncome += Double.parseDouble(invoice.getAmount().replace("₱", ""));
                patientsBilled++;
            } else {
                pendingPayments++;
            }

            updateSummaryLabels();
        });
    }


    private void updateSummaryLabels() {
        totalIncomeLabel.setText("₱" + totalIncome);
        patientsBilledLabel.setText(String.valueOf(patientsBilled));
        pendingPaymentsLabel.setText(String.valueOf(pendingPayments));
        invoicesGeneratedLabel.setText(String.valueOf(invoicesGenerated));
    }

    @FXML
    public void handleBack() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("receptionist_dashboard.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML private Button backButton;
    @FXML private Button signOutButton;

    @FXML
    protected void handleSignOut() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Signup.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) signOutButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
