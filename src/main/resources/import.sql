-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

insert into Fabricante (nome, email) values
('Intel', 'support@intel.com'),
('AMD', 'support@amd.com');

insert into Fornecedor (nome, email) values
('Terabyte', 'suporte@terabyte.com');

insert into Processador (nome, socket, threads, nucleos, desbloqueado, preco, id_fabricante) values
('I5 12700', 'LGA1155', 4, 4, true, 45.00, 1),
('I7 7700K', 'LGA777', 4, 4, true, 70.00, 1);

insert into Estado (nome, sigla) values
('Tocantins', 'TO'),
('São Paulo', 'SP');

insert into Cidade (nome, id_estado) values
('Palmas', 1);

insert into PlacaIntegrada (nome, directx, opengl, vulkan) values
('Vega 5', 1.0, 2.0, 3.0);

insert into Cartao (nometitular, numero, cpf, validade, cvc, tipo) values
('Keanu Reeves', '40028922', '904.001.690-96', '2022-02-02', 123, 1);

insert into Usuario (username, senha, perfil) values
('Ahri', 'SbMcPs9kSbqdOa3VT0byJuyqkcYOXpaVEkSyKHLg5cXNJiEK+WCrMPIH/lmEgJDvyqipXMoquqN8rY53rRGjAQ==', 1);


