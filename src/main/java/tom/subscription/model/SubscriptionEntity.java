package tom.subscription.model;

import java.time.LocalDate;
import java.util.List;
import tom.subscription.utils.DateUtil;

/**
 * A business entity.<br>
 * 24/02/2021 created
 * 
 * @author Tom
 *
 */
public class SubscriptionEntity {
  private final String amount;
  private final SubscriptionType subType;
  private final String dayOfWeekOrMonth;
  private final LocalDate startDate;
  private final LocalDate endDate;
  private List<String> invoiceDates;

  private SubscriptionEntity(String amount, SubscriptionType subType, String dayOfWeekOrMonth,
      LocalDate startDate, LocalDate endDate) {
    super();
    this.amount = amount;
    this.subType = subType;
    this.dayOfWeekOrMonth = dayOfWeekOrMonth;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public static SubscriptionEntity of(String amount, String subType, String dayOfWeekOrMonth,
      String startDate, String endDate) {
    return new SubscriptionEntity(amount, SubscriptionType.valueOf(subType), dayOfWeekOrMonth,
        DateUtil.parse(startDate), DateUtil.parse(endDate));
  }

  public String getAmount() {
    return amount;
  }

  public SubscriptionType getSubType() {
    return subType;
  }

  public String getDayOfWeekOrMonth() {
    return dayOfWeekOrMonth;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public List<String> getInvoiceDates() {
    return invoiceDates;
  }

  public void setInvoiceDates(List<String> invoiceDates) {
    this.invoiceDates = invoiceDates;
  }
}
