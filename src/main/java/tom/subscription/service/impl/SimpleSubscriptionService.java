package tom.subscription.service.impl;

import java.util.Collections;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tom.subscription.invoice.InvoiceDatesGenerator;
import tom.subscription.model.SubscriptionEntity;
import tom.subscription.model.SubscriptionRequest;
import tom.subscription.model.SubscriptionResponse;
import tom.subscription.service.SubscriptionService;

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
  private final Map<String, InvoiceDatesGenerator> invoiceDatesGens;

  @Autowired
  public SimpleSubscriptionService(ObjectMapper mapper,
      Map<String, InvoiceDatesGenerator> invoiceDatesGens) {
    super();
    this.mapper = mapper;
    this.invoiceDatesGens = invoiceDatesGens;
  }

  @Override
  public SubscriptionResponse addSubscription(SubscriptionRequest req) {
    SubscriptionEntity subscription = buildEntity(req);
    subscription.setInvoiceDates(
        invoiceDatesGens.get(subscription.getSubType().toString()).generate(subscription));
    try {
      logger.info(mapper.writeValueAsString(subscription));
    } catch (JsonProcessingException e) {
      // TODO Just ignore it now
    }
    return new SubscriptionResponse(req.getAmount(), req.getSubscriptionType(),
        Collections.unmodifiableList(subscription.getInvoiceDates()));
  }

}
