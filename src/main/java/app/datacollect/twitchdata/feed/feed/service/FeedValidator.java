package app.datacollect.twitchdata.feed.feed.service;

import app.datacollect.time.UTCDateTime;
import app.datacollect.twitchdata.feed.common.error.ErrorMessage;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class FeedValidator {

  public Optional<ErrorMessage> validateLimit(int limit) {
    if (limit < 0 || limit > 2000) {
      return Optional.of(
          new ErrorMessage(
              "INVALID_LIMIT",
              "Limit must be a number between 0 and 2000 (inclusive)",
              UTCDateTime.now().iso8601()));
    }
    return Optional.empty();
  }
}
