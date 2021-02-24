package tom.subscription;

/**
 * 25/02/2021 created
 * 
 * @author Tom
 *
 */
public class Constants {
  public static final int ERR_DATETIME = 4101;

  public static final int ERR_DATETIME_PARSE = 4102;
  public static final String ERR_MSG_DATETIME_PARSE = "Date parsing error!";
  public static final String ERR_MSG_DATETIME_PARSE_ILLEGAL_DAYOFWEEK = "Day-of-week is illegal!";

  public static final int ERR_DATETIME_RANGE = 4103;
  public static final String ERR_MSG_DATETIME_RANGE = "End date before start date!";
  public static final String ERR_MSG_DATETIME_MAX_RANGE =
      "The duration between start and end is more than 3 months!";

  public static final int ERR_BAD_SUBSCRIPTION_TYPE = 4201;
  public static final String ERR_MSG_BAD_SUBSCRIPTION_TYPE = "Subscription type is illegal!";

  public static final int ERR_GENERIC = 5000;
  public static final String ERR_MSG_GENERIC = "Unknown error!";
}
