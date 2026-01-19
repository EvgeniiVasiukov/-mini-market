create table if not exists items (
    id bigserial primary key,
    name varchar(255) not null,
    created_at timestamp not null default now()
);