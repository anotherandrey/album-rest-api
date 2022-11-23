create table "image" (
  "id" bigint not null primary key,
  "filename" varchar(1024) not null,
  "content_type" varchar(1024) not null,
  "content_length" bigint not null,
  "created_at" timestamp
);
create sequence "image_id_sequence" start 1 increment 1;
