package tom.subscription.invoice.impl;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
@Component("DAILY")
public class DaylyInvoiceDatesGenerator implements InvoiceDatesGenerator {

  /**
   * Generate all available dates between [start date, end date]
   */
  @Override
  public List<String> generate(SubscriptionEntity subs) {
    // int duration = Period.between(start, end).getDays();
    long duration = ChronoUnit.DAYS.between(subs.getStartDate(), subs.getEndDate()) + 1;
    return Stream.iterate(subs.getStartDate(), date -> date.plusDays(1)).limit(duration)
        .map(DateUtil::format).collect(Collectors.toList());
  }

}
