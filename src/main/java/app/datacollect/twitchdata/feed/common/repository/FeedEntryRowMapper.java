package app.datacollect.twitchdata.feed.common.repository;

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
import app.datacollect.twitchdata.feed.events.EventType;
import app.datacollect.twitchdata.feed.events.ObjectType;
import app.datacollect.twitchdata.feed.events.Version;
import app.datacollect.twitchdata.feed.feed.domain.FeedEntry;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public final class FeedEntryRowMapper {

  public static FeedEntry mapRow(ResultSet resultSet, int rowNum) throws SQLException {
    return new FeedEntry(
        UUID.fromString(resultSet.getString(ID)),
        resultSet.getString(AUTHOR),
        EventType.valueOf(resultSet.getString(EVENT_TYPE)),
        ObjectType.valueOf(resultSet.getString(OBJECT_TYPE)),
        Version.valueOf(resultSet.getString(VERSION)),
        UTCDateTime.of(resultSet.getString(PUBLISHED_DATE)),
        UTCDateTime.of(resultSet.getString(UPDATED_DATE)),
        resultSet.getString(CONTENT_TYPE),
        resultSet.getString(CONTENT));
  }
}
