-- dog breed
create table breed
(
    id        int auto_increment primary key,
    name      varchar(255) not null,
    create_by varchar(255) not null
);

-- dog sub breed
create table sub_breed
(
    id        int auto_increment primary key,
    name      varchar(255) not null,
    breed_id  int          not null,
    create_by varchar(255) not null,
    foreign key (breed_id) references breed (id)
);