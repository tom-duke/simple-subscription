package tom.subscription.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The request data coming from API
 * 
 * @author Tom
 *
 */

public class SubscriptionRequest extends BaseSubscription {
  // MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
  private final String dayOfWeekOrMonth;
  // Local date 24/02/2021
  private final String startDate;
  // Local date 24/02/2021
  private final String endDate;

  @JsonCreator(mode = Mode.PROPERTIES)
  public SubscriptionRequest(@JsonProperty("amount") String amount,
      @JsonProperty("subscriptionType") String subscriptionType,
      @JsonProperty("dayOfWeekOrMonth") String dayOfWeekOrMonth,
      @JsonProperty("startDate") String startDate, @JsonProperty("endDate") String endDate) {
    super(amount, subscriptionType);
    this.dayOfWeekOrMonth = dayOfWeekOrMonth;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public String getDayOfWeekOrMonth() {
    return dayOfWeekOrMonth;
  }

  public String getStartDate() {
    return startDate;
  }

  public String getEndDate() {
    return endDate;
  }

}
