package app.datacollect.twitchdata.feed.publisher.chatmessage.controller;

import static app.datacollect.twitchdata.feed.common.Resource.CHAT_MESSAGE;
import static app.datacollect.twitchdata.feed.common.Resource.PUBLISHER;

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
public class ChatMessagePublisherController {

  private final FeedService service;
  private final FeedConfigProperties feedConfigProperties;

  public ChatMessagePublisherController(
      FeedService service,
      @Qualifier("chatMessageFeedConfigProperties") FeedConfigProperties feedConfigProperties) {
    this.service = service;
    this.feedConfigProperties = feedConfigProperties;
  }

  @PostMapping(CHAT_MESSAGE)
  public ResponseEntity<List<UUID>> publishToChatMessageFeed(
      @RequestBody List<PublisherEntry> publisherEntries) {
    final List<UUID> publishedEntryIds =
        service.createEntries(feedConfigProperties.getTableName(), publisherEntries);
    return ResponseEntity.accepted().body(publishedEntryIds);
  }
}
