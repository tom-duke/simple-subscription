package tom.subscription.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tom.subscription.model.SubscriptionRequest;
import tom.subscription.model.SubscriptionResponse;
import tom.subscription.service.SubscriptionService;

@SpringBootTest
class DailySubscriptionServiceTests {
  @Autowired
  private SubscriptionService subService;

  @Test
  void normalDailySubscription() {
    SubscriptionRequest request =
        new SubscriptionRequest("$39.15", "DAILY", null, "25/02/2021", "28/02/2021");
    SubscriptionResponse resp = subService.addSubscription(request);
    assertEquals("$39.15", resp.getAmount());
    assertEquals("DAILY", resp.getSubscriptionType());
    List<String> invoiceDates = resp.getInvoiceDates();
    assertEquals(4, invoiceDates.size());
    assertEquals("25/02/2021", invoiceDates.get(0));
    assertEquals("26/02/2021", invoiceDates.get(1));
    assertEquals("28/02/2021", invoiceDates.get(3));
  }

  @Test
  void oneDaySubscription() {
    SubscriptionRequest request =
        new SubscriptionRequest("$23.60", "DAILY", null, "28/02/2021", "28/02/2021");
    SubscriptionResponse resp = subService.addSubscription(request);
    List<String> invoiceDates = resp.getInvoiceDates();
    assertEquals(1, invoiceDates.size());
    assertEquals("28/02/2021", invoiceDates.get(0));
  }

  @Test
  void acrossMonthSubscription() {
    SubscriptionRequest request =
        new SubscriptionRequest("$10.60", "DAILY", null, "27/02/2021", "10/03/2021");
    SubscriptionResponse resp = subService.addSubscription(request);
    List<String> invoiceDates = resp.getInvoiceDates();
    assertEquals(12, invoiceDates.size());
    assertEquals("28/02/2021", invoiceDates.get(1));
    assertEquals("01/03/2021", invoiceDates.get(2));
    assertEquals("08/03/2021", invoiceDates.get(9));
  }

}
