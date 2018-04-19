-- drop table if exists tt_salary_review;
/*==============================================================*/
/* Table: tt_salary_review(工资审核表)                                      */
/*==============================================================*/
create table tt_salary_review
(
   review_id            varchar(32) not null,
   programme_num        varchar(255),
   review_status        varchar(16),
   review_explain       varchar(256),
   creater              varchar(32),
   create_time          timestamp NULL default NULL,
   primary key (review_id)
);
alter table tt_salary_review add index idx_programme_num(programme_num);
alter table tt_salary_review add column app_id varchar(32);

-- alter table tt_salary_review add column review_amount decimal(10,2) after review_status;

-- drop table if exists tt_salary_review_history;
/*==============================================================*/
/* Table: tt_salary_review_history(工资审核历史表)                              */
/*==============================================================*/
create table tt_salary_review_history
(
   history_id           varchar(32) not null,
   review_id            varchar(32),
   programme_num        varchar(255),
   review_status        varchar(16),
   review_explain       varchar(256),
   creater              varchar(32),
   create_time          timestamp NULL default NULL,
   app_id               varchar(32),
   primary key (history_id)
);

insert into tt_salary_review_history(history_id, review_id, programme_num, review_status, review_explain, creater, create_time, app_id)
select review_id, review_id, programme_num, review_status, review_explain, creater, create_time, app_id 
from tt_salary_review;

