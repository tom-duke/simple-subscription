package tom.subscription.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
  private static final DateTimeFormatter defaultFomatter =
      DateTimeFormatter.ofPattern("dd/MM/yyyy");

  public static final LocalDate parse(String date) {
    return LocalDate.parse(date, defaultFomatter);
  }

  public static final String format(LocalDate date) {
    return defaultFomatter.format(date);
  }
}
