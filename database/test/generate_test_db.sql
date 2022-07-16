CREATE DATABASE IF NOT EXISTS `verby_main`;
use `verby_main`;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    `id`            bigint(20)   NOT NULL,
    `account_id`    bigint(20)   NOT NULL,
    `nickname`      varchar(50)  NOT NULL,
    `bio`           varchar(255) NULL,
    `profile_image` varchar(255) NULL,
    `created_at`    datetime     NOT NULL DEFAULT NOW(),
    `updated_at`    datetime     NULL
);

DROP TABLE IF EXISTS `contest`;

CREATE TABLE `contest`
(
    `id`         bigint(20) NOT NULL,
    `song_id`    bigint(20) NOT NULL,
    `content`    text       NOT NULL,
    `round`      int(11)    NOT NULL,
    `start_time` datetime   NOT NULL,
    `end_time`   datetime   NOT NULL,
    `created_at` datetime   NOT NULL DEFAULT NOW(),
    `updated_at` datetime   NULL
);

DROP TABLE IF EXISTS `artist`;

CREATE TABLE `artist`
(
    `id`   bigint(20)  NOT NULL,
    `name` varchar(50) NOT NULL
);

DROP TABLE IF EXISTS `song`;

CREATE TABLE `song`
(
    `id`        bigint(20)   NOT NULL,
    `artist_id` bigint(20)   NOT NULL,
    `name`      varchar(50)  NOT NULL,
    `image`     varchar(255) NULL
);

DROP TABLE IF EXISTS `cover`;

CREATE TABLE `cover`
(
    `id`         bigint(20)   NOT NULL,
    `contest_id` bigint(20)   NOT NULL,
    `user_id`    bigint(20)   NOT NULL,
    `title`      varchar(100) NOT NULL,
    `video`      varchar(255) NOT NULL,
    `highlight`  varchar(255) NULL,
    `image`      varchar(255) NULL,
    `hits`       bigint(20)   NOT NULL DEFAULT 0,
    `created_at` datetime     NOT NULL DEFAULT NOW(),
    `updated_at` datetime     NULL
);

DROP TABLE IF EXISTS `inquiry`;

CREATE TABLE `inquiry`
(
    `id`          bigint(20)   NOT NULL,
    `inquirer_id` bigint(20)   NOT NULL,
    `answerer_id` bigint(20)   NOT NULL,
    `title`       varchar(100) NOT NULL,
    `content`     text         NOT NULL,
    `answer`      text         NULL,
    `status_id`   bigint(20)   NOT NULL,
    `created_at`  datetime     NOT NULL DEFAULT NOW(),
    `answered_at` datetime     NULL
);

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role`
(
    `id`   bigint(20)  NOT NULL,
    `name` varchar(20) NOT NULL
);

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment`
(
    `id`         bigint(20) NOT NULL,
    `cover_id`   bigint(20) NOT NULL,
    `user_id`    bigint(20) NOT NULL,
    `content`    text       NOT NULL,
    `reply_to`   bigint(20) NULL,
    `created_at` datetime   NOT NULL DEFAULT NOW(),
    `updated_at` datetime   NULL
);

DROP TABLE IF EXISTS `cover_like`;

CREATE TABLE `cover_like`
(
    `id`         bigint(20) NOT NULL,
    `cover_id`   bigint(20) NOT NULL,
    `user_id`    bigint(20) NOT NULL,
    `created_at` datetime   NOT NULL DEFAULT NOW()
);

DROP TABLE IF EXISTS `comment_like`;

CREATE TABLE `comment_like`
(
    `id`         bigint(20) NOT NULL,
    `comment_id` bigint(20) NOT NULL,
    `user_id`    bigint(20) NOT NULL,
    `created_at` datetime   NOT NULL DEFAULT NOW()
);

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role`
(
    `user_id` bigint(20) NOT NULL,
    `role_id` bigint(20) NOT NULL
);

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account`
(
    `id`                                   bigint(20)   NOT NULL,
    `login_id`                             varchar(20)  NOT NULL,
    `password`                             varchar(255) NOT NULL,
    `name`                                 varchar(50)  NOT NULL,
    `birthday`                             date         NULL,
    `phone`                                varchar(13)  NOT NULL,
    `is_allowed_to_marketing_notification` boolean      NOT NULL,
    `is_limited`                           boolean      NOT NULL,
    `created_at`                           datetime     NOT NULL DEFAULT NOW(),
    `updated_at`                           datetime     NULL
);

DROP TABLE IF EXISTS `notification`;

CREATE TABLE `notification`
(
    `id`          bigint(20) NOT NULL,
    `sender_id`   bigint(20) NOT NULL,
    `receiver_id` bigint(20) NOT NULL,
    `entity_id`   bigint(20) NULL,
    `type_id`     bigint(20) NOT NULL,
    `status_id`   bigint(20) NOT NULL,
    `created_at`  datetime   NOT NULL
);

