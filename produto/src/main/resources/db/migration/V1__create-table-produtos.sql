create table produtos(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    descricao varchar(100) not null,
    valor bigint not null,

    primary key(id)
);