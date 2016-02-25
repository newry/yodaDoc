
    alter table DOCUMENT 
        drop constraint if exists FK_akf625i81wx2gvodrhc76h58o;

    drop table DOCUMENT cascade;

    drop table FOLDER cascade;

    create table DOCUMENT (
        ID  bigserial not null,
        CREATOR varchar(255) not null,
        DATE_ADDED timestamp not null,
        DATE_LAST_MODIFIED timestamp not null,
        COMMENT varchar(255) not null,
        FILE_NAME varchar(255) not null,
        FILE_SIZE int8 not null,
        STATUS varchar(50) not null,
        TITLE varchar(255) not null,
        FOLDER_ID int8 not null,
        primary key (ID)
    );

    create table FOLDER (
        ID  bigserial not null,
        CREATOR varchar(255) not null,
        DATE_ADDED timestamp not null,
        DATE_LAST_MODIFIED timestamp not null,
        DESCRIPTION varchar(255) not null,
        NAME varchar(255) not null,
        PARENT_ID int8 not null,
        POSITION int4 not null,
        STATUS varchar(50) not null,
        primary key (ID)
    );

    alter table DOCUMENT 
        add constraint FK_akf625i81wx2gvodrhc76h58o 
        foreign key (FOLDER_ID) 
        references FOLDER;
