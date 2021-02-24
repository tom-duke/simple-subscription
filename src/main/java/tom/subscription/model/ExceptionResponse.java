package tom.subscription.model;

/**
 * 24/02/2021 created
 * 
 * @author Tom
 *
 */
public class ExceptionResponse {
  private final String msg;
  private final int code;

  public ExceptionResponse(String msg, int code) {
    super();
    this.msg = msg;
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public int getCode() {
    return code;
  }

}
