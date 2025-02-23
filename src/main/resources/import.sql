-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

insert into TelefoneFabricante (codigoarea, numero) values
('63', '40028922'),
('63', '40028922');

insert into Fabricante (nome, email, id_telefone) values
('Intel', 'support@intel.com', 1),
('AMD', 'support@amd.com', 2);

insert into TelefoneFornecedor (codigoarea, numero) values
('63', '40028922');

insert into TelefoneUsuario (codigoarea, numero) values
('63', '40028922'),
('63', '40093822'),
('SUPER', 'SUPER');

insert into Fornecedor (nome, email, id_telefone) values
('Terabyte', 'suporte@terabyte.com', 1);

insert into MemoriaCache(cachel2, cachel3) values
(12, 12),
(20, 20);

insert into Conectividade(pci, tipomemoria, canaismemoria) values
(3.0, 'DDR4', 4),
(4.0, 'DDR5', 4);

insert into ConsumoEnergetico(energiabasica, energiamaxima) values
(40, 80),
(100, 200);

insert into Frequencia(clockbasico, clockboost) values
(50, 100),
(200, 800);

insert into Processador (nome, socket, threads, nucleos, desbloqueado, preco, id_fabricante, id_memoriacache, id_conectividade, id_frequencia, id_consumoenergetico) values
('I5 12700', 'LGA1155', 4, 4, true, 600.00, 1, 1, 1, 1, 1),
('I7 7700K', 'LGA777', 4, 4, true, 2400.00, 1, 2, 2, 2, 2);

insert into Estado (nome, sigla) values
('Tocantins', 'TO'),
('São Paulo', 'SP'),
('Minas Gerais', 'MG'),
('Maranhao', 'MA'),
('Para', 'PA'),
('Rio Grande do Sul', 'RS');

insert into Cidade (nome, id_estado) values
('Palmas', 1);

insert into PlacaIntegrada (nome, directx, opengl, vulkan) values
('Vega 5', 1.0, 2.0, 3.0);

-- insert into Cartao (nometitular, numero, cpf, validade, cvc, tipo) values
-- ('Keanu Reeves', '40028922', '904.001.690-96', '2022-02-02', 123, 1);

insert into Usuario (username, senha, perfil, cpf, id_telefone) values
('Ahri', 'SbMcPs9kSbqdOa3VT0byJuyqkcYOXpaVEkSyKHLg5cXNJiEK+WCrMPIH/lmEgJDvyqipXMoquqN8rY53rRGjAQ==', 2, '123', 1),
('SUPER', 'SbMcPs9kSbqdOa3VT0byJuyqkcYOXpaVEkSyKHLg5cXNJiEK+WCrMPIH/lmEgJDvyqipXMoquqN8rY53rRGjAQ==', 1, 'SUPER', 3),
('Acabaxi', 'SbMcPs9kSbqdOa3VT0byJuyqkcYOXpaVEkSyKHLg5cXNJiEK+WCrMPIH/lmEgJDvyqipXMoquqN8rY53rRGjAQ==', 3, '321', 2);

insert into Funcionario(id_usuario, cargo, salario, datacontratacao) values
(1, 'Administrador', 1200, '2024-12-03'),
(2, 'SUPER', 0, '2004-07-29');

insert into Cliente(id_usuario) values
(1),
(2),
(3);

insert into Lote (codigo, estoque, data, id_processador, id_fornecedor) values
('#ZXYD4343', 20, '2024-11-07', 1, 1),
('#INTEL2024DFCG', 40, '2024-11-06', 2, 1);


