package tom.subscription.api;

import static org.hamcrest.Matchers.hasSize;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;
import tom.subscription.model.SubscriptionRequest;

/**
 * Test unusual case
 * 
 * @author Tom
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
class SubscriptionControllerTest {
  @Autowired
  private ObjectMapper mapper;
  @Autowired
  private MockMvc mockMvc;

  private static final String API_URL = "/api/subscription/v1";

  @Test
  void normalRequest() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.post(API_URL)
            .content(asJsonString(
                new SubscriptionRequest("$68.75", "MONTHLY", "2", "02/02/2021", "02/05/2021")))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.invoiceDates", hasSize(4)));
  }

  @Test
  void dateTimeExceptionRequest() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.post(API_URL)
            .content(asJsonString(
                new SubscriptionRequest("$68.75", "MONTHLY", "32", "02/02/2021", "02/05/2021")))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(4101)));
  }

  @Test
  void dateTimeParseExceptionRequest() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.post(API_URL)
            .content(asJsonString(
                new SubscriptionRequest("$68.75", "MONTHLY", "1", "32/02/2021", "02/05/2021")))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(4102)));
  }

  @Test
  void badRequestEndBeforStartExceptionRequest() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.post(API_URL)
            .content(asJsonString(
                new SubscriptionRequest("$68.75", "WEEKLY", "MONDAY", "02/02/2021", "02/01/2021")))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(4103)));
  }

  @Test
  void badRequestBadDayOfWeekExceptionRequest() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.post(API_URL)
            .content(asJsonString(new SubscriptionRequest("$68.75", "WEEKLY", "MONDAY1hjhj",
                "02/02/2021", "02/03/2021")))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(4102)));
  }

  @Test
  void badRequestBadSubscriptionTypeExceptionRequest() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.post(API_URL)
            .content(asJsonString(new SubscriptionRequest("$68.75", "WEEKLYsdsd", "MONDAY",
                "02/02/2021", "02/03/2021")))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(4201)));
  }

  @Test
  void badRequestOverRangeExceptionRequest() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.post(API_URL)
            .content(asJsonString(
                new SubscriptionRequest("$68.75", "WEEKLY", "MONDAY", "02/02/2021", "02/08/2021")))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(4103)));
  }

  public String asJsonString(final Object obj) {
    try {
      return mapper.writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
