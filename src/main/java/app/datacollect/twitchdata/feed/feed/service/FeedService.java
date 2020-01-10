package app.datacollect.twitchdata.feed.feed.service;

import app.datacollect.time.UTCDateTime;
import app.datacollect.twitchdata.feed.feed.domain.FeedEntry;
import app.datacollect.twitchdata.feed.feed.repository.FeedRepository;
import app.datacollect.twitchdata.feed.publisher.resource.PublisherEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
public class FeedService {

  private static final int FEED_READ_DELAY_SECONDS = 300;

  private final FeedRepository repository;

  public FeedService(FeedRepository repository) {
    this.repository = repository;
  }

  public List<UUID> createEntries(String table, List<PublisherEntry> publisherEntries) {
    UTCDateTime lastPublishedTime = UTCDateTime.now();
    final List<FeedEntry> feedEntries = new ArrayList<>();
    for (PublisherEntry publisherEntry : publisherEntries) {
      final var id = UUID.randomUUID();
      final var feedEntry =
          new FeedEntry(
              id,
              publisherEntry.getAuthor(),
              publisherEntry.getEventType(),
              publisherEntry.getObjectType(),
              publisherEntry.getVersion(),
              lastPublishedTime,
              lastPublishedTime,
              MediaType.APPLICATION_JSON_VALUE,
              publisherEntry.getContent());
      feedEntries.add(feedEntry);
      lastPublishedTime = lastPublishedTime.plusSeconds(1);
    }
    repository.insertEntries(table, feedEntries);
    return feedEntries.stream().map(FeedEntry::getId).collect(Collectors.toList());
  }

  public List<FeedEntry> getEntries(String table, Optional<String> marker, int limit) {
    if (marker.isPresent()) {
      if (marker.get().equals("start")) {
        return repository.getEntriesFromBeginning(
            table, limit, UTCDateTime.now().minusSeconds(FEED_READ_DELAY_SECONDS));
      } else {
        return repository.getEntriesFromMarker(
            table,
            UUID.fromString(marker.get()),
            limit,
            UTCDateTime.now().minusSeconds(FEED_READ_DELAY_SECONDS));
      }
    } else {
      return repository.getEntries(table, limit);
    }
  }

  public Optional<FeedEntry> getEntry(String table, UUID id) {
    return repository.getEntry(table, id);
  }
}
