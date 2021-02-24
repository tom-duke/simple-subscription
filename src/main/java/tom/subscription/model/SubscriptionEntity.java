package tom.subscription.model;

import java.util.List;

/**
 * A business entity.<br>
 * 24/02/2021 created
 * 
 * @author Tom
 *
 */
public class SubscriptionEntity {
  private long amount;
  private String currency;
  private SubscriptionType subType;
  private String dayOfWeekOrMonth;
  private String startDate;
  private String endDate;
  private List<String> invoiceDates;

  private SubscriptionEntity(SubscriptionType subType, String dayOfWeekOrMonth, String startDate,
      String endDate) {
    super();
    this.subType = subType;
    this.dayOfWeekOrMonth = dayOfWeekOrMonth;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public static SubscriptionEntity of(String subType, String dayOfWeekOrMonth, String startDate,
      String endDate) {
    return new SubscriptionEntity(SubscriptionType.valueOf(subType), dayOfWeekOrMonth, startDate,
        endDate);
  }

  public long getAmount() {
    return amount;
  }

  public void setAmount(long amount) {
    this.amount = amount;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public SubscriptionType getSubType() {
    return subType;
  }

  public void setSubType(SubscriptionType subType) {
    this.subType = subType;
  }

  public String getDayOfWeekOrMonth() {
    return dayOfWeekOrMonth;
  }

  public void setDayOfWeekOrMonth(String dayOfWeekOrMonth) {
    this.dayOfWeekOrMonth = dayOfWeekOrMonth;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public List<String> getInvoiceDates() {
    return invoiceDates;
  }

  public void setInvoiceDates(List<String> invoiceDates) {
    this.invoiceDates = invoiceDates;
  }
}
