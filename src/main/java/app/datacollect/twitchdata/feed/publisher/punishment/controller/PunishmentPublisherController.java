package app.datacollect.twitchdata.feed.publisher.punishment.controller;

import static app.datacollect.twitchdata.feed.common.Resource.PUBLISHER;
import static app.datacollect.twitchdata.feed.common.Resource.PUNISHMENT;

import app.datacollect.twitchdata.feed.config.FeedConfigProperties;
import app.datacollect.twitchdata.feed.feed.service.FeedService;
import app.datacollect.twitchdata.feed.publisher.resource.PublisherEntry;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PUBLISHER)
public class PunishmentPublisherController {

  private final FeedService service;
  private final FeedConfigProperties feedConfigProperties;

  public PunishmentPublisherController(
      FeedService service,
      @Qualifier("punishmentFeedConfigProperties") FeedConfigProperties feedConfigProperties) {
    this.service = service;
    this.feedConfigProperties = feedConfigProperties;
  }

  @PostMapping(PUNISHMENT)
  public ResponseEntity<List<UUID>> publishToPunishmentFeed(
      @RequestBody List<PublisherEntry> publisherEntries) {
    final List<UUID> publishedEntryIds =
        service.createEntries(feedConfigProperties.getTableName(), publisherEntries);
    return ResponseEntity.accepted().body(publishedEntryIds);
  }
}
