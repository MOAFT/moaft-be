CREATE DATABASE moaft;
USE moaft;
SET NAMES utf8mb4;

-- 1. 회원 및 프로필
CREATE TABLE `media_asset` (
                               `id`                    BIGINT       NOT NULL AUTO_INCREMENT,
                               `storage_key`           VARCHAR(500) NOT NULL,
                               `original_file_name`    VARCHAR(255) NOT NULL,
                               `mime_type`             VARCHAR(100) NOT NULL,
                               `file_size`             BIGINT       NOT NULL,
                               `media_type`            VARCHAR(20)  NOT NULL,
                               `thumbnail_storage_key` VARCHAR(500) NULL,
                               `upload_status`         VARCHAR(20)  NOT NULL DEFAULT 'PENDING',
                               `created_at`            DATETIME     NOT NULL,
                               `uploaded_by_user_id`   BIGINT       NOT NULL,
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `UK_media_asset_storage_key` (`storage_key`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE `users` (
                         `id`                     BIGINT       NOT NULL AUTO_INCREMENT,
                         `username`               VARCHAR(10)  NOT NULL,
                         `nickname`               VARCHAR(10)  NOT NULL,
                         `social_provider`        VARCHAR(20)  NOT NULL,
                         `oauth_id`               VARCHAR(255) NOT NULL,
                         `email`                  VARCHAR(255) NULL,
                         `profile_image_asset_id` BIGINT       NULL,
                         `bio`                    VARCHAR(150) NULL,
                         `is_profile_public`      BOOLEAN      NOT NULL DEFAULT TRUE,
                         `created_at`             DATETIME     NOT NULL,
                         `updated_at`             DATETIME     NOT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `UK_users_username` (`username`),
                         UNIQUE KEY `UK_users_social` (`social_provider`, `oauth_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE `user_external_link` (
                                      `id`      BIGINT       NOT NULL AUTO_INCREMENT,
                                      `user_id` BIGINT       NOT NULL,
                                      `url`     VARCHAR(500) NOT NULL,
                                      PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- CREATE TABLE `interest_field` (
--     `id`   BIGINT      NOT NULL AUTO_INCREMENT,
--     `name` VARCHAR(30) NOT NULL,
--     PRIMARY KEY (`id`),
--     UNIQUE KEY `UK_interest_field_name` (`name`)
-- ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE `user_interest` (
                                 `user_id`   BIGINT      NOT NULL,
                                 `interest`  VARCHAR(30) NOT NULL,
                                 PRIMARY KEY (`user_id`, `interest`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE `refresh_token` (
                                 `id`         BIGINT       NOT NULL AUTO_INCREMENT,
                                 `user_id`    BIGINT       NOT NULL,
                                 `token`      VARCHAR(512) NOT NULL,
                                 `created_at` DATETIME     NOT NULL,
                                 `expired_at` DATETIME     NOT NULL,
                                 PRIMARY KEY (`id`),
                                 UNIQUE KEY `UK_refresh_token_token` (`token`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE `policy_document` (
                                   `id`            BIGINT      NOT NULL AUTO_INCREMENT,
                                   `document_type` VARCHAR(30) NOT NULL,
                                   `title`         VARCHAR(50) NOT NULL,
                                   `version`       VARCHAR(20) NOT NULL,
                                   `content`       TEXT        NOT NULL,
                                   `is_required`   BOOLEAN     NOT NULL DEFAULT TRUE,
                                   PRIMARY KEY (`id`),
                                   UNIQUE KEY `UK_policy_document_type_version` (`document_type`, `version`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE `user_policy_document` (
                                        `id`                 BIGINT   NOT NULL AUTO_INCREMENT,
                                        `user_id`            BIGINT   NOT NULL,
                                        `policy_document_id` BIGINT   NOT NULL,
                                        `is_agreed`          BOOLEAN  NOT NULL DEfAULT FALSE,
                                        `agreed_at`          DATETIME NOT NULL,
                                        PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- 2. 기록
CREATE TABLE `category` (
                            `id`          BIGINT      NOT NULL AUTO_INCREMENT,
                            `result_type` VARCHAR(20) NOT NULL,
                            `name`        VARCHAR(30) NOT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `UK_category_type_name` (`result_type`, `name`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE `tool` (
                        `id`        BIGINT      NOT NULL AUTO_INCREMENT,
                        `name`      VARCHAR(50) NOT NULL,
                        `is_preset` BOOLEAN     NOT NULL DEFAULT FALSE,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `UK_tool_name` (`name`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE `tag` (
                       `id`   BIGINT      NOT NULL AUTO_INCREMENT,
                       `name` VARCHAR(50) NOT NULL,
                       PRIMARY KEY (`id`),
                       UNIQUE KEY `UK_tag_name` (`name`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE `record` (
                          `id`                    BIGINT      NOT NULL AUTO_INCREMENT,
                          `user_id`               BIGINT      NOT NULL,
                          `record_type`           VARCHAR(20) NOT NULL,
                          `title`                 VARCHAR(50) NULL,
                          `description`           TEXT        NULL,
                          `result_type`           VARCHAR(20) NULL,
                          `category_id`           BIGINT      NULL,
                          `estimated_duration`    INT         NULL,
                          `is_public`             BOOLEAN     NOT NULL DEFAULT FALSE,
                          `is_copyable`           BOOLEAN     NOT NULL DEFAULT FALSE,
                          `status`                VARCHAR(20) NOT NULL DEFAULT 'DRAFT',
                          `copied_from_record_id` BIGINT      NULL,
                          `view_count`            INT         NOT NULL DEFAULT 0,
                          `created_at`            DATETIME    NOT NULL,
                          `updated_at`            DATETIME    NOT NULL,
                          `share_key`             CHAR(36)    NOT NULL,
                          PRIMARY KEY (`id`),
                          UNIQUE KEY UK_record_share_key (share_key)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE `record_tag` (
                              `record_id` BIGINT NOT NULL,
                              `tag_id`    BIGINT NOT NULL,
                              PRIMARY KEY (`record_id`, `tag_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- 3. 제작 과정·변화 과정
CREATE TABLE `production_step` (
                                   `id`          BIGINT      NOT NULL AUTO_INCREMENT,
                                   `record_id`   BIGINT      NOT NULL,
                                   `name`        VARCHAR(50) NULL,
                                   `description` TEXT        NULL,
                                   `step_order`  INT         NOT NULL DEFAULT 0,
                                   PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE `production_step_tool` (
                                        `production_step_id` BIGINT NOT NULL,
                                        `tool_id`            BIGINT NOT NULL,
                                        PRIMARY KEY (`production_step_id`, `tool_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE `variation_item` (
                                  `id`              BIGINT      NOT NULL AUTO_INCREMENT,
                                  `record_id`       BIGINT      NOT NULL,
                                  `name`            VARCHAR(50) NULL,
                                  `description`     TEXT        NULL,
                                  `tool_id`         BIGINT      NULL,
                                  `variation_order` INT         NOT NULL DEFAULT 0,
                                  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE `prompt` (
                          `id`                 BIGINT NOT NULL AUTO_INCREMENT,
                          `production_step_id` BIGINT NULL,
                          `variation_item_id`  BIGINT NULL,
                          `content`            TEXT   NULL,
                          `description`        TEXT   NULL,
                          `prompt_order`       INT    NOT NULL DEFAULT 0,
                          PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- 4. 기록 첨부 자료
CREATE TABLE `record_material` (
                                   `id`                 BIGINT        NOT NULL AUTO_INCREMENT,
                                   `record_id`          BIGINT        NOT NULL,
                                   `media_asset_id`     BIGINT        NULL,
                                   `production_step_id` BIGINT        NULL,
                                   `variation_item_id`  BIGINT        NULL,
                                   `material_type`      VARCHAR(20)   NOT NULL ,
                                   `material_role`      VARCHAR(30)   NOT NULL,
                                   `text_content`       TEXT          NULL,
                                   `external_url`       VARCHAR(2000) NULL,
                                   `display_order`      INT           NOT NULL DEFAULT 0,
                                   PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- 5. 보관함·기록 간 관계
CREATE TABLE `saved_record` (
                                `id`        BIGINT   NOT NULL AUTO_INCREMENT,
                                `user_id`   BIGINT   NOT NULL,
                                `record_id` BIGINT   NULL,
                                `saved_at`  DATETIME NOT NULL,
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `UK_saved_record_user_record` (`user_id`, `record_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE `record_reference` (
                                    `id`               BIGINT   NOT NULL AUTO_INCREMENT,
                                    `target_record_id` BIGINT   NOT NULL,
                                    `source_record_id` BIGINT   NULL,
                                    `snapshot_data`    JSON     NULL,
                                    `referenced_at`    DATETIME NOT NULL,
                                    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- 6. AI 분석
CREATE TABLE `ai_analysis_job` (
                                   `id`            BIGINT       NOT NULL AUTO_INCREMENT,
                                   `user_id`       BIGINT       NOT NULL,
                                   `record_id`     BIGINT       NULL,
                                   `analysis_type` VARCHAR(20)  NOT NULL,
                                   `status`        VARCHAR(20)  NOT NULL,
                                   `error_message` VARCHAR(500) NULL,
                                   `requested_at`  DATETIME     NOT NULL,
                                   `completed_at`  DATETIME     NULL,
                                   `result_data`   JSON         NULL,
                                   PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- 엔티티 6-2.
-- CREATE TABLE `ai_analysis_candidate` (
--     `id`              BIGINT      NOT NULL AUTO_INCREMENT,
--     `analysis_job_id` BIGINT      NOT NULL,
--     `candidate_type`  VARCHAR(30) NOT NULL,
--     `candidate_data`  JSON        NOT NULL,
--     `is_selected`     BOOLEAN     NOT NULL DEFAULT FALSE,
--     `sort_order`      INT         NOT NULL DEFAULT 0,
--     PRIMARY KEY (`id`)
-- ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;


-- 외래키 (ERD 내보내기에서 6개만 있었음 → 전체 추가)
-- 회원
ALTER TABLE `users`
    ADD CONSTRAINT `FK_users_profile_image` FOREIGN KEY (`profile_image_asset_id`)
        REFERENCES `media_asset` (`id`) ON DELETE SET NULL;

ALTER TABLE `media_asset`
    ADD CONSTRAINT `FK_media_asset_user` FOREIGN KEY (`uploaded_by_user_id`)
        REFERENCES `users` (`id`) ON DELETE CASCADE;

ALTER TABLE `user_external_link`
    ADD CONSTRAINT `FK_user_external_link_user` FOREIGN KEY (`user_id`)
        REFERENCES `users` (`id`) ON DELETE CASCADE;

ALTER TABLE `user_interest`
    ADD CONSTRAINT `FK_user_interest_user` FOREIGN KEY (`user_id`)
        REFERENCES `users` (`id`) ON DELETE CASCADE;

ALTER TABLE `refresh_token`
    ADD CONSTRAINT `FK_refresh_token_user` FOREIGN KEY (`user_id`)
        REFERENCES `users` (`id`) ON DELETE CASCADE;

ALTER TABLE `user_policy_document`
    ADD CONSTRAINT `FK_user_policy_document_user` FOREIGN KEY (`user_id`)
        REFERENCES `users` (`id`) ON DELETE CASCADE,
    ADD CONSTRAINT `FK_user_policy_document_document` FOREIGN KEY (`policy_document_id`)
        REFERENCES `policy_document` (`id`) ON DELETE RESTRICT;

-- 기록
ALTER TABLE `record`
    ADD CONSTRAINT `FK_record_user` FOREIGN KEY (`user_id`)
        REFERENCES `users` (`id`) ON DELETE CASCADE,
    ADD CONSTRAINT `FK_record_category` FOREIGN KEY (`category_id`)
        REFERENCES `category` (`id`) ON DELETE RESTRICT,
    ADD CONSTRAINT `FK_record_copied_from` FOREIGN KEY (`copied_from_record_id`)
        REFERENCES `record` (`id`) ON DELETE SET NULL;

ALTER TABLE `record_tag`
    ADD CONSTRAINT `FK_record_tag_record` FOREIGN KEY (`record_id`)
        REFERENCES `record` (`id`) ON DELETE CASCADE,
    ADD CONSTRAINT `FK_record_tag_tag` FOREIGN KEY (`tag_id`)
        REFERENCES `tag` (`id`) ON DELETE RESTRICT;

-- 제작 과정·변화 과정
ALTER TABLE `production_step`
    ADD CONSTRAINT `FK_production_step_record` FOREIGN KEY (`record_id`)
        REFERENCES `record` (`id`) ON DELETE CASCADE;

ALTER TABLE `production_step_tool`
    ADD CONSTRAINT `FK_production_step_tool_step` FOREIGN KEY (`production_step_id`)
        REFERENCES `production_step` (`id`) ON DELETE CASCADE,
    ADD CONSTRAINT `FK_production_step_tool_tool` FOREIGN KEY (`tool_id`)
        REFERENCES `tool` (`id`) ON DELETE RESTRICT;

ALTER TABLE `variation_item`
    ADD CONSTRAINT `FK_variation_item_record` FOREIGN KEY (`record_id`)
        REFERENCES `record` (`id`) ON DELETE CASCADE,
    ADD CONSTRAINT `FK_variation_item_tool` FOREIGN KEY (`tool_id`)
        REFERENCES `tool` (`id`) ON DELETE RESTRICT;

ALTER TABLE `prompt`
    ADD CONSTRAINT `FK_prompt_production_step` FOREIGN KEY (`production_step_id`)
        REFERENCES `production_step` (`id`) ON DELETE CASCADE,
    ADD CONSTRAINT `FK_prompt_variation_item` FOREIGN KEY (`variation_item_id`)
        REFERENCES `variation_item` (`id`) ON DELETE CASCADE;

-- 기록 첨부 자료
ALTER TABLE `record_material`
    ADD CONSTRAINT `FK_record_material_record` FOREIGN KEY (`record_id`)
        REFERENCES `record` (`id`) ON DELETE CASCADE,
    ADD CONSTRAINT `FK_record_material_media` FOREIGN KEY (`media_asset_id`)
        REFERENCES `media_asset` (`id`) ON DELETE RESTRICT,
    ADD CONSTRAINT `FK_record_material_production_step` FOREIGN KEY (`production_step_id`)
        REFERENCES `production_step` (`id`) ON DELETE CASCADE,
    ADD CONSTRAINT `FK_record_material_variation_item` FOREIGN KEY (`variation_item_id`)
        REFERENCES `variation_item` (`id`) ON DELETE CASCADE;

-- 보관함·기록 간 관계
ALTER TABLE `saved_record`
    ADD CONSTRAINT `FK_saved_record_user` FOREIGN KEY (`user_id`)
        REFERENCES `users` (`id`) ON DELETE CASCADE,
    ADD CONSTRAINT `FK_saved_record_record` FOREIGN KEY (`record_id`)
        REFERENCES `record` (`id`) ON DELETE SET NULL;

ALTER TABLE `record_reference`
    ADD CONSTRAINT `FK_record_reference_target` FOREIGN KEY (`target_record_id`)
        REFERENCES `record` (`id`) ON DELETE CASCADE,
    ADD CONSTRAINT `FK_record_reference_source` FOREIGN KEY (`source_record_id`)
        REFERENCES `record` (`id`) ON DELETE SET NULL;

-- AI 분석
ALTER TABLE `ai_analysis_job`
    ADD CONSTRAINT `FK_ai_analysis_job_user` FOREIGN KEY (`user_id`)
        REFERENCES `users` (`id`) ON DELETE CASCADE,
    ADD CONSTRAINT `FK_ai_analysis_job_record` FOREIGN KEY (`record_id`)
        REFERENCES `record` (`id`) ON DELETE CASCADE;

-- ai_analysis_candidate 사용 시
-- ALTER TABLE `ai_analysis_candidate`
--     ADD CONSTRAINT `FK_ai_analysis_candidate_job` FOREIGN KEY (`analysis_job_id`)
--         REFERENCES `ai_analysis_job` (`id`) ON DELETE CASCADE;

