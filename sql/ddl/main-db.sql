CREATE DATABASE IF NOT EXISTS `verby_main`;
use `verby_main`;

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account`
(
    `id`                                   bigint(20) AUTO_INCREMENT PRIMARY KEY,
    `login_id`                             varchar(20)  NOT NULL,
    `password`                             varchar(255) NOT NULL,
    `name`                                 varchar(50)  NOT NULL,
    `birthday`                             date         NULL,
    `phone`                                varchar(13)  NULL,
    `status`                               varchar(30)  NOT NULL,
    `allow_to_marketing_notification` boolean      NOT NULL,
    `created_at`                           datetime     NOT NULL DEFAULT NOW(),
    `updated_at`                           datetime     NULL
);

ALTER TABLE `account` ADD UNIQUE uk_login_id (`login_id`);
ALTER TABLE `account` ADD UNIQUE uk_phone (`phone`);

DROP TABLE IF EXISTS `account_verification_token`;

CREATE TABLE `account_verification_token`
(
    `id`              bigint(20)   AUTO_INCREMENT PRIMARY KEY,
    `account_id`      bigint(20)   NOT NULL,
    `key`             varchar(255) NOT NULL,
    `type`            varchar(20)  NOT NULL,
    `expiration_date` datetime     NULL,
    `created_at`      datetime     NOT NULL DEFAULT NOW()
);


DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    `id`            bigint(20) AUTO_INCREMENT PRIMARY KEY,
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
    `id`         bigint(20) AUTO_INCREMENT PRIMARY KEY,
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
    `id`   bigint(20) AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(50) NOT NULL
);

DROP TABLE IF EXISTS `song`;

CREATE TABLE `song`
(
    `id`        bigint(20) AUTO_INCREMENT PRIMARY KEY,
    `artist_id` bigint(20)   NOT NULL,
    `name`      varchar(50)  NOT NULL,
    `image`     varchar(255) NULL
);

DROP TABLE IF EXISTS `cover`;

CREATE TABLE `cover`
(
    `id`         bigint(20) AUTO_INCREMENT PRIMARY KEY,
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
    `id`          bigint(20) AUTO_INCREMENT PRIMARY KEY,
    `inquirer_id` bigint(20)   NOT NULL,
    `answerer_id` bigint(20)   NOT NULL,
    `title`       varchar(100) NOT NULL,
    `content`     text         NOT NULL,
    `answer`      text         NULL,
    `status`      varchar(30)  NOT NULL,
    `created_at`  datetime     NOT NULL DEFAULT NOW(),
    `answered_at` datetime     NULL
);

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role`
(
    `id`   bigint(20) AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(20) NOT NULL
);

INSERT INTO `role` (name) VALUES
    ('ADMIN'),
    ('MEMBER');

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment`
(
    `id`         bigint(20) AUTO_INCREMENT PRIMARY KEY,
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
    `id`         bigint(20) AUTO_INCREMENT PRIMARY KEY,
    `cover_id`   bigint(20) NOT NULL,
    `user_id`    bigint(20) NOT NULL,
    `created_at` datetime   NOT NULL DEFAULT NOW()
);

DROP TABLE IF EXISTS `comment_like`;

CREATE TABLE `comment_like`
(
    `id`         bigint(20) AUTO_INCREMENT PRIMARY KEY,
    `comment_id` bigint(20) NOT NULL,
    `user_id`    bigint(20) NOT NULL,
    `created_at` datetime   NOT NULL DEFAULT NOW()
);

DROP TABLE IF EXISTS `account_role`;

CREATE TABLE `account_role`
(
    `account_id` bigint(20) NOT NULL,
    `role_id` bigint(20) NOT NULL,
    PRIMARY KEY (`account_id`, `role_id`)
);

DROP TABLE IF EXISTS `notification`;

CREATE TABLE `notification`
(
    `id`          bigint(20) AUTO_INCREMENT PRIMARY KEY,
    `sender_id`   bigint(20)  NOT NULL,
    `receiver_id` bigint(20)  NOT NULL,
    `entity_id`   bigint(20)  NULL,
    `type_id`     bigint(20)  NOT NULL,
    `status`      varchar(30) NOT NULL,
    `created_at`  datetime    NOT NULL
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
    `id`         bigint(20) AUTO_INCREMENT PRIMARY KEY,
    `cover_id`   bigint(20) NOT NULL,
    `user_id`    bigint(20) NOT NULL,
    `reason`     text       NOT NULL,
    `created_at` datetime   NOT NULL DEFAULT NOW()
);

DROP TABLE IF EXISTS `comment_report`;

CREATE TABLE `comment_report`
(
    `id`         bigint(20) AUTO_INCREMENT PRIMARY KEY,
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

DROP TABLE IF EXISTS `Ranking`;

CREATE TABLE `Ranking`
(
    `id`         bigint(20) AUTO_INCREMENT PRIMARY KEY,
    `contest_id` bigint(20) NOT NULL,
    `cover_id`   bigint(20) NOT NULL,
    `rank`       int(11)    NULL,
    `created_at` datetime   NOT NULL
);