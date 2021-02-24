package tom.subscription.service.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tom.subscription.model.SubscriptionEntity;
import tom.subscription.model.SubscriptionRequest;
import tom.subscription.model.SubscriptionResponse;
import tom.subscription.service.SubscriptionService;
import tom.subscription.utils.DateUtil;

/**
 * It's a simple implement of subscription service.<br>
 * 24/02/2021 created
 * 
 * @author Tom
 *
 */
@Service
public class SimpleSubscriptionService implements SubscriptionService {
  private static final Logger logger = LoggerFactory.getLogger(SimpleSubscriptionService.class);
  private final ObjectMapper mapper;

  @Autowired
  public SimpleSubscriptionService(ObjectMapper mapper) {
    super();
    this.mapper = mapper;
  }

  @Override
  public SubscriptionResponse addSubscription(SubscriptionRequest req) {
    SubscriptionEntity subscription = buildEntity(req);
    subscription.setInvoiceDates(buildInvoiceDates(subscription));
    try {
      logger.info(mapper.writeValueAsString(subscription));
    } catch (JsonProcessingException e) {
      // TODO Maybe just ignore it now
    }
    return new SubscriptionResponse(req.getAmount(), req.getSubscriptionType(),
        Collections.unmodifiableList(subscription.getInvoiceDates()));
  }

  // TODO need refactoring, maybe strategy?
  private List<String> buildInvoiceDates(SubscriptionEntity subs) {
    LocalDate start = DateUtil.parse(subs.getStartDate());
    LocalDate end = DateUtil.parse(subs.getEndDate());

    switch (subs.getSubType()) {
      case DAILY: {
        return dailyInvoceDates(start, end);
      }
      case WEEKLY: {
        return weeklyInvoceDates(subs, start, end);
      }
      case MONTHLY: {
        return monthlyInvoceDates(subs, start, end);
      }
      default: {
        return Collections.emptyList();
      }
    }
  }

  private List<String> dailyInvoceDates(LocalDate start, LocalDate end) {
    // int duration = Period.between(start, end).getDays();
    long duration = ChronoUnit.DAYS.between(start, end) + 1;
    return Stream.iterate(start, date -> date.plusDays(1)).limit(duration)
        .map(date -> DateUtil.format(date)).collect(Collectors.toList());
  }

  private List<String> weeklyInvoceDates(SubscriptionEntity subs, LocalDate start, LocalDate end) {
    LocalDate issueDate =
        start.with(TemporalAdjusters.nextOrSame(DayOfWeek.valueOf(subs.getDayOfWeekOrMonth())));
    List<String> dates = new LinkedList<>();
    while (issueDate.isBefore(end) || issueDate.isEqual(end)) {
      dates.add(DateUtil.format(issueDate));
      issueDate = issueDate.plusWeeks(1);
    }
    return dates;
  }

  private List<String> monthlyInvoceDates(SubscriptionEntity subs, LocalDate start, LocalDate end) {
    LocalDate issueDate = start.withDayOfMonth(Integer.parseInt(subs.getDayOfWeekOrMonth()));
    if (issueDate.isBefore(start))
      issueDate = issueDate.plusMonths(1);
    List<String> dates = new LinkedList<>();
    while (issueDate.isBefore(end) || issueDate.isEqual(end)) {
      dates.add(DateUtil.format(issueDate));
      issueDate = issueDate.plusMonths(1);
    }
    return dates;
  }

}
