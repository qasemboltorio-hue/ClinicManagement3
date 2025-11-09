package com.example.clinicmanagement3;

import java.util.LinkedHashMap;
import java.util.Map;

public class ReportsDataService {

    public Map<String, Number> getPatientsOverTime() {
        Map<String, Number> data = new LinkedHashMap<>();
        data.put("Mon", 25);
        data.put("Tue", 32);
        data.put("Wed", 28);
        data.put("Thu", 40);
        data.put("Fri", 35);
        return data;
    }

    public Map<String, Number> getCompletedAppointmentsOverTime() {
        Map<String, Number> data = new LinkedHashMap<>();
        data.put("Mon", 10);
        data.put("Tue", 15);
        data.put("Wed", 12);
        data.put("Thu", 18);
        data.put("Fri", 14);
        return data;
    }

    public Map<String, Number> getTopDiagnoses() {
        Map<String, Number> data = new LinkedHashMap<>();
        data.put("Flu", 30);
        data.put("Covid-19", 25);
        data.put("Allergy", 15);
        data.put("Cold", 10);
        data.put("Others", 20);
        return data;
    }
}
