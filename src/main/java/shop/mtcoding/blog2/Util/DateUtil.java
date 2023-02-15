package shop.mtcoding.blog2.Util;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static String format(Timestamp stamp) {
        return stamp.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
