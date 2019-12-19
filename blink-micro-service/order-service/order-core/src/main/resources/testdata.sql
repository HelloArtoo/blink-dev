create database master;
create database foo;
create database bar;

use foo;
drop table if exists demo;
create table demo (id bigint primary key auto_increment, name varchar(255), time timestamp default now());

insert into demo (name) values ('foo1');
insert into demo (name) values ('foo2');
insert into demo (name) values ('foo3');

DROP TABLE IF EXISTS task;
CREATE TABLE task (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  tenant_id varchar(255) DEFAULT NULL COMMENT '租户ID',
  job_name varchar(255) DEFAULT NULL COMMENT '任务名称',
  job_desc varchar(255) DEFAULT NULL COMMENT '任务描述',
  create_time datetime DEFAULT NULL COMMENT '任务创建时间',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  status int(11) DEFAULT NULL COMMENT '任务状态（0：新建；1：成功；2：失败）',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;


use bar;
drop table if exists demo;
create table demo (id bigint primary key auto_increment, name varchar(255), time timestamp default now());

insert into demo (name) values ('bar1');
insert into demo (name) values ('bar2');
insert into demo (name) values ('bar3');
insert into demo (name) values ('bar4');
insert into demo (name) values ('bar5');
insert into demo (name) values ('bar6');

DROP TABLE IF EXISTS task;
CREATE TABLE task (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  tenant_id varchar(255) DEFAULT NULL COMMENT '租户ID',
  job_name varchar(255) DEFAULT NULL COMMENT '任务名称',
  job_desc varchar(255) DEFAULT NULL COMMENT '任务描述',
  create_time datetime DEFAULT NULL COMMENT '任务创建时间',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  status int(11) DEFAULT NULL COMMENT '任务状态（0：新建；1：成功；2：失败）',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

use master;
DROP TABLE IF EXISTS tenant_infos;
CREATE TABLE tenant_infos ( id BIGINT (20) NOT NULL COMMENT '主键', tenant_id VARCHAR (255) DEFAULT NULL COMMENT '租户编号', url VARCHAR (255) DEFAULT NULL COMMENT '数据库url', USER VARCHAR (255) DEFAULT NULL COMMENT '数据库用户名', PASSWORD VARCHAR (255) DEFAULT NULL COMMENT '数据库密码', PRIMARY KEY (id)) ENGINE = INNODB DEFAULT CHARSET = utf8;

INSERT INTO tenant_infos VALUES ( '1', 'foo', 'jdbc:mysql://localhost:3306/foo', 'root', NULL );
INSERT INTO tenant_infos VALUES ( '2', 'bar', 'jdbc:mysql://localhost:3306/bar', 'root', NULL );

DROP TABLE IF EXISTS redis_info;
CREATE TABLE redis_info ( id INT (11) NOT NULL AUTO_INCREMENT COMMENT '主键', tenant_id VARCHAR (255) NOT NULL COMMENT '租户编号', use_type TINYINT (3) NOT NULL DEFAULT '2' COMMENT '使用类型(1:单独，2：混合)', HOST VARCHAR (255) NOT NULL COMMENT 'Redis host', PASSWORD VARCHAR (255) NOT NULL COMMENT 'Redis密码', PORT INT (6) NOT NULL DEFAULT '0' COMMENT 'Redis端口', timeout INT (10) NOT NULL DEFAULT '3000' COMMENT '超时时间', PRIMARY KEY (id)) ENGINE = INNODB AUTO_INCREMENT = 3 DEFAULT CHARSET = utf8;

INSERT INTO redis_info VALUES ('1', 'foo', '1', '10.182.33.172', '/redis/cluster/172', '6379', '3000');
INSERT INTO redis_info VALUES ('2', 'bar', '1', '10.182.33.172', '/redis/cluster/172', '6379', '3000');

