package app.datacollect.twitchdata.feed.feed.repository;

import static app.datacollect.twitchdata.feed.common.repository.Tables.FEED.AUTHOR;
import static app.datacollect.twitchdata.feed.common.repository.Tables.FEED.CONTENT;
import static app.datacollect.twitchdata.feed.common.repository.Tables.FEED.CONTENT_TYPE;
import static app.datacollect.twitchdata.feed.common.repository.Tables.FEED.EVENT_TYPE;
import static app.datacollect.twitchdata.feed.common.repository.Tables.FEED.ID;
import static app.datacollect.twitchdata.feed.common.repository.Tables.FEED.OBJECT_TYPE;
import static app.datacollect.twitchdata.feed.common.repository.Tables.FEED.PUBLISHED_DATE;
import static app.datacollect.twitchdata.feed.common.repository.Tables.FEED.UPDATED_DATE;
import static app.datacollect.twitchdata.feed.common.repository.Tables.FEED.VERSION;

import app.datacollect.time.UTCDateTime;
import app.datacollect.twitchdata.feed.common.repository.FeedEntryRowMapper;
import app.datacollect.twitchdata.feed.feed.domain.FeedEntry;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FeedRepository {

  private final NamedParameterJdbcTemplate jdbcTemplate;

  public FeedRepository(NamedParameterJdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public void insertEntries(String table, List<FeedEntry> feedEntries) {
    jdbcTemplate.batchUpdate(
        "INSERT INTO "
            + table
            + " (id, author, event_type, object_type, version, published_date, updated_date, content_type, content) "
            + "VALUES(:id, :author, :event_type, :object_type, :version, :published_date, :updated_date, :content_type, :content)",
        feedEntries.stream()
            .map(this::toParams)
            .collect(Collectors.toList())
            .toArray(new MapSqlParameterSource[feedEntries.size()]));
  }

  public List<FeedEntry> getEntries(String table, int limit) {
    return jdbcTemplate.query(
        "SELECT id, author, event_type, object_type, version, published_date, updated_date, content_type, content "
            + "FROM "
            + table
            + " ORDER BY published_date DESC LIMIT :limit",
        Map.of("limit", limit),
        FeedEntryRowMapper::mapRow);
  }

  public List<FeedEntry> getEntriesFromBeginning(
      String table, int limit, UTCDateTime lastEventTime) {
    return jdbcTemplate.query(
        "SELECT id, author, event_type, object_type, version, published_date, updated_date, content_type, content "
            + "FROM "
            + table
            + " "
            + "WHERE published_date <= :last_event_time "
            + "ORDER BY published_date LIMIT :limit",
        Map.of("limit", limit, "last_event_time", lastEventTime.iso8601()),
        FeedEntryRowMapper::mapRow);
  }

  public List<FeedEntry> getEntriesFromMarker(
      String table, UUID marker, int limit, UTCDateTime lastEventTime) {
    return jdbcTemplate.query(
        "SELECT id, author, event_type, object_type, version, published_date, updated_date, content_type, content "
            + "FROM "
            + table
            + " "
            + "WHERE published_date > (SELECT published_date FROM "
            + table
            + " WHERE id = :marker) AND published_date <= :last_event_time "
            + "ORDER BY published_date "
            + "LIMIT :limit",
        Map.of("marker", marker, "limit", limit, "last_event_time", lastEventTime.iso8601()),
        FeedEntryRowMapper::mapRow);
  }

  public Optional<FeedEntry> getEntry(String table, UUID id) {
    try {
      return Optional.ofNullable(
          jdbcTemplate.queryForObject(
              "SELECT id, author, event_type, object_type, version, published_date, updated_date, content_type, content FROM "
                  + table
                  + " WHERE id = :id",
              Map.of(ID, id),
              FeedEntryRowMapper::mapRow));
    } catch (EmptyResultDataAccessException ex) {
      return Optional.empty();
    }
  }

  private MapSqlParameterSource toParams(FeedEntry feedEntry) {
    return new MapSqlParameterSource()
        .addValue(ID, feedEntry.getId())
        .addValue(AUTHOR, feedEntry.getAuthor())
        .addValue(EVENT_TYPE, feedEntry.getEventType().name())
        .addValue(OBJECT_TYPE, feedEntry.getObjectType().name())
        .addValue(VERSION, feedEntry.getVersion().name())
        .addValue(PUBLISHED_DATE, feedEntry.getPublishedDate().iso8601())
        .addValue(UPDATED_DATE, feedEntry.getUpdatedDate().iso8601())
        .addValue(CONTENT_TYPE, feedEntry.getContentType())
        .addValue(CONTENT, feedEntry.getContent());
  }
}
