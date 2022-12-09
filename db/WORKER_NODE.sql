create table worker_node
(
    ID           bigint auto_increment comment 'auto increment id'
        primary key,
    WORK_ID      bigint unsigned                     null,
    HOST_NAME    varchar(64)                         not null comment 'host name',
    PORT         varchar(64)                         not null comment 'port',
    TYPE         int                                 not null comment 'node type: ACTUAL or CONTAINER',
    GLOBAL_TOKEN varchar(255)                        null,
    LAUNCH_DATE  date                                not null comment 'launch date',
    MODIFIED     timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment 'modified time',
    CREATED      timestamp                           null comment 'created time',
    constraint worker_node_pk
        unique (WORK_ID)
)
    comment 'DB WorkerID Assigner for UID Generator';

