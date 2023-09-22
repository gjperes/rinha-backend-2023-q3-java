create extension if not exists pg_trgm;

create table pessoas (
id uuid     primary key,
apelido     varchar(32) unique not null,
nome        varchar(100) not null,
nascimento  char(10) not null,
stack       text null,
termo       text generated always as (lower(pessoas.nome||pessoas.apelido||coalesce(pessoas.stack, ''))) stored
);

create index termo_idx on pessoas using gist(termo gist_trgm_ops);