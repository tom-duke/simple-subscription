package tom.subscription.service;

import tom.subscription.model.SubscriptionEntity;
import tom.subscription.model.SubscriptionRequest;
import tom.subscription.model.SubscriptionResponse;

/**
 * 24/02/2021 created
 * 
 * @author Tom
 *
 */
public interface SubscriptionService {
  /**
   * Handle the subscription request and compute the invoice dates.
   * 
   * @param req
   * @return {@link SubscriptionEntity}
   */
  public SubscriptionResponse addSubscription(SubscriptionRequest req);

  /**
   * Maybe move it into a utility class when refactoring.
   * 
   * @param req
   * @return {@link SubscriptionEntity}
   */
  default SubscriptionEntity buildEntity(SubscriptionRequest req) {
    return SubscriptionEntity.of(req.getAmount(), req.getSubscriptionType(),
        req.getDayOfWeekOrMonth(), req.getStartDate(), req.getEndDate());
  }
}