DROP TABLE IF EXISTS `app_version`;

CREATE TABLE `app_version`
(
    `id`      bigint(20)  NOT NULL,
    `release` varchar(20) NOT NULL,
    `minimum` varchar(20) NOT NULL
);

DROP TABLE IF EXISTS `cover_report`;

CREATE TABLE `cover_report`
(
    `id`         bigint(20) NOT NULL,
    `cover_id`   bigint(20) NOT NULL,
    `user_id`    bigint(20) NOT NULL,
    `reason`     text       NOT NULL,
    `created_at` datetime   NOT NULL DEFAULT NOW()
);

DROP TABLE IF EXISTS `comment_report`;

CREATE TABLE `comment_report`
(
    `id`         bigint(20) NOT NULL,
    `comment_id` bigint(20) NOT NULL,
    `user_id`    bigint(20) NOT NULL,
    `reason`     text       NOT NULL,
    `created_at` datetime   NOT NULL DEFAULT NOW()
);

DROP TABLE IF EXISTS `notification_type`;

CREATE TABLE `notification_type`
(
    `id`   bigint(20)  NOT NULL,
    `name` varchar(30) NOT NULL
);

DROP TABLE IF EXISTS `notification_status`;

CREATE TABLE `notification_status`
(
    `id`   bigint(20)  NOT NULL,
    `name` varchar(50) NOT NULL
);

DROP TABLE IF EXISTS `inquiry_status`;

CREATE TABLE `inquiry_status`
(
    `id`   bigint(20) NOT NULL,
    `name` bigint(20) NOT NULL
);

DROP TABLE IF EXISTS `Ranking`;

CREATE TABLE `Ranking`
(
    `id`         bigint(20) NOT NULL,
    `contest_id` bigint(20) NOT NULL,
    `cover_id`   bigint(20) NOT NULL,
    `rank`       int(11)    NULL,
    `created_at` datetime   NOT NULL
);

ALTER TABLE `user`
    ADD CONSTRAINT `PK_USER` PRIMARY KEY (
                                          `id`
        );

ALTER TABLE `contest`
    ADD CONSTRAINT `PK_CONTEST` PRIMARY KEY (
                                             `id`
        );

ALTER TABLE `artist`
    ADD CONSTRAINT `PK_ARTIST` PRIMARY KEY (
                                            `id`
        );

ALTER TABLE `song`
    ADD CONSTRAINT `PK_SONG` PRIMARY KEY (
                                          `id`
        );

ALTER TABLE `cover`
    ADD CONSTRAINT `PK_COVER` PRIMARY KEY (
                                           `id`
        );

ALTER TABLE `inquiry`
    ADD CONSTRAINT `PK_INQUIRY` PRIMARY KEY (
                                             `id`
        );

ALTER TABLE `role`
    ADD CONSTRAINT `PK_ROLE` PRIMARY KEY (
                                          `id`
        );

ALTER TABLE `comment`
    ADD CONSTRAINT `PK_COMMENT` PRIMARY KEY (
                                             `id`
        );

ALTER TABLE `cover_like`
    ADD CONSTRAINT `PK_COVER_LIKE` PRIMARY KEY (
                                                `id`
        );

ALTER TABLE `comment_like`
    ADD CONSTRAINT `PK_COMMENT_LIKE` PRIMARY KEY (
                                                  `id`
        );

ALTER TABLE `user_role`
    ADD CONSTRAINT `PK_USER_ROLE` PRIMARY KEY (
                                               `user_id`,
                                               `role_id`
        );

ALTER TABLE `account`
    ADD CONSTRAINT `PK_ACCOUNT` PRIMARY KEY (
                                             `id`
        );

ALTER TABLE `notification`
    ADD CONSTRAINT `PK_NOTIFICATION` PRIMARY KEY (
                                                  `id`
        );

ALTER TABLE `app_version`
    ADD CONSTRAINT `PK_APP_VERSION` PRIMARY KEY (
                                                 `id`
        );

ALTER TABLE `cover_report`
    ADD CONSTRAINT `PK_COVER_REPORT` PRIMARY KEY (
                                                  `id`
        );

ALTER TABLE `comment_report`
    ADD CONSTRAINT `PK_COMMENT_REPORT` PRIMARY KEY (
                                                    `id`
        );

ALTER TABLE `notification_type`
    ADD CONSTRAINT `PK_NOTIFICATION_TYPE` PRIMARY KEY (
                                                       `id`
        );

ALTER TABLE `notification_status`
    ADD CONSTRAINT `PK_NOTIFICATION_STATUS` PRIMARY KEY (
                                                         `id`
        );

ALTER TABLE `inquiry_status`
    ADD CONSTRAINT `PK_INQUIRY_STATUS` PRIMARY KEY (
                                                    `id`
        );

ALTER TABLE `Ranking`
    ADD CONSTRAINT `PK_RANKING` PRIMARY KEY (
                                             `id`
        );