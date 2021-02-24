package tom.subscription.utils;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

/**
 * Validate subscription input.<br>
 * 24/02/2021 created
 * 
 * @author Tom
 *
 */
public class ValidationUtil {
  private static final Set<String> WEEK = new HashSet<>();
  static {
    WEEK.add(DayOfWeek.MONDAY.toString());
    WEEK.add(DayOfWeek.TUESDAY.toString());
    WEEK.add(DayOfWeek.WEDNESDAY.toString());
    WEEK.add(DayOfWeek.THURSDAY.toString());
    WEEK.add(DayOfWeek.FRIDAY.toString());
    WEEK.add(DayOfWeek.SATURDAY.toString());
    WEEK.add(DayOfWeek.SUNDAY.toString());
  }

  public static boolean validateDayOfWeek(String day) {
    return WEEK.contains(day);
  }

}
