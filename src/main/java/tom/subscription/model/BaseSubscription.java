package tom.subscription.model;

/**
 * 24/02/2021 created
 * 
 * @author Tom
 *
 */
public class BaseSubscription {
  // $10.00
  protected final String amount;
  // DAILY, WEEKLY or MONTHLY
  protected final String subscriptionType;

  public BaseSubscription(String amount, String subscriptionType) {
    super();
    this.amount = amount;
    this.subscriptionType = subscriptionType;
  }

  public String getAmount() {
    return amount;
  }

  public String getSubscriptionType() {
    return subscriptionType;
  }

}
