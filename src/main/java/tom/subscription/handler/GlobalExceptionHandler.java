package tom.subscription.handler;

import java.time.DateTimeException;
import java.time.format.DateTimeParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tom.subscription.Constants;
import tom.subscription.exception.SubscriptionBadReqeustException;
import tom.subscription.model.ExceptionResponse;

/**
 * 24/02/2021 created
 * 
 * @author Tom
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(DateTimeException.class)
  public ResponseEntity<ExceptionResponse> handleDateTime(DateTimeException ex) {
    return ResponseEntity.badRequest()
        .body(new ExceptionResponse(ex.getMessage(), Constants.ERR_DATETIME));
  }

  @ExceptionHandler(DateTimeParseException.class)
  public ResponseEntity<ExceptionResponse> handleDateTimeParse(DateTimeParseException ex) {
    return ResponseEntity.badRequest().body(
        new ExceptionResponse(Constants.ERR_MSG_DATETIME_PARSE, Constants.ERR_DATETIME_PARSE));
  }

  @ExceptionHandler(SubscriptionBadReqeustException.class)
  public ResponseEntity<ExceptionResponse> handleSubscriptionBadRequest(
      SubscriptionBadReqeustException ex) {
    return ResponseEntity.badRequest().body(new ExceptionResponse(ex.getMessage(), ex.getCode()));
  }

  // Subscription type parse error
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ExceptionResponse> handleIllegalArgument(IllegalArgumentException ex) {
    return ResponseEntity.badRequest().body(new ExceptionResponse(
        Constants.ERR_MSG_BAD_SUBSCRIPTION_TYPE, Constants.ERR_BAD_SUBSCRIPTION_TYPE));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionResponse> handleGenericException(Exception ex, WebRequest req) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ExceptionResponse(Constants.ERR_MSG_GENERIC, Constants.ERR_GENERIC));
  }
}
