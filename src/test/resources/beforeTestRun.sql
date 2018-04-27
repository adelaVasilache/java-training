DROP TABLE IF EXISTS post_image;
DROP TABLE IF EXISTS post_tag;
DROP TABLE IF EXISTS posts;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS image;
DROP TABLE IF EXISTS users;

CREATE TABLE users(id BIGINT, email VARCHAR(255), first_name VARCHAR(255), last_name VARCHAR(255),
  password VARCHAR(255));

INSERT INTO users VALUES(1, 'test1@test.test', 'first', 'last', '$2a$10$i3iDcoUDQsiLC/XQMxk3a.mvt2UlmE8H5Ta5WTmrKxzEMRyI4n8hi');
INSERT INTO users VALUES(2, 'test2@test.test', 'first', 'last', '$2a$10$i3iDcoUDQsiLC/XQMxk3a.mvt2UlmE8H5Ta5WTmrKxzEMRyI4n8hi');

CREATE TABLE  posts(id BIGINT, content VARCHAR(255), created BYTEA, grade DOUBLE PRECISION, title VARCHAR(255),
user_id BIGINT REFERENCES users(id));

INSERT INTO posts VALUES(1, 'test content', '�� sr org.joda.time.DateTime�<xdj[��  xr org.joda.time.base.BaseDateTime����O].� J iMillisL iChronologyt Lorg/joda/time/Chronology;xp  c�*�sr ''org.joda.time.chrono.ISOChronology$Stub��fq7P''  xpsr org.joda.time.DateTimeZone$Stub�/�|2�  xpw Europe/Bucharestxx',
8, 'test post1', 1);

INSERT INTO posts VALUES(2, 'test content', '�� sr org.joda.time.DateTime�<xdj[��  xr org.joda.time.base.BaseDateTime����O].� J iMillisL iChronologyt Lorg/joda/time/Chronology;xp  c�*�sr ''org.joda.time.chrono.ISOChronology$Stub��fq7P''  xpsr org.joda.time.DateTimeZone$Stub�/�|2�  xpw Europe/Bucharestxx',
                         9.3, 'test post2', 1);

INSERT INTO posts VALUES(3, 'test content', '�� sr org.joda.time.DateTime�<xdj[��  xr org.joda.time.base.BaseDateTime����O].� J iMillisL iChronologyt Lorg/joda/time/Chronology;xp  c�*�sr ''org.joda.time.chrono.ISOChronology$Stub��fq7P''  xpsr org.joda.time.DateTimeZone$Stub�/�|2�  xpw Europe/Bucharestxx',
                         6, 'test post3', 2);

CREATE TABLE comment(id BIGINT, content VARCHAR(255), created BYTEA, post_id BIGINT REFERENCES posts(id),
user_id BIGINT REFERENCES users(id));

INSERT INTO comment VALUES(1, 'test comment 1', '�� sr org.joda.time.DateTime�<xdj[��  xr org.joda.time.base.BaseDateTime����O].� J iMillisL iChronologyt Lorg/joda/time/Chronology;xp  c�*�sr ''org.joda.time.chrono.ISOChronology$Stub��fq7P''  xpsr org.joda.time.DateTimeZone$Stub�/�|2�  xpw Europe/Bucharestxx',
1, 1);

INSERT INTO comment VALUES(2, 'test comment 2', '�� sr org.joda.time.DateTime�<xdj[��  xr org.joda.time.base.BaseDateTime����O].� J iMillisL iChronologyt Lorg/joda/time/Chronology;xp  c�*�sr ''org.joda.time.chrono.ISOChronology$Stub��fq7P''  xpsr org.joda.time.DateTimeZone$Stub�/�|2�  xpw Europe/Bucharestxx',
                           1, 2);

INSERT INTO comment VALUES(3, 'test comment 3', '�� sr org.joda.time.DateTime�<xdj[��  xr org.joda.time.base.BaseDateTime����O].� J iMillisL iChronologyt Lorg/joda/time/Chronology;xp  c�*�sr ''org.joda.time.chrono.ISOChronology$Stub��fq7P''  xpsr org.joda.time.DateTimeZone$Stub�/�|2�  xpw Europe/Bucharestxx',
                           2, 2);

CREATE TABLE image(id BIGINT, file_name VARCHAR(255), file_path VARCHAR(255));

INSERT INTO image VALUES(1, 'test image1', 'test path');

INSERT INTO image VALUES(2, 'test image2', 'test path');

INSERT INTO image VALUES(3, 'test image3', 'test path');

CREATE TABLE tags(id BIGINT, name VARCHAR(255));

INSERT INTO tags VALUES(1, 'tag1');

INSERT INTO tags VALUES(2, 'tag2');

INSERT INTO tags VALUES(3, 'tag3');

CREATE TABLE post_image(post_id BIGINT, image_id BIGINT);

INSERT INTO post_image VALUES(1, 1);

INSERT INTO post_image VALUES(1, 2);

INSERT INTO post_image VALUES(2, 3);

CREATE TABLE post_tag(post_id BIGINT, tag_id BIGINT);

INSERT INTO post_tag VALUES(1, 1);

INSERT INTO post_tag VALUES(1, 2);

INSERT INTO post_tag VALUES(2, 1);


