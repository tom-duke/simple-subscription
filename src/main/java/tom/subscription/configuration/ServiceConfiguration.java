package tom.subscription.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Main, basic configuration.<br>
 * 24/02/2021 created
 * 
 * @author Tom
 *
 */
@Configuration
public class ServiceConfiguration {

  @Bean
  public ObjectMapper getJsonMapper() {
    return new ObjectMapper();
  }
}
