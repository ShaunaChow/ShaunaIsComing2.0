create database ShaunaIsComing;

use ShaunaIsComing;

-- 创建用户表
create table users (
  id int primary key  auto_increment,
  mail varchar(255) unique ,
  password varchar(255) ,
  name varchar (255) ,
  home varchar (255) ,
  registdata date ,
  type int
);

create index phoneindex on users (mail);

insert into users
(mail, password, name, home, registdata, type)
values
(17318588134,'$2a$10$UkUja.U8nlOuGqvzmXjWXuI1s15AjYWiOGINoq68afsdUkRSMlv9q','System','/',now(),0);

insert into users
(mail, password, name, home, registdata, type)
values
(15869100891,'$2a$10$AyDuRSqqiEfJ8xbcjhpSNOT3GZIfTMPxbBT84seKRdI3dlyWhTwmi','System','/',now(),0);

insert into users
(mail, password, name, home, registdata, type)
values
(13248139750,'$2a$10$QdRP7yHyBMVRJWaQ/HMPbePCONh168JN8KJNwuEDKRaUWPYK21ILy','System','/',now(),0);

select * from users;

update users set name='User' where id not in (1,2,3);


-- 创建角色表
create table roles (
  id int primary key auto_increment,
  roleName varchar(50) default null,
  description varchar(255) default null,
  createTime timestamp default now(),
  updateTime timestamp default now(),
  unique key unique_roleName (roleName)
);

-- 创建角色用户关联表
create table user_role (
  userId int not null ,
  roleId int not null ,
  createTime timestamp default now(),
  creator varchar(255),
  primary key (userId, roleId)
);

-- 创建权限表
create table permission (
  id int primary key auto_increment,
  code varchar(32) not null comment '权限标识符',
  description varchar(255) default null,
  url varchar(128) not null comment 'url地址'
);

-- 创建角色权限关联表
create table role_permission (
  roleId int not null ,
  permissionId int not null ,
  createTime timestamp default now(),
  creator varchar(255),
  primary key (roleId, permissionId)
);

insert into roles
(roleName,createTime,updateTime)
values
('System',now(),now()),
('User',now(),now());

insert into user_role
(userId,roleId,createTime,creator)
values
(1,1,now(),'ShaunaChow'),
(2,1,now(),'ShaunaChow'),
(3,1,now(),'ShaunaChow'),
(5,2,now(),'ShaunaChow'),
(6,2,now(),'ShaunaChow'),
(7,2,now(),'ShaunaChow');

insert into permission
(code,url)
values
('1','/r/r1'),
('2','/r/r2');

insert into role_permission
(roleId,permissionId)
values
(1,1),
(1,2),
(2,1);

show tables;

select * from permission where id in (
  select permissionId from role_permission where roleId in (
      select roleId from user_role where userId in (
          select id from users where phonenum='17318588134'
      )
  )
);



create table oauth_client_details(
  client_id varchar(255) primary key ,
  resource_ids varchar (255),
  client_secret varchar (255),
  scope varchar (255),
  authorized_grant_types varchar (255),
  web_server_redirect_uri varchar (255),
  authorities varchar (255),
  access_token_validity int(11),
  refresh_token_validity int(11),
  additional_information varchar (255),
  create_time timestamp default now(),
  archived int ,
  trusted int,
  autoapprove varchar (255)
);

INSERT INTO oauth_client_details VALUES ('c1', 'res1',
'$2a$10$9S8Wx5zDJYDE8fUqJgmvJOUSkGHMB0YYhSFFOYZM9JZpjlW5GXPR2', 'ROLE_ADMIN,ROLE_USER,ROLE_API',
'client_credentials,password,authorization_code,implicit,refresh_token', 'http://www.shauna.top',
NULL, 7200, 259200, NULL, now(), 0, 0, 'false'),
('c2', 'res2',
'$2a$10$9S8Wx5zDJYDE8fUqJgmvJOUSkGHMB0YYhSFFOYZM9JZpjlW5GXPR2', 'ROLE_API',
'client_credentials,password,authorization_code,implicit,refresh_token', 'http://www.shauna.top',
NULL, 31536000, 2592000, NULL, now(), 0, 0, 'false');

select * from oauth_client_details;


CREATE TABLE oauth_code (
  create_time timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  code varchar(255),
  authentication blob,
  INDEX  code_index (code) USING BTREE
);


select * from oauth_code;


show tables;








