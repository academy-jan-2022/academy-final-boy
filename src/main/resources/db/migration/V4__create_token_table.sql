create table [token]
(
    id int identity,
    join_id VARCHAR(255) NOT NULL,
    team_id int,
    expiry_date smalldatetime NOT NULL
)
    go
