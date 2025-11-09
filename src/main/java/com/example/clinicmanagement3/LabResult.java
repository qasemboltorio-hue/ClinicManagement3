// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package com.example.clinicmanagement3;

import javafx.beans.property.SimpleStringProperty;

public class LabResult {
    private final SimpleStringProperty service;

    public LabResult(String service) {
        this.service = new SimpleStringProperty(service);
    }

    public String getService() {
        return this.service.get();
    }

    public SimpleStringProperty serviceProperty() {
        return this.service;
    }
}
