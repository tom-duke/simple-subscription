package tom.subscription.invoice;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import tom.subscription.Constants;
import tom.subscription.exception.SubscriptionBadReqeustException;
import tom.subscription.model.SubscriptionEntity;
import tom.subscription.utils.DateUtil;

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

  default void validate(SubscriptionEntity subs) {
    if (subs.getEndDate().isBefore(subs.getStartDate()))
      throw new SubscriptionBadReqeustException(Constants.ERR_DATETIME_RANGE,
          Constants.ERR_MSG_DATETIME_RANGE);

    LocalDate temp = subs.getStartDate().plusMonths(3);
    if (temp.isBefore(subs.getEndDate()))
      throw new SubscriptionBadReqeustException(Constants.ERR_DATETIME_RANGE,
          Constants.ERR_MSG_DATETIME_MAX_RANGE);
  }

  default List<String> genRange(LocalDate start, LocalDate end,
      Function<LocalDate, LocalDate> stepFun) {
    List<String> dates = new LinkedList<>();
    while (start.isBefore(end) || start.isEqual(end)) {
      dates.add(DateUtil.format(start));
      start = stepFun.apply(start);
    }
    return dates;
  }
}
