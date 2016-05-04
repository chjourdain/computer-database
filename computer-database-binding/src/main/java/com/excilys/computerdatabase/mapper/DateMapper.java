package com.excilys.computerdatabase.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * statics methods to convert date in String into other format date.
 * 
 * @author charles
 *
 */
public class DateMapper {

    /**
     * Convert from international format to french format dd-mm-yyyy.
     * 
     * @param date
     * @return
     */
    public static String toFrFormat(String date) {
        if (date != null && !date.isEmpty()) {
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }
        return null;
    }

    /**
     * Convert from international format to english format mm-dd-yyyy.
     * 
     * @param date
     * @return
     */
    public static String toEnFormat(String date) {
        if (date != null && !date.isEmpty() && !date.isEmpty()) {
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String datereturn = localDate.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
            return datereturn;
        }
        return null;
    }

    /**
     * Convert from french format to international format yyyy-mm-dd.
     * 
     * @param date
     * @return
     */
    public static String frToInternFormat(String date) {
        if (date != null && !date.isEmpty()) {
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        return null;
    }

    /**
     * Convert from english format to international format yyyy-mm-dd.
     * 
     * @param date
     * @return
     */
    public static String enToInternFormat(String date) {
        if (date != null && !date.isEmpty()) {
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
            return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        return null;
    }

}
