create table [activity]
(
    team_id int,
    name VARCHAR(255) NOT NULL,
    team_key int,
    PRIMARY KEY (team_id, team_key)
    );

create table [activity_group]
(
    team_id int,
    team_key int,
    activity_key int,
    primary key (team_id,team_key,activity_key)
    );

create table [activity_member]
(
    team_id int,
    team_key int,
    activity_key int,
    group_key int,
    name VARCHAR(255) NOT NULL,
    primary key (team_id, team_key, group_key, name)
    );
