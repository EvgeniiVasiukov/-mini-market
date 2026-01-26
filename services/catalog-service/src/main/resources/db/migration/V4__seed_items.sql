alter table items
    add column category varchar(60),
    add column image_url varchar(255),
    add column is_available boolean default FALSE;