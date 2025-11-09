// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package com.example.clinicmanagement3;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;

class ViewAppointmentsController$1 extends TableCell<Appointment, Void> {
    private final Button cancelBtn;
    private final Button rescheduleBtn;
    private final HBox buttonBox;

    ViewAppointmentsController$1(final ViewAppointmentsController this$0) {
        this.this$0 = this$0;
        this.cancelBtn = new Button("Cancel");
        this.rescheduleBtn = new Button("Reschedule");
        this.buttonBox = new HBox(10.0, new Node[]{this.cancelBtn, this.rescheduleBtn});
        this.cancelBtn.setOnAction((event) -> {
            Appointment appointment = (Appointment)this.getTableView().getItems().get(this.getIndex());
            this.this$0.handleCancel(appointment);
        });
        this.rescheduleBtn.setOnAction((event) -> {
            Appointment appointment = (Appointment)this.getTableView().getItems().get(this.getIndex());
            this.this$0.handleReschedule(appointment);
        });
    }

    protected void updateItem(Void item, boolean empty) {
        super.updateItem(item, empty);
        this.setGraphic(empty ? null : this.buttonBox);
    }
}
