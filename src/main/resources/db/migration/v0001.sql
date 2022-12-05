--liquibase formatted sql

--changeset trungtmnguyen:1
create table users
(
    id            serial  not null
        constraint users_pk
                    primary key,
    username        varchar not null unique,
    enabled         boolean default true,
    first_name      varchar not null,
    middle_name     varchar,
    last_name       varchar not null,
    date_of_birth   date,
    phone_number    varchar not null,
    address          varchar,
    hometown        varchar,
    email           varchar not null unique,
    external_id     varchar,
    attributes      jsonb,
    version         int,
    created_by      varchar,
    created_date    timestamp,
    updated_by      varchar,
    updated_date    timestamp
);