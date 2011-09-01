alter table movie add constraint pk_movie_id primary key (id);
alter table movie add constraint uc_imdb unique (imdb);
