package tom.subscription.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tom.subscription.model.SubscriptionRequest;
import tom.subscription.model.SubscriptionResponse;
import tom.subscription.service.SubscriptionService;

/**
 * 24/02/2021 created
 * 
 * @author Tom
 *
 */
@RestController
@RequestMapping(value = "api/subscription/v1", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
public class SubscriptionController {
  private final SubscriptionService subsService;

  @Autowired
  public SubscriptionController(SubscriptionService subsService) {
    super();
    this.subsService = subsService;
  }

  @PostMapping
  public SubscriptionResponse addSubscription(@RequestBody SubscriptionRequest subsRequest) {
    return subsService.addSubscription(subsRequest);
  }
}
