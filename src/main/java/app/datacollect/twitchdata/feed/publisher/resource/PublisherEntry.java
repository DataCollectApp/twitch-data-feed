package app.datacollect.twitchdata.feed.publisher.resource;

import app.datacollect.twitchdata.feed.events.EventType;
import app.datacollect.twitchdata.feed.events.ObjectType;
import app.datacollect.twitchdata.feed.events.Version;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PublisherEntry {
  private final String author;
  private final EventType eventType;
  private final ObjectType objectType;
  private final Version version;
  private final String content;

  @JsonCreator
  public PublisherEntry(
      @JsonProperty("author") String author,
      @JsonProperty("eventType") EventType eventType,
      @JsonProperty("objectType") ObjectType objectType,
      @JsonProperty("version") Version version,
      @JsonProperty("content") String content) {
    this.author = author;
    this.eventType = eventType;
    this.objectType = objectType;
    this.version = version;
    this.content = content;
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

  public String getContent() {
    return content;
  }
}
