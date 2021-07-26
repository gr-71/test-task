drop table if exists items cascade;

create table items (id bigserial, name varchar (255), score int not null, primary key (id));

insert into items (name, score) values
                                        ('Item 001', 789),
                                        ('Item 002', 123),
                                        ('Item 003', 564),
                                        ('Item 004', 465),
                                        ('Item 005', 854);