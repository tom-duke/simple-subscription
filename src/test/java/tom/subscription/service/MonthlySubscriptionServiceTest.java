package tom.subscription.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tom.subscription.model.SubscriptionRequest;
import tom.subscription.model.SubscriptionResponse;

@SpringBootTest
class MonthlySubscriptionServiceTest {
  @Autowired
  private SubscriptionService subService;

  @Test
  void normalMonthlySubscription() {
    SubscriptionRequest request =
        new SubscriptionRequest("$68.75", "MONTHLY", "2", "02/02/2021", "02/05/2021");
    SubscriptionResponse resp = subService.addSubscription(request);
    assertEquals("$68.75", resp.getAmount());
    assertEquals("MONTHLY", resp.getSubscriptionType());
    List<String> invoiceDates = resp.getInvoiceDates();
    assertEquals(4, invoiceDates.size());
    assertEquals("02/02/2021", invoiceDates.get(0));
    assertEquals("02/03/2021", invoiceDates.get(1));
    assertEquals("02/04/2021", invoiceDates.get(2));
    assertEquals("02/05/2021", invoiceDates.get(3));
  }

  @Test
  void outRangeMonthlySubscription() {
    SubscriptionRequest request =
        new SubscriptionRequest("$68.75", "MONTHLY", "6", "26/02/2021", "26/05/2021");
    SubscriptionResponse resp = subService.addSubscription(request);
    assertEquals("$68.75", resp.getAmount());
    assertEquals("MONTHLY", resp.getSubscriptionType());
    List<String> invoiceDates = resp.getInvoiceDates();
    assertEquals(3, invoiceDates.size());
    assertEquals("06/03/2021", invoiceDates.get(0));
    assertEquals("06/04/2021", invoiceDates.get(1));
    assertEquals("06/05/2021", invoiceDates.get(2));
  }

  @Test
  void endOfMonthMonthlySubscription() {
    SubscriptionRequest request =
        new SubscriptionRequest("$68.75", "MONTHLY", "31", "26/01/2021", "26/04/2021");
    SubscriptionResponse resp = subService.addSubscription(request);
    assertEquals("$68.75", resp.getAmount());
    assertEquals("MONTHLY", resp.getSubscriptionType());
    List<String> invoiceDates = resp.getInvoiceDates();
    assertEquals(3, invoiceDates.size());
    assertEquals("31/01/2021", invoiceDates.get(0));
    assertEquals("28/02/2021", invoiceDates.get(1));
    assertEquals("31/03/2021", invoiceDates.get(2));
  }
}
