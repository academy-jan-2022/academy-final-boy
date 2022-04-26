create table [activity]
(
    id int identity,
    team_id int,
    name VARCHAR(255) NOT NULL,
    team_key int,
    );

create table [activity_group]
(
    id int identity,
    activity_id int
    );

create table [activity_member]
(
    id int identity,
    group_id int,
    name VARCHAR(255) NOT NULL,
    );
