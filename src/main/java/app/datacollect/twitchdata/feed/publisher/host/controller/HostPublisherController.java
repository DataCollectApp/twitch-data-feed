package app.datacollect.twitchdata.feed.publisher.host.controller;

import static app.datacollect.twitchdata.feed.common.Resource.HOST;
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
public class HostPublisherController {

  private final FeedService service;

  public HostPublisherController(FeedService service) {
    this.service = service;
  }

  @PostMapping(HOST)
  public ResponseEntity<List<UUID>> publishToHostFeed(
      @RequestBody List<PublisherEntry> publisherEntries) {
    final List<UUID> publishedEntryIds = service.createEntries("host_feed", publisherEntries);
    return ResponseEntity.accepted().body(publishedEntryIds);
  }
}
