create table usuarios(

    id bigint not null auto_increment,
    login varchar(256) not null UNIQUE,
    senha varchar(256) not null,
    role varchar(10),

     primary key(id)

);