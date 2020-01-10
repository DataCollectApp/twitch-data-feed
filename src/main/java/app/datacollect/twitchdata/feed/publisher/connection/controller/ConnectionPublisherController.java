package app.datacollect.twitchdata.feed.publisher.connection.controller;

import static app.datacollect.twitchdata.feed.common.Resource.CONNECTION;
import static app.datacollect.twitchdata.feed.common.Resource.PUBLISHER;

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
public class ConnectionPublisherController {

  private final FeedService service;

  public ConnectionPublisherController(FeedService service) {
    this.service = service;
  }

  @PostMapping(CONNECTION)
  public ResponseEntity<List<UUID>> publishToConnectionFeed(
      @RequestBody List<PublisherEntry> publisherEntries) {
    final List<UUID> publishedEntryIds = service.createEntries("connection_feed", publisherEntries);
    return ResponseEntity.accepted().body(publishedEntryIds);
  }
}
