// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package com.example.clinicmanagement3;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

class LabResultController$1 extends TableCell<LabResult, Void> {
    private final Button viewButton;

    LabResultController$1(final LabResultController this$0) {
        this.this$0 = this$0;
        this.viewButton = new Button("View");
        this.viewButton.setOnAction((event) -> {
            LabResult result = (LabResult)this.getTableView().getItems().get(this.getIndex());
            this.this$0.handleView(result);
        });
    }

    protected void updateItem(Void item, boolean empty) {
        super.updateItem(item, empty);
        this.setGraphic(empty ? null : this.viewButton);
    }
}
