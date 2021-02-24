package tom.subscription.model;

import java.util.List;

/**
 * 24/02/2021 created
 * 
 * @author Tom
 *
 */
public class SubscriptionResponse extends BaseSubscription {
  private final List<String> invoiceDates;

  public SubscriptionResponse(String amount, String subscriptionType, List<String> invoiceDates) {
    super(amount, subscriptionType);
    this.invoiceDates = invoiceDates;
  }

  public List<String> getInvoiceDates() {
    return invoiceDates;
  }

}
