# twitch-data-feed
Application to expose data collected from Twitch

### Environment variables
The following properties are required to run the application:  

| Name  | Description |  
| ------------- | ------------- |  
| SPRING_SECURITY_USER_NAME | Username for accessing endpoints  |  
| SPRING_SECURITY_USER_PASSWORD | Password for accessing endpoints  |  
| SPRING_DATASOURCE_URL | Database URL (Postgres  supported)  |  
| SPRING_DATASOURCE_USERNAME | Database username  |  
| SPRING_DATASOURCE_PASSWORD | Database password  |
| TWITCH_DATA_FEED_CONFIG_CHAT_MESSAGE_NAME | Name of the chat message feed |
| TWITCH_DATA_FEED_CONFIG_CHAT_MESSAGE_DESCRIPTION | Description of the chat message feed |
| TWITCH_DATA_FEED_CONFIG_CHAT_MESSAGE_AUTHOR | Author of the chat message feed |
| TWITCH_DATA_FEED_CONFIG_CONNECTION_NAME | Name of the connection feed |
| TWITCH_DATA_FEED_CONFIG_CONNECTION_DESCRIPTION | Description of the connection feed |
| TWITCH_DATA_FEED_CONFIG_CONNECTION_AUTHOR | Author of the connection feed |
| TWITCH_DATA_FEED_CONFIG_HOST_NAME | Name of the host feed |
| TWITCH_DATA_FEED_CONFIG_HOST_DESCRIPTION | Description of the host feed |
| TWITCH_DATA_FEED_CONFIG_HOST_AUTHOR | Author of the host feed |
| TWITCH_DATA_FEED_CONFIG_PUNISHMENT_NAME | Name of the punishment feed |
| TWITCH_DATA_FEED_CONFIG_PUNISHMENT_DESCRIPTION | Description of the punishment feed |
| TWITCH_DATA_FEED_CONFIG_PUNISHMENT_AUTHOR | Author of the punishment feed |

### Database migration
The application manages the database schema with Flyway.