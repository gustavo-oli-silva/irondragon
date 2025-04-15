-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

insert into TelefoneFabricante (codigoarea, numero) values
('63', '40028922'),
('63', '40028922'),
('11', '987654321'),  
('21', '123456789'),
('31', '40041234'),   
('41', '998877665');

insert into Fabricante (nome, email, id_telefone) values
('Intel', 'support@intel.com', 1),
('AMD', 'support@amd.com', 2),
('Qualcomm', 'support@qualcomm.com', 3),
('IBM', 'support@ibm.com', 4),
('ARM', 'support@arm.com', 5),
('Apple', 'support@apple.com', 6);

insert into TelefoneFornecedor (codigoarea, numero) values
('63', '40028922'),
('63', '40093822'),
('11', '987654321'),  
('21', '123456789'),
('31', '40041234');


INSERT INTO Fornecedor (nome, email, id_telefone) VALUES
('Terabyte', 'suporte@terabyte.com', 1),
('Kabum', 'vendas@kabum.com.br', 2),
('Pichau', 'contato@pichau.com.br', 3),
('Amazon', 'fornecedores@amazon.com', 4),
('Newegg', 'sales@newegg.com', 5);

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


INSERT INTO Estado (nome, sigla) VALUES
('Acre', 'AC'),
('Alagoas', 'AL'),
('Amapá', 'AP'),
('Amazonas', 'AM'),
('Bahia', 'BA'),
('Ceará', 'CE'),
('Distrito Federal', 'DF'),
('Espírito Santo', 'ES'),
('Goiás', 'GO'),
('Maranhão', 'MA'),
('Mato Grosso', 'MT'),
('Mato Grosso do Sul', 'MS'),
('Minas Gerais', 'MG'),
('Pará', 'PA'),
('Paraíba', 'PB'),
('Paraná', 'PR'),
('Pernambuco', 'PE'),
('Piauí', 'PI'),
('Rio de Janeiro', 'RJ'),
('Rio Grande do Norte', 'RN'),
('Rio Grande do Sul', 'RS'),
('Rondônia', 'RO'),
('Roraima', 'RR'),
('Santa Catarina', 'SC'),
('São Paulo', 'SP'),
('Sergipe', 'SE'),
('Tocantins', 'TO');


INSERT INTO Cidade (nome, id_estado) VALUES
('Palmas', 27),
('São Paulo', 25),
('Belo Horizonte', 13),
('São Luís', 10),
('Belém', 14),
('Porto Alegre', 21),
('Salvador', 5),
('Recife', 17),
('Fortaleza', 6),
('Rio de Janeiro', 19),
('Brasília', 7),
('Curitiba', 16),
('Florianópolis', 24),
('Aracaju', 26),
('Maceió', 2);


insert into PlacaIntegrada (nome, directx, opengl, vulkan) values
('Vega 5', 1.0, 2.0, 3.0);

-- insert into Cartao (nometitular, numero, cpf, validade, cvc, tipo) values
-- ('Keanu Reeves', '40028922', '904.001.690-96', '2022-02-02', 123, 1);

insert into TelefoneUsuario (codigoarea, numero) values
('63', '40028922'),   -- 1 
('63', '40093822'),   -- 2
('SU', 'SUPER'),   -- 3
('11', '40093833'),
('23', '40090090');   

insert into Usuario (username, senha, perfil, cpf, id_telefone, email) values
('Ahri', 'SbMcPs9kSbqdOa3VT0byJuyqkcYOXpaVEkSyKHLg5cXNJiEK+WCrMPIH/lmEgJDvyqipXMoquqN8rY53rRGjAQ==', 2, '123', 1, 'ahri@email.com'),
('Fernando', 'SbMcPs9kSbqdOa3VT0byJuyqkcYOXpaVEkSyKHLg5cXNJiEK+WCrMPIH/lmEgJDvyqipXMoquqN8rY53rRGjAQ==', 3, '321', 2, 'corno@gmail.com'),
('GustavoOliveira', 'SbMcPs9kSbqdOa3VT0byJuyqkcYOXpaVEkSyKHLg5cXNJiEK+WCrMPIH/lmEgJDvyqipXMoquqN8rY53rRGjAQ==', 1, '444', 3, 'gustavoO@email.com'),
('MatheusNardi', 'SbMcPs9kSbqdOa3VT0byJuyqkcYOXpaVEkSyKHLg5cXNJiEK+WCrMPIH/lmEgJDvyqipXMoquqN8rY53rRGjAQ==', 1, '555', 4, 'matheusN@email.com'),
('AngelaAmaral', 'SbMcPs9kSbqdOa3VT0byJuyqkcYOXpaVEkSyKHLg5cXNJiEK+WCrMPIH/lmEgJDvyqipXMoquqN8rY53rRGjAQ==', 2, '101', 5, 'angelaA@email.com');

insert into Funcionario(id_usuario, cargo, salario, datacontratacao) values
(1, 'Administrador', 1200, '2024-12-03'),
(2, 'Gerente', 50000, '2024-07-29'),
(4, 'Gerente', 50000, '2024-07-29'),
(5, 'Vendedora', 3400, '2025-07-29');

insert into Cliente(id_usuario) values
(1),
(2),
(3);

insert into Lote (codigo, estoque, data, id_processador, id_fornecedor) values
('#PROC1-TBY-20241107', 20, '2024-11-07', 1, 1),  
('#PROC2-KBM-20241106', 40, '2024-11-06', 2, 2), 
('#PROC1-PCH-20241105', 30, '2024-11-05', 1, 3),  
('#PROC2-AMZ-20241105', 30, '2024-11-05', 2, 4),  
('#PROC1-NWG-20241105', 30, '2024-11-05', 1, 5);   