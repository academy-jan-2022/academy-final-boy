create table [participant]
(
    id          int identity,
    external_id VARCHAR (255) not null,
    full_name   VARCHAR (255) not null,
    username    VARCHAR (255) not null
)
    go
