connect 'jdbc:derby:Supermarket;create=true;user=root;password=root';

create table Fornecedor(id bigint not null generated always as identity, cnpj varchar(18) not null, nome varchar(256) not null, constraint Fornecedor_PK primary key (id));

create table Produto(id bigint not null generated always as identity, titulo varchar(256) not null, categoria varchar(256) not null, quantidade integer not null, preco float not null, fornecedor_id bigint not null, constraint Produto_PK primary key (id), constraint Fornecedor_FK foreign key (fornecedor_id) references Fornecedor(id));

insert into Fornecedor(cnpj, nome) values  ('55.789.390/0008-99', 'Nestle');

insert into Fornecedor(cnpj, nome) values ('71.150.470/0001-40', 'Bauduco');

insert into Fornecedor(cnpj, nome) values ('32.106.536/0001-82', 'CocaCola');

insert into Produto(titulo, categoria, quantidade, preco, fornecedor_id) values ('Nescau', 'Chocolate em po', 1995, 4.9, 1);

insert into Produto(titulo, categoria, quantidade, preco, fornecedor_id) values  ('Cookies', 'Bolacha', 1977, 9.9, 2);

insert into Produto(titulo, categoria, quantidade, preco, fornecedor_id) values ('Coca-Cola 2L', 'Refrigerante', 2012, 12.9, 3);

create table Usuario(id bigint not null generated always as identity (start with 1, increment by 1), nome varchar(256) not null, login varchar(20) not null unique, senha varchar(64) not null, papel varchar(10), constraint Usuario_PK primary key (id));

insert into Usuario(nome, login, senha, papel) values ('Administrador', 'admin', 'admin', 'ADMIN');

insert into Usuario(nome, login, senha, papel) values ('Usuario', 'user', 'user', 'USER');


create table Compra(id bigint not null generated always as identity, data varchar(10) not null, valor float not null, produto_id bigint not null, usuario_id bigint not null, constraint Compra_PK primary key (id), constraint PRODUTO_FK foreign key (produto_id) references Produto(id), constraint USUARIO_FK foreign key (usuario_id) references Usuario(id));

insert into Compra(data, valor, produto_id, usuario_id) values ('30/08/2020', 10.88, 1, 2);

disconnect;

quit;