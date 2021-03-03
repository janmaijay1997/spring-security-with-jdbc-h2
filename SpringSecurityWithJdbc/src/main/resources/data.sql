

insert into users(username,password,enabled) 
values('User','User@123',true);

insert into users(username,password,enabled) 
values('Admin','Admin@123',true);

insert into authorities(username,authority) values('User','ROLE_USER');
insert into authorities(username,authority) values('Admin','ROLE_ADMIN');