package app.datacollect.twitchdata.feed.publisher.chatmessage.controller;

import static app.datacollect.twitchdata.feed.common.Resource.CHAT_MESSAGE;
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
public class ChatMessagePublisherController {

  private final FeedService service;

  public ChatMessagePublisherController(FeedService service) {
    this.service = service;
  }

  @PostMapping(CHAT_MESSAGE)
  public ResponseEntity<List<UUID>> publishToChatMessageFeed(
      @RequestBody List<PublisherEntry> publisherEntries) {
    final List<UUID> publishedEntryIds =
        service.createEntries("chat_message_feed", publisherEntries);
    return ResponseEntity.accepted().body(publishedEntryIds);
  }
}
