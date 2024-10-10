-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

insert into Fabricante (nome, email) values
('Intel', 'support@intel.com'),
('AMD', 'support@amd.com');


insert into Processador (nome, socket, threads, nucleos, desbloqueado, preco, id_fabricante) values
('I5 12700', 'LGA1155', 4, 4, true, 45.00, 1),
('I7 7700K', 'LGA777', 4, 4, true, 70.00, 1);

insert into Estado (nome, sigla) values
('Tocantins', 'TO'),
('São Paulo', 'SP');

