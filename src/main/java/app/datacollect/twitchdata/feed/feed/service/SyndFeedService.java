package app.datacollect.twitchdata.feed.feed.service;

import app.datacollect.twitchdata.feed.config.FeedConfigProperties;
import app.datacollect.twitchdata.feed.feed.domain.FeedEntry;
import com.rometools.rome.feed.synd.SyndCategory;
import com.rometools.rome.feed.synd.SyndCategoryImpl;
import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndContentImpl;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndEntryImpl;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SyndFeedService {

  private static final Logger logger = LoggerFactory.getLogger(SyndFeedService.class);

  public SyndFeed createFeed(
      List<FeedEntry> feedEntries, FeedConfigProperties feedConfigProperties) {
    logger.debug("Creating feed with '{}' entries", feedEntries.size());
    SyndFeed syndFeed = new SyndFeedImpl();
    syndFeed.setTitle(feedConfigProperties.getName());
    syndFeed.setDescription(feedConfigProperties.getDescription());
    syndFeed.setAuthor(feedConfigProperties.getAuthor());
    syndFeed.setFeedType("atom_1.0");
    syndFeed.setEntries(feedEntries.stream().map(this::createEntry).collect(Collectors.toList()));
    logger.info("Created feed with '{}' entries", feedEntries.size());
    return syndFeed;
  }

  private SyndEntry createEntry(FeedEntry feedEntry) {
    SyndContent content = new SyndContentImpl();
    content.setType(feedEntry.getContentType());
    content.setValue(feedEntry.getContent());

    SyndEntry syndEntry = new SyndEntryImpl();
    syndEntry.setUri(feedEntry.getId().toString());
    syndEntry.setAuthor(feedEntry.getAuthor());
    syndEntry.setPublishedDate(Date.from(feedEntry.getPublishedDate().getInstant()));
    syndEntry.setUpdatedDate(Date.from(feedEntry.getUpdatedDate().getInstant()));
    syndEntry.setContents(List.of(content));

    syndEntry.setCategories(
        List.of(
            createCategory(feedEntry.getEventType().name(), "eventType"),
            createCategory(feedEntry.getObjectType().name(), "objectType"),
            createCategory(feedEntry.getVersion().name(), "version")));

    return syndEntry;
  }

  private SyndCategory createCategory(String name, String taxonomyUri) {
    final SyndCategory category = new SyndCategoryImpl();
    category.setName(name);
    category.setTaxonomyUri(taxonomyUri);
    return category;
  }
}
