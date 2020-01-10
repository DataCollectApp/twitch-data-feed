package app.datacollect.twitchdata.feed.common.error;

public class ErrorMessage {
  private final String key;
  private final String message;
  private final String time;

  public ErrorMessage(String key, String message, String time) {
    this.key = key;
    this.message = message;
    this.time = time;
  }

  public String getKey() {
    return key;
  }

  public String getMessage() {
    return message;
  }

  public String getTime() {
    return time;
  }
}
