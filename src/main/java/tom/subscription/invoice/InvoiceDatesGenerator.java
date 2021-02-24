package tom.subscription.invoice;

import java.util.List;
import tom.subscription.model.SubscriptionEntity;

/**
 * Generate invoice dates 24/02/2021 created
 * 
 * @author Tom
 *
 */
public interface InvoiceDatesGenerator {
  /**
   * Generate Invoice dates.
   * 
   * @param subs
   * @return {@link List<String>}
   */
  public List<String> generate(SubscriptionEntity subs);
}
