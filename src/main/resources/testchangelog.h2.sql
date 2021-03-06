--liquibase formatted sql

--changeset andrius.kliunka:1596107929497-1
CREATE SEQUENCE HIBERNATE_SEQUENCE;

--changeset andrius.kliunka:1596107929497-2
CREATE TABLE BUILDING (BUILDING_ID BIGINT NOT NULL, BUILDING_SIZE DOUBLE(17) NOT NULL, CITY VARCHAR(255), MARKET_VALUE DOUBLE(17) NOT NULL, PROPERTY_TYPE VARCHAR(255) NOT NULL, STREET VARCHAR(255), STREET_NO VARCHAR(255), OWNER_ID BIGINT, CONSTRAINT CONSTRAINT_D PRIMARY KEY (BUILDING_ID));

--changeset andrius.kliunka:1596107929497-3
CREATE TABLE OWNER (OWNER_ID BIGINT NOT NULL, NAME VARCHAR(255), SURNAME VARCHAR(255), CONSTRAINT CONSTRAINT_4 PRIMARY KEY (OWNER_ID));

--changeset andrius.kliunka:1596107929497-4
CREATE INDEX FK6P8FPY95P6I0WT8ES00ANTYQE_INDEX_D ON BUILDING(OWNER_ID);

--changeset andrius.kliunka:1596107929497-5
ALTER TABLE BUILDING ADD CONSTRAINT FK6P8FPY95P6I0WT8ES00ANTYQE FOREIGN KEY (OWNER_ID) REFERENCES OWNER (OWNER_ID) ON UPDATE RESTRICT ON DELETE RESTRICT;

