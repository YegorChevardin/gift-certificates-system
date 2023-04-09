CREATE TABLE IF NOT EXISTS tags (
                                   id          IDENTITY NOT NULL PRIMARY KEY UNIQUE,
                                   `value`    VARCHAR(45) NOT NULL UNIQUE
    );

create table gift_certificates
(
    id                  identity           not null primary key UNIQUE,
    name                varchar(45)        not null UNIQUE,
    description         varchar(500),
    duration            integer            not null,
    price               float      not null,
    create_date         timestamp        not null,
    last_update_date    timestamp        not null
);

create table gift_certificates_tags
(
    gift_certificate_id bigint
        references gift_certificates
            on update cascade on delete cascade,
    tag_id              bigint
        references tags
            on update cascade on delete cascade
);