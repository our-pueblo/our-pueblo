use pueblo_db;
drop table if exists departments;
insert into departments (name)
values ('Test Department');
drop table if exists request_status;
insert into request_status(id, status) VALUES (101, 'Test Request Status'), (201, 'Translated');
drop table if exists translation_status;
insert into translation_status (id, status) VALUES (101, 'Waiting for Admin Check'), (201, 'Approved by Admin');