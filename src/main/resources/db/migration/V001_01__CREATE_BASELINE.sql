CREATE TABLE chat_message_feed
(
    id             UUID PRIMARY KEY,
    author         VARCHAR(32) NOT NULL,
    event_type     VARCHAR(32) NOT NULL,
    object_type    VARCHAR(32) NOT NULL,
    version        VARCHAR(8)  NOT NULL,
    published_date VARCHAR(64) NOT NULL,
    updated_date   VARCHAR(64) NOT NULL,
    content_type   VARCHAR(32) NOT NULL,
    content        TEXT        NOT NULL
);