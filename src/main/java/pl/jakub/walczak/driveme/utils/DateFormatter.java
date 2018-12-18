package pl.jakub.walczak.driveme.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateFormatter {

    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static String formatDateToString(Instant date) {
        LocalDateTime ldt = LocalDateTime.ofInstant(date, ZoneId.systemDefault());
        return ldt.format(formatter);
    }

    public static Instant parseStringToInstant(String date){
        LocalDateTime ldt = LocalDateTime.parse(date, formatter);
        Instant instant = ldt.atZone(ZoneId.systemDefault()).toInstant();
        return instant;
    }
}
