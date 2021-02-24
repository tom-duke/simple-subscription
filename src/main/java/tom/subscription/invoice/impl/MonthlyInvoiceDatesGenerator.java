package tom.subscription.invoice.impl;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Component;
import tom.subscription.invoice.InvoiceDatesGenerator;
import tom.subscription.model.SubscriptionEntity;

/**
 * 24/02/2021 created
 * 
 * @author Tom
 *
 */
@Component("MONTHLY")
public class MonthlyInvoiceDatesGenerator implements InvoiceDatesGenerator {

  /**
   * Generate all available dates specified by the day-of-month,<br>
   * also are between [start date, end date]
   */
  @Override
  public List<String> generate(SubscriptionEntity subs) {
    LocalDate issueDate =
        subs.getStartDate().withDayOfMonth(Integer.parseInt(subs.getDayOfWeekOrMonth()));
    if (issueDate.isBefore(subs.getStartDate()))
      issueDate = issueDate.plusMonths(1);
    // List<String> dates = new LinkedList<>();
    // while (issueDate.isBefore(subs.getEndDate()) || issueDate.isEqual(subs.getEndDate())) {
    // dates.add(DateUtil.format(issueDate));
    // issueDate = issueDate.plusMonths(1);
    // }
    // return dates;
    return genRange(issueDate, subs.getEndDate(), (d) -> d.plusMonths(1));
  }

}
