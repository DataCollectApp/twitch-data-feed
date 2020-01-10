package app.datacollect.twitchdata.feed.common.repository;

@SuppressWarnings("unused")
public final class Tables {

  public static final class CHAT_MESSAGE_FEED extends FEED {
    public static final String TABLE = "chat_message_feed";
  }

  public static final class CONNECTION_FEED extends FEED {
    public static final String TABLE = "connection_feed";
  }

  public static final class HOST_FEED extends FEED {
    public static final String TABLE = "host_feed";
  }

  public static final class PUNISHMENT_FEED extends FEED {
    public static final String TABLE = "punishment_feed";
  }

  public static class FEED {
    public static final String ID = "id";
    public static final String AUTHOR = "author";
    public static final String EVENT_TYPE = "event_type";
    public static final String OBJECT_TYPE = "object_type";
    public static final String VERSION = "version";
    public static final String PUBLISHED_DATE = "published_date";
    public static final String UPDATED_DATE = "updated_date";
    public static final String CONTENT_TYPE = "content_type";
    public static final String CONTENT = "content";
  }
}
