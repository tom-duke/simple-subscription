package tom.subscription.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tom.subscription.model.SubscriptionRequest;
import tom.subscription.model.SubscriptionResponse;

@SpringBootTest
class WeeklySubscriptionService {
  @Autowired
  private SubscriptionService subService;

  @Test
  void normalWeeklySubscription() {
    SubscriptionRequest request =
        new SubscriptionRequest("$67.75", "WEEKLY", "FRIDAY", "26/02/2021", "12/03/2021");
    SubscriptionResponse resp = subService.addSubscription(request);
    assertEquals("$67.75", resp.getAmount());
    assertEquals("WEEKLY", resp.getSubscriptionType());
    List<String> invoiceDates = resp.getInvoiceDates();
    assertEquals(3, invoiceDates.size());
    assertEquals("26/02/2021", invoiceDates.get(0));
    assertEquals("05/03/2021", invoiceDates.get(1));
    assertEquals("12/03/2021", invoiceDates.get(2));
  }

  @Test
  void outRangeWeeklySubscription() {
    SubscriptionRequest request =
        new SubscriptionRequest("$67.75", "WEEKLY", "WEDNESDAY", "25/02/2021", "12/03/2021");
    SubscriptionResponse resp = subService.addSubscription(request);
    assertEquals("$67.75", resp.getAmount());
    assertEquals("WEEKLY", resp.getSubscriptionType());
    List<String> invoiceDates = resp.getInvoiceDates();
    assertEquals(2, invoiceDates.size());
    assertEquals("03/03/2021", invoiceDates.get(0));
    assertEquals("10/03/2021", invoiceDates.get(1));
  }
}
