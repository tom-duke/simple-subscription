package tom.subscription.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
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
      default: {
        return Collections.emptyList();
      }
    }
  }

  private List<String> dailyInvoceDates(LocalDate start, LocalDate end) {
    int duration = Period.between(start, end).getDays();
    List<String> dates = new LinkedList<>();
    for (int i = 0; i <= duration; i++) {
      dates.add(DateUtil.format(start.plusDays(i)));
    }

    return dates;
  }

}
