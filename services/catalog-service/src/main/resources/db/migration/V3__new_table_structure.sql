alter table items
    alter column name TYPE varchar(255),
    alter column name set not null,
    add column price numeric not null,
    add column description varchar(1000)
;