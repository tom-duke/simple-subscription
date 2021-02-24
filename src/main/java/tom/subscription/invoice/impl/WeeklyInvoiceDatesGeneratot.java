package tom.subscription.invoice.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Component;
import tom.subscription.invoice.InvoiceDatesGenerator;
import tom.subscription.model.SubscriptionEntity;
import tom.subscription.utils.DateUtil;

/**
 * 24/02/2021 created
 * 
 * @author Tom
 *
 */
@Component("WEEKLY")
public class WeeklyInvoiceDatesGeneratot implements InvoiceDatesGenerator {

  /**
   * Generate all available dates specified by the day-of-week,<br>
   * also are between [start date, end date]
   */
  @Override
  public List<String> generate(SubscriptionEntity subs) {
    LocalDate issueDate = subs.getStartDate()
        .with(TemporalAdjusters.nextOrSame(DayOfWeek.valueOf(subs.getDayOfWeekOrMonth())));
    List<String> dates = new LinkedList<>();
    while (issueDate.isBefore(subs.getEndDate()) || issueDate.isEqual(subs.getEndDate())) {
      dates.add(DateUtil.format(issueDate));
      issueDate = issueDate.plusWeeks(1);
    }
    return dates;
  }

}
