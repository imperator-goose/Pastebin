-- CREATE TABLE users (
--                        id BIGINT AUTO_INCREMENT,
--                        username VARCHAR(50) NOT NULL,
--                        status TINYINT DEFAULT 1 NOT NULL,
--                        password VARCHAR(255) NOT NULL,
--                        role VARCHAR(50) NOT NULL,
--                        CONSTRAINT pk_users_id PRIMARY KEY (id),
--                        CONSTRAINT uq_users_username UNIQUE (username),
--                        CONSTRAINT ck_users_status CHECK (status IN (0, 1))
-- );
--
-- CREATE TABLE posts (
--                        id BIGINT AUTO_INCREMENT,
--                        title VARCHAR(255) NOT NULL,
--                        content TEXT NOT NULL,
--                        short_code VARCHAR(20),
--                        author_id BIGINT NOT NULL,
--                        status TINYINT DEFAULT 1 NOT NULL,
--                        views INT DEFAULT 0,
--                        upvotes INT DEFAULT 0,
--                        downvotes INT DEFAULT 0,
--                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
--                        CONSTRAINT pk_posts_id PRIMARY KEY (id),
--                        CONSTRAINT uq_posts_short_code UNIQUE (short_code),
--                        CONSTRAINT ck_posts_status CHECK (status IN (0, 1)),
--                        CONSTRAINT fk_posts_author_id FOREIGN KEY (author_id) REFERENCES users(id) ON UPDATE CASCADE
-- );
--
-- CREATE TABLE comments (
--                           id BIGINT AUTO_INCREMENT,
--                           text TEXT NOT NULL,
--                           post_id BIGINT NOT NULL,
--                           author_id BIGINT NOT NULL,
--                           status TINYINT DEFAULT 1 NOT NULL,
--                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--                           updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
--                           CONSTRAINT pk_comments_id PRIMARY KEY (id),
--                           CONSTRAINT ck_comments_status CHECK (status IN (0, 1)),
--                           CONSTRAINT fk_comments_post_id FOREIGN KEY (post_id) REFERENCES posts(id) ON UPDATE CASCADE,
--                           CONSTRAINT fk_comments_author_id FOREIGN KEY (author_id) REFERENCES users(id) ON UPDATE CASCADE
-- );
-- Таблица users
-- Таблица users
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT,
                       username VARCHAR(50) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(50) NOT NULL,
                       status TINYINT DEFAULT 1 NOT NULL,
                       CONSTRAINT pk_users_id PRIMARY KEY (id),
                       CONSTRAINT uq_users_username UNIQUE (username),
                       CONSTRAINT ck_users_status CHECK (status IN (0, 1))
) ENGINE=InnoDB;

-- Таблица posts
CREATE TABLE posts (
                       id BIGINT AUTO_INCREMENT,
                       title VARCHAR(255) NOT NULL,
                       content TEXT NOT NULL,
                       short_code VARCHAR(20) NOT NULL,
                       author_id BIGINT NOT NULL,
                       status TINYINT DEFAULT 1 NOT NULL,
                       views INT DEFAULT 0 NOT NULL,
                       upvotes INT DEFAULT 0 NOT NULL,
                       downvotes INT DEFAULT 0 NOT NULL,
                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       CONSTRAINT pk_posts_id PRIMARY KEY (id),
                       CONSTRAINT uq_posts_short_code UNIQUE (short_code),
                       CONSTRAINT ck_posts_status CHECK (status IN (0, 1)),
                       CONSTRAINT fk_posts_author_id FOREIGN KEY (author_id) REFERENCES users(id)
) ENGINE=InnoDB;

-- Таблица comments
CREATE TABLE comments (
                          id BIGINT AUTO_INCREMENT,
                          text TEXT NOT NULL,
                          status TINYINT DEFAULT 1 NOT NULL,
                          post_id BIGINT NOT NULL,
                          author_id BIGINT NOT NULL,
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          CONSTRAINT pk_comments_id PRIMARY KEY (id),
                          CONSTRAINT ck_comments_status CHECK (status IN (0, 1)),
                          CONSTRAINT fk_comments_post_id FOREIGN KEY (post_id) REFERENCES posts(id),
                          CONSTRAINT fk_comments_author_id FOREIGN KEY (author_id) REFERENCES users(id)
) ENGINE=InnoDB;