package tom.subscription.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import tom.subscription.invoice.InvoiceDatesGenerator;
import tom.subscription.utils.DateUtil;
import tom.subscription.utils.ValidationUtil;

public enum SubscriptionType implements InvoiceDatesGenerator {
  DAILY {
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

  },
  WEEKLY {
    /**
     * Generate all available dates specified by the day-of-week,<br>
     * also are between [start date, end date]
     */
    @Override
    public List<String> generate(SubscriptionEntity subs) {
      LocalDate issueDate = subs.getStartDate()
          .with(TemporalAdjusters.nextOrSame(DayOfWeek.valueOf(subs.getDayOfWeekOrMonth())));
      return genRange(issueDate, subs.getEndDate(), (d) -> d.plusWeeks(1));
    }

    @Override
    public void validate(SubscriptionEntity subs) {
      ValidationUtil.validateDayOfWeek(subs.getDayOfWeekOrMonth());
      super.validate(subs);
    }

  },
  MONTHLY {

    @Override
    public List<String> generate(SubscriptionEntity subs) {
      LocalDate issueDate =
          subs.getStartDate().withDayOfMonth(Integer.parseInt(subs.getDayOfWeekOrMonth()));
      if (issueDate.isBefore(subs.getStartDate()))
        issueDate = issueDate.plusMonths(1);
      return genRange(issueDate, subs.getEndDate(), (d) -> d.plusMonths(1));
    }

  };

}
