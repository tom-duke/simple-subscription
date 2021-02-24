package tom.subscription.exception;

/**
 * 24/02/2021 created
 * 
 * @author Tom
 *
 */
public class SubscriptionBadReqeustException extends RuntimeException {
  private final int code;

  /**
   * 
   */
  private static final long serialVersionUID = -6555482809037375647L;

  public SubscriptionBadReqeustException(int code, String arg0) {
    super(arg0);
    this.code = code;
  }

  public int getCode() {
    return code;
  }

}
