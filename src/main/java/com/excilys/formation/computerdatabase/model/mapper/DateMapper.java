package com.excilys.formation.computerdatabase.model.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateMapper {
    
    public static String toFrFormat(String date) {
        if (date != null && !date.isEmpty()) {
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }
        return null;
    }

    public static String toEnFormat(String date) {
        if (date != null && !date.isEmpty() && !date.isEmpty()) {
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String datereturn  =  localDate.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
            return datereturn;
        }
        return null;
    }
    
    public static String frToInternFormat(String date) {
        if (date != null && !date.isEmpty()) {
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        return null;
    }
    public static String enToInternFormat(String date) {
        if (date != null && !date.isEmpty()) {
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
            return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        return null;
    }
    
}
