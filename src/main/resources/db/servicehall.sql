-- drop table if exists tm_column;
/*==============================================================*/
/* Table: tm_column(栏目表)                                             */
/*==============================================================*/
create table tm_column
(
   column_id            varchar(32) not null,
   name                 varchar(16),
   pic                  varchar(32),
   column_desc          varchar(256),
   is_show              tinyint,
   creater              varchar(32),
   create_time          timestamp NULL default NULL,
   is_delete            tinyint,
   app_id               varchar(32),
   primary key (column_id)
);

-- drop table if exists tm_article;
/*==============================================================*/
/* Table: tm_article(文章表)                                            */
/*==============================================================*/
create table tm_article
(
   article_id           varchar(32) not null,
   column_id            varchar(32),
   title                varchar(32),
   introduction         varchar(256),
   pic                  varchar(32),
   start_time           timestamp NULL default NULL,
   end_time             timestamp NULL default NULL,
   content_type         varchar(12) comment 'image-text:图文;video:视频;resources:外部资源;',
   resources_id         varchar(32),
   creater              varchar(32),
   create_time          timestamp NULL default NULL,
   is_delete            tinyint,
   app_id               varchar(32),
   primary key (article_id)
);

-- drop table if exists tm_faq_type;
/*==============================================================*/
/* Table: tm_faq_type(问题类型)                                           */
/*==============================================================*/
create table tm_faq_type
(
   type_id              varchar(32) not null,
   column_id            varchar(32),
   name                 varchar(16),
   code                 varchar(16),
   creater              varchar(32),
   create_time          timestamp NULL default NULL,
   is_delete            tinyint,
   app_id               varchar(32),
   primary key (type_id)
);

-- drop table if exists tt_faq;
/*==============================================================*/
/* Table: tt_faq(问答中心)                                                */
/*==============================================================*/
create table tt_faq
(
   faq_id               varchar(32) not null,
   type_id              varchar(32),
   column_id            varchar(32),
   faq_content          varchar(128),
   creater              varchar(32),
   create_time          timestamp NULL default NULL,
   is_delete            tinyint,
   app_id               varchar(32),
   primary key (faq_id)
);

-- drop table if exists tt_article_operation_log;
/*==============================================================*/
/* Table: tt_article_operation_log(文章操作日志)                              */
/*==============================================================*/
create table tt_article_operation_log
(
   log_id               varchar(32) not null,
   article_id           varchar(32),
   user_id              varchar(32),
   action               varchar(16),
   create_time          timestamp NULL default NULL,
   asso_id              varchar(32),
   app_id               varchar(32),
   primary key (log_id)
);

-- drop table if exists tt_article_comment;
/*==============================================================*/
/* Table: tt_article_comment(文章评论)                                    */
/*==============================================================*/
create table tt_article_comment
(
   comment_id           varchar(32) not null,
   article_id           varchar(32),
   user_id              varchar(32),
   comment              varchar(256),
   create_time          timestamp NULL default NULL,
   app_id               varchar(32),
   is_delete            tinyint default 0,
   primary key (comment_id)
);

-- drop table if exists tt_faq_reply;
/*==============================================================*/
/* Table: tt_faq_reply(问答中心回复)                                          */
/*==============================================================*/
create table tt_faq_reply
(
   reply_id             varchar(32) not null,
   faq_id               varchar(32),
   reply_content        varchar(256),
   user_id              varchar(32),
   create_time          timestamp NULL default NULL,
   app_id               varchar(32),
   is_delete            tinyint default 0,
   primary key (reply_id)
);



alter table tm_article add column is_publish tinyint default 0 comment '0否1是' after create_time;
alter table tm_article add column publish_time timestamp NULL default NULL after is_publish;

alter table tm_column change pic pic varchar(128);
alter table tm_article change pic pic varchar(128);

-- index
alter table tt_article_comment add index idx_article_id(article_id);
alter table tt_article_comment add index idx_article_id_app_id_is_delete(article_id, app_id, is_delete);
alter table tt_article_operation_log add index idx_article_id_app_id_action(article_id, app_id, `action`);
alter table tm_article add index idx_column_id_is_delete_is_publish(column_id, is_delete, is_publish);
alter table tm_article add index idx_app_id_is_delete(app_id, is_delete);
alter table tm_column add index idx_app_id_is_delete(app_id, is_delete);
alter table tm_column add index idx_app_id_is_show_is_delete(app_id, is_show, is_delete);
alter table tt_faq_reply add index idx_faq_id_app_id_is_delete(faq_id, app_id, is_delete);
alter table tt_faq add index idx_app_id_is_delete(app_id, is_delete);
alter table tm_faq_type add index idx_app_id_column_id_is_delete(app_id, column_id, is_delete);

-- drop table if exists tt_faq_type_person_liable;
/*==============================================================*/
/* Table: tt_faq_type_person_liable(问答分类责任人)                             */
/*==============================================================*/
create table tt_faq_type_person_liable
(
   id                   varchar(32) not null,
   type_id              varchar(32),
   user_id              varchar(32),
   creater              varchar(32),
   create_time          timestamp NULL default NULL,
   is_delete            tinyint,
   app_id               varchar(32),
   primary key (id)
);
alter table tt_faq_type_person_liable add index idx_type_id(type_id);

alter table tm_article add column resources_title varchar(64) after resources_id;
alter table tt_faq add column faq_state varchar(16);

-- drop table if exists tr_faq_asso_user;
/*==============================================================*/
/* Table: tr_faq_asso_user(问答类型用户关联)                                      */
/*==============================================================*/
create table tr_faq_asso_user
(
   asso_id              varchar(32) not null,
   type_id              varchar(32),
   obj_id               varchar(32),
   obj_type             varchar(16) comment 'user:用户；usergroup：用户组；',
   creater              varchar(32),
   create_time          timestamp null default NULL,
   is_delete            tinyint,
   del_user             varchar(32),
   del_time             timestamp null default NULL,
   app_id               varchar(32),
   primary key (asso_id)
);

-- drop table if exists tt_faq_pic;
/*==============================================================*/
/* Table: tt_faq_pic(问答中心图片)                                            */
/*==============================================================*/
create table tt_faq_pic
(
   pic_id               varchar(32) not null,
   faq_id               varchar(32),
   pic_url              varchar(128),
   creater              varchar(32),
   create_time          timestamp NULL default NULL,
   app_id               varchar(32),
   primary key (pic_id)
);


alter table tm_article add column is_allow_comment tinyint default 0 after create_time;