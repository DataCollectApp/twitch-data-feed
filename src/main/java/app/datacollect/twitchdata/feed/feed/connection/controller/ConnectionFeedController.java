package app.datacollect.twitchdata.feed.feed.connection.controller;

import static app.datacollect.twitchdata.feed.common.Resource.CONNECTION;
import static app.datacollect.twitchdata.feed.common.Resource.FEED;
import static org.springframework.http.MediaType.APPLICATION_ATOM_XML_VALUE;

import app.datacollect.twitchdata.feed.common.error.ErrorMessage;
import app.datacollect.twitchdata.feed.config.FeedConfigProperties;
import app.datacollect.twitchdata.feed.feed.domain.FeedEntry;
import app.datacollect.twitchdata.feed.feed.service.FeedService;
import app.datacollect.twitchdata.feed.feed.service.FeedValidator;
import app.datacollect.twitchdata.feed.feed.service.SyndFeedService;
import com.rometools.rome.feed.WireFeed;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(FEED)
public class ConnectionFeedController {

  private final FeedService service;
  private final SyndFeedService syndFeedService;
  private final FeedConfigProperties feedConfigProperties;
  private final FeedValidator feedValidator;

  public ConnectionFeedController(
      FeedService service,
      SyndFeedService syndFeedService,
      @Qualifier("connectionFeedConfigProperties") FeedConfigProperties feedConfigProperties,
      FeedValidator feedValidator) {
    this.service = service;
    this.syndFeedService = syndFeedService;
    this.feedConfigProperties = feedConfigProperties;
    this.feedValidator = feedValidator;
  }

  @GetMapping(value = CONNECTION, produces = APPLICATION_ATOM_XML_VALUE)
  public ResponseEntity<?> getConnectionFeed(
      @RequestParam("marker") Optional<String> marker,
      @RequestParam(value = "limit", defaultValue = "1000") int limit) {
    final Optional<ErrorMessage> errorMessage = feedValidator.validateLimit(limit);
    if (errorMessage.isPresent()) {
      return ResponseEntity.badRequest().body(errorMessage.get());
    }
    final List<FeedEntry> feedEntries =
        service.getEntries(feedConfigProperties.getTableName(), marker, limit);
    return ResponseEntity.ok(
        syndFeedService.createFeed(feedEntries, feedConfigProperties).createWireFeed());
  }

  @GetMapping(value = CONNECTION + "/{id}", produces = APPLICATION_ATOM_XML_VALUE)
  public ResponseEntity<WireFeed> getConnectionFeedEntry(@PathVariable("id") UUID id) {
    return service
        .getEntry(feedConfigProperties.getTableName(), id)
        .map(
            feedEntry ->
                ResponseEntity.ok(
                    syndFeedService
                        .createFeed(List.of(feedEntry), feedConfigProperties)
                        .createWireFeed()))
        .orElse(ResponseEntity.notFound().build());
  }
}
