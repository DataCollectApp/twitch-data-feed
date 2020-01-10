package app.datacollect.twitchdata.feed.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseConfig {
  @Bean("chatMessageFeedConfigProperties")
  @ConfigurationProperties("twitch-data-feed.config.chat-message")
  public FeedConfigProperties getChatMessageFeedConfigProperties() {
    return new FeedConfigProperties();
  }

  @Bean("connectionFeedConfigProperties")
  @ConfigurationProperties("twitch-data-feed.config.connection")
  public FeedConfigProperties getConnectionFeedConfigProperties() {
    return new FeedConfigProperties();
  }

  @Bean("hostFeedConfigProperties")
  @ConfigurationProperties("twitch-data-feed.config.host")
  public FeedConfigProperties getHostFeedConfigProperties() {
    return new FeedConfigProperties();
  }

  @Bean("punishmentFeedConfigProperties")
  @ConfigurationProperties("twitch-data-feed.config.punishment")
  public FeedConfigProperties getPunishmentFeedConfigProperties() {
    return new FeedConfigProperties();
  }
}
