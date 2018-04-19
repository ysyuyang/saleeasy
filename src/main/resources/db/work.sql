-- 工作日报
drop table if exists tm_work_daily;
create table tm_work_daily
(
   daily_id             varchar(32) not null,
   finish_work_today    varchar(256),
   un_finish_work       varchar(256),
   coordinate_work      varchar(256),
   remark               varchar(256),
   creater              varchar(32),
   create_time          timestamp null default NULL,
   primary key (daily_id)
);

-- 工作日报关联人员
drop table if exists tr_work_daily_relation_user;
create table tr_work_daily_relation_user
(
   relation_id          varchar(32) not null,
   daily_id             varchar(32),
   user_id              varchar(32),
   asso_relation        varchar(16) comment '发送:send;cc:抄送;',
   primary key (relation_id)
);

-- 工作日报操作日志
drop table if exists tt_work_daily_operation_log;
create table tt_work_daily_operation_log
(
   log_id               varchar(32) not null,
   daily_id             varchar(32),
   user_id              varchar(32),
   action               varchar(16),
   create_time          timestamp null default NULL,
   primary key (log_id)
);

-- 工作日报图片
drop table if exists tt_work_daily_pic;
create table tt_work_daily_pic
(
   pic_id               varchar(32) not null,
   daily_id             varchar(32),
   pic_url              varchar(128),
   primary key (pic_id)
);

-- 工作任务
drop table if exists tm_work_task;
create table tm_work_task
(
   task_id              varchar(32) not null,
   task_name            varchar(32),
   start_time           timestamp null default NULL,
   end_time             timestamp null default NULL,
   task_desc            varchar(256),
   creater              varchar(32),
   create_time          timestamp null default NULL,
   primary key (task_id)
);

-- 协调工作
drop table if exists tt_coordinate_work;
create table tt_coordinate_work
(
   work_id              varchar(32) not null,
   task_id              varchar(32),
   work_desc            varchar(128),
   assigner             varchar(32),
   status               varchar(16),
   primary key (work_id)
);
-- 工作任务关联人员
drop table if exists tr_work_task_relation_user;
create table tr_work_task_relation_user
(
   relation_id          varchar(32) not null,
   task_id              varchar(32),
   user_id              varchar(32),
   asso_relation        varchar(16) comment 'sup:上级;coordinate:协调;',
   primary key (relation_id)
);
-- 工作任务图片
drop table if exists tt_work_task_pic;
create table tt_work_task_pic
(
   pic_id               varchar(32) not null,
   task_id              varchar(32),
   pic_url              varchar(128),
   primary key (pic_id)
);
-- 工作任务操作日志
drop table if exists tt_work_task_operation_log;
create table tt_work_task_operation_log
(
   log_id               varchar(32) not null,
   task_id              varchar(32),
   user_id              varchar(32),
   action               varchar(16),
   create_time          timestamp null default NULL,
   primary key (log_id)
);
-- 人员上下级关系表
drop table if exists tr_user_relation;
create table tr_user_relation
(
   relation_id          varchar(32) not null,
   user_id              varchar(32),
   sup_user_id          varchar(32),
   primary key (relation_id)
);

alter table tr_work_daily_relation_user add column is_read tinyint default 0;
-- 工作日报评论
drop table if exists tt_work_daily_comment;
create table tt_work_daily_comment
(
   comment_id           varchar(32) not null,
   daily_id             varchar(32) not null,
   log_id               varchar(32),
   user_id              varchar(32),
   comment              varchar(256),
   create_time          timestamp null default NULL,
   primary key (comment_id)
);



alter table tm_work_task add column status varchar(16) comment 'init:初始化；complate:完成；' after task_desc;
alter table tt_coordinate_work add column sort int;
-- 工作任务评论
drop table if exists tt_work_task_comment;
create table tt_work_task_comment
(
   comment_id           varchar(32) not null,
   task_id              varchar(32),
   log_id               varchar(32),
   user_id              varchar(32),
   comment              varchar(256),
   create_time          timestamp null default NULL,
   primary key (comment_id)
);

alter table tm_work_daily add column app_id varchar(32);
alter table tr_work_daily_relation_user add column app_id varchar(32);
alter table tt_work_daily_operation_log add column app_id varchar(32);
alter table tt_work_daily_comment add column app_id varchar(32);
alter table tt_work_daily_pic add column app_id varchar(32);
alter table tm_work_task add column app_id varchar(32);
alter table tt_work_task_operation_log add column app_id varchar(32);
alter table tt_work_task_comment add column app_id varchar(32);
alter table tt_work_task_pic add column app_id varchar(32);
alter table tr_work_task_relation_user add column app_id varchar(32);
alter table tt_coordinate_work add column app_id varchar(32);

alter table tt_work_daily_operation_log add column asso_id varchar(32) after create_time;
alter table tt_work_task_operation_log add column asso_id varchar(32) after create_time;
alter table tt_work_daily_comment drop column log_id;
alter table tt_work_task_comment drop column log_id;

alter table tt_work_daily_comment add column is_delete tinyint default 0;
alter table tt_work_task_comment add column is_delete tinyint default 0;

alter table tm_work_task add column task_code varchar(32);

-- 2017-11-30
alter table tt_work_task_operation_log add column asso_data varchar(32);


-- index
alter table tt_coordinate_work add index idx_task_id(task_id);
alter table tt_work_daily_comment add index idx_daily_id_is_delete(daily_id, is_delete);
alter table tt_work_daily_operation_log add index idx_daily_id(daily_id);
alter table tt_work_daily_operation_log add index idx_daily_id_action(daily_id, `action`);
alter table tt_work_daily_pic add index idx_daily_id(daily_id);
alter table tr_work_daily_relation_user add index idx_daily_id_asso_relation(daily_id, asso_relation);
alter table tr_work_daily_relation_user add index idx_daily_id_user_id(daily_id, user_id);
alter table tm_work_daily add index idx_app_id(app_id);
alter table tm_work_daily add index idx_app_id_creater(app_id, creater);
alter table tt_work_task_comment add index idx_task_id_is_delete(task_id, is_delete);
alter table tt_work_task_operation_log add index idx_task_id(task_id);
alter table tt_work_task_operation_log add index idx_task_id_action(task_id, `action`);
alter table tt_work_task_pic add index idx_task_id(task_id);
alter table tr_work_task_relation_user add index idx_task_id_asso_relation(task_id, asso_relation);
alter table tm_work_task add index idx_app_id(app_id);
alter table tm_work_task add index idx_app_id_creater(app_id, creater);


alter table tm_work_task add column complate_time timestamp null default NULL after create_time;
alter table tm_work_task add column close_time timestamp null default NULL after complate_time;

alter table tm_work_task add column urgent varchar(16) comment 'high:高; middle:中; low:低' after status;
alter table tm_work_task add column significant varchar(16) comment 'high:高; middle:中; low:低;' after urgent;

alter table tm_work_task add column is_delay bit default 0 comment '0否1是';
alter table tm_work_task add column delay_time timestamp NULL default NULL;

-- 修改任务日志动作长度
alter table tt_work_task_operation_log change action action varchar(32);

alter table tm_work_task add index idx_status(status);

