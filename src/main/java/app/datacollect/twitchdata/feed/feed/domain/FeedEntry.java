package app.datacollect.twitchdata.feed.feed.domain;

import app.datacollect.time.UTCDateTime;
import app.datacollect.twitchdata.feed.events.EventType;
import app.datacollect.twitchdata.feed.events.ObjectType;
import app.datacollect.twitchdata.feed.events.Version;
import java.util.UUID;

public class FeedEntry {
  private final UUID id;
  private final String author;
  private final EventType eventType;
  private final ObjectType objectType;
  private final Version version;
  private final UTCDateTime publishedDate;
  private final UTCDateTime updatedDate;
  private final String contentType;
  private final String content;

  public FeedEntry(
      UUID id,
      String author,
      EventType eventType,
      ObjectType objectType,
      Version version,
      UTCDateTime publishedDate,
      UTCDateTime updatedDate,
      String contentType,
      String content) {
    this.id = id;
    this.author = author;
    this.eventType = eventType;
    this.objectType = objectType;
    this.version = version;
    this.publishedDate = publishedDate;
    this.updatedDate = updatedDate;
    this.contentType = contentType;
    this.content = content;
  }

  public UUID getId() {
    return id;
  }

  public String getAuthor() {
    return author;
  }

  public EventType getEventType() {
    return eventType;
  }

  public ObjectType getObjectType() {
    return objectType;
  }

  public Version getVersion() {
    return version;
  }

  public UTCDateTime getPublishedDate() {
    return publishedDate;
  }

  public UTCDateTime getUpdatedDate() {
    return updatedDate;
  }

  public String getContentType() {
    return contentType;
  }

  public String getContent() {
    return content;
  }
}
