create table hibernate_sequences (
  sequence_name varchar(255),
  sequence_next_hi_value int
);

create table movie (
	id bigint not null,
	imdb varchar(255) not null,
	title varchar(255) not null,
	name varchar(255),
	firstname varchar(255)
);