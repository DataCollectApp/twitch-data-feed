package app.datacollect.twitchdata.feed.publisher.punishment.controller;

import static app.datacollect.twitchdata.feed.common.Resource.PUBLISHER;
import static app.datacollect.twitchdata.feed.common.Resource.PUNISHMENT;

import app.datacollect.twitchdata.feed.feed.service.FeedService;
import app.datacollect.twitchdata.feed.publisher.resource.PublisherEntry;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PUBLISHER)
public class PunishmentPublisherController {

  private final FeedService service;

  public PunishmentPublisherController(FeedService service) {
    this.service = service;
  }

  @PostMapping(PUNISHMENT)
  public ResponseEntity<List<UUID>> publishToPunishmentFeed(
      @RequestBody List<PublisherEntry> publisherEntries) {
    final List<UUID> publishedEntryIds = service.createEntries("punishment_feed", publisherEntries);
    return ResponseEntity.accepted().body(publishedEntryIds);
  }
}
