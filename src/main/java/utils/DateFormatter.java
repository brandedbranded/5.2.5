package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatter {
    public static String formatDate(int y, int m, int d) {
        LocalDate date1 = LocalDate.of(y,m,d);
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date1.format(formatters);
    }
}
