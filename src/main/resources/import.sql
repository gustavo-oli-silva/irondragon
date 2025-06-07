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
(12, 12), (20, 20), (16, 24), (10, 30), (8, 8),
(6, 12), (18, 36), (14, 28), (12, 24), (10, 20),
(9, 18), (11, 22), (15, 30), (13, 26), (17, 34),
(7, 14), (19, 38), (21, 42), (22, 44), (23, 46),
(24, 48), (25, 50), (26, 52), (27, 54), (28, 56);


insert into Conectividade(pci, tipomemoria, canaismemoria) values
(3.0, 'DDR4', 4), (4.0, 'DDR5', 4), (5.0, 'DDR5', 2), (4.0, 'DDR4', 2), (3.0, 'DDR3', 4),
(5.0, 'LPDDR5', 4), (5.0, 'DDR5', 8), (4.0, 'LPDDR4X', 2), (3.0, 'DDR3L', 2), (4.0, 'DDR4', 2),
(4.0, 'DDR4', 1), (5.0, 'DDR5X', 4), (3.0, 'GDDR6', 2), (4.0, 'DDR6', 4), (5.0, 'DDR5', 4),
(5.0, 'DDR4', 2), (4.0, 'LPDDR5X', 2), (3.0, 'DDR2', 2), (4.0, 'DDR3', 4), (5.0, 'DDR5', 2),
(3.0, 'LPDDR4', 2), (4.0, 'DDR4', 4), (5.0, 'DDR6X', 2), (5.0, 'DDR7', 4), (4.0, 'DDR4', 8);


insert into ConsumoEnergetico(energiabasica, energiamaxima) values
(40, 80), (100, 200), (65, 95), (125, 250), (35, 65),
(80, 160), (50, 100), (90, 180), (70, 140), (110, 220),
(60, 120), (95, 190), (45, 90), (85, 170), (75, 150),
(55, 110), (105, 210), (115, 230), (130, 260), (120, 240),
(98, 196), (88, 176), (108, 216), (70, 150), (65, 135);


insert into Frequencia(clockbasico, clockboost) values
(50, 100), (200, 800), (300, 500), (320, 640), (150, 300),
(100, 200), (250, 750), (275, 550), (180, 360), (220, 440),
(160, 320), (140, 280), (190, 380), (210, 420), (230, 460),
(170, 340), (260, 520), (290, 580), (310, 620), (330, 660),
(240, 480), (195, 390), (205, 410), (215, 430), (225, 450);

insert into Processador (nome, socket, threads, nucleos, desbloqueado, preco, id_fabricante, id_memoriacache, id_conectividade, id_frequencia, id_consumoenergetico) values
('I5 11400F', 'LGA1200', 12, 6, false, 800.00, 1, 1, 1, 1, 1),
('I7 11700K', 'LGA1200', 16, 8, true, 1200.00, 1, 2, 2, 2, 2),
('I9 11900K', 'LGA1200', 16, 8, true, 1600.00, 1, 3, 3, 3, 3),
('I3 10100', 'LGA1200', 8, 4, false, 500.00, 1, 4, 4, 4, 4),
('I5 12400', 'LGA1700', 12, 6, false, 950.00, 1, 5, 5, 5, 5),
('I7 12700K', 'LGA1700', 20, 10, true, 1800.00, 1, 6, 6, 6, 6),
('I9 12900K', 'LGA1700', 24, 12, true, 2400.00, 1, 7, 7, 7, 7),
('Ryzen 3 3100', 'AM4', 8, 4, false, 450.00, 2, 8, 8, 8, 8),
('Ryzen 5 3600', 'AM4', 12, 6, false, 750.00, 2, 9, 9, 9, 9),
('Ryzen 7 3700X', 'AM4', 16, 8, false, 1100.00, 2, 10, 10, 10, 10),
('Ryzen 9 3900X', 'AM4', 24, 12, true, 1800.00, 2, 11, 11, 11, 11),
('Ryzen 5 5600X', 'AM4', 12, 6, true, 950.00, 2, 12, 12, 12, 12),
('Ryzen 7 5800X', 'AM4', 16, 8, true, 1300.00, 2, 13, 13, 13, 13),
('Ryzen 9 5950X', 'AM4', 32, 16, true, 2200.00, 2, 14, 14, 14, 14),
('Ryzen 7 7700X', 'AM5', 16, 8, true, 1600.00, 2, 15, 15, 15, 15),
('Ryzen 9 7900X', 'AM5', 24, 12, true, 2300.00, 2, 16, 16, 16, 16),
('I5 13400F', 'LGA1700', 16, 10, false, 1000.00, 1, 17, 17, 17, 17),
('I7 13700K', 'LGA1700', 24, 16, true, 2100.00, 1, 18, 18, 18, 18),
('I9 13900K', 'LGA1700', 32, 24, true, 2700.00, 1, 19, 19, 19, 19),
('Ryzen 5 7600', 'AM5', 12, 6, true, 1100.00, 2, 20, 20, 20, 20),
('Ryzen 5 8600G', 'AM5', 12, 6, true, 1300.00, 2, 21, 21, 21, 21),
('Ryzen 3 4300G', 'AM4', 8, 4, false, 650.00, 2, 22, 22, 22, 22),
('Ryzen 7 5700X', 'AM4', 16, 8, true, 1250.00, 2, 23, 23, 23, 23),
('I3 12100F', 'LGA1700', 8, 4, false, 600.00, 1, 24, 24, 24, 24),
('I5 12600K', 'LGA1700', 16, 10, true, 1400.00, 1, 25, 25, 25, 25);

INSERT INTO imagemprocessador (id_processador, nomeImagem, imagem, principal, index) VALUES
(1, 'i5.jpg', 'i5.jpg', true, 0),
(2, 'i7.jpg', 'i7.jpg', true, 0),
(3, 'i9.jpg', 'i9.jpg', true, 0),
(4, 'i3.jpg', 'i3.jpg', true, 0),
(5, 'i5.jpg', 'i5.jpg', true, 0),
(6, 'i7.jpg', 'i7.jpg', true, 0),
(7, 'i9.jpg', 'i9.jpg', true, 0),
(8, 'ryzen3.jpg', 'ryzen3.jpg', true, 0),
(9, 'ryzen5.jpg', 'ryzen5.jpg', true, 0),
(10, 'ryzen7.jpg', 'ryzen7.jpg', true, 0),
(11, 'ryzen9.jpg', 'ryzen9.jpg', true, 0),
(12, 'ryzen5.jpg', 'ryzen5.jpg', true, 0),
(13, 'ryzen7.jpg', 'ryzen7.jpg', true, 0),
(14, 'ryzen9.jpg', 'ryzen9.jpg', true, 0),
(15, 'ryzen7.jpg', 'ryzen7.jpg', true, 0),
(16, 'ryzen9.jpg', 'ryzen9.jpg', true, 0),
(17, 'i5.jpg', 'i5.jpg', true, 0),
(18, 'i7.jpg', 'i7.jpg', true, 0),
(19, 'i9.jpg', 'i9.jpg', true, 0),
(20, 'ryzen5.jpg', 'ryzen5.jpg', true, 0),
(21, 'ryzen5.jpg', 'ryzen5.jpg', true, 0),
(22, 'ryzen3.jpg', 'ryzen3.jpg', true, 0),
(23, 'ryzen7.jpg', 'ryzen7.jpg', true, 0),
(24, 'i3.jpg', 'i3.jpg', true, 0),
(25, 'i5.jpg', 'i5.jpg', true, 0);

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
('23', '40090090'),
('24', '40081192'),
('31', '40041234'),
('41', '998877665'),
('51', '123456789'),
('61', '987654321');  




insert into Usuario (nome, username, senha, perfil, cpf, id_telefone, email, dataCriacao, nomeImagem) values
('Admin', 'admin@admin.com' , 'SbMcPs9kSbqdOa3VT0byJuyqkcYOXpaVEkSyKHLg5cXNJiEK+WCrMPIH/lmEgJDvyqipXMoquqN8rY53rRGjAQ==', 2, '123', 1, 'admin@admin.com', '2024-02-04' , null),
('Fernando', 'corno@gmail.com', 'SbMcPs9kSbqdOa3VT0byJuyqkcYOXpaVEkSyKHLg5cXNJiEK+WCrMPIH/lmEgJDvyqipXMoquqN8rY53rRGjAQ==', 3, '321', 2, 'corno@gmail.com', '2024-12-05', null),
('Gustavo liveira', 'gustavoO@email.com' , 'SbMcPs9kSbqdOa3VT0byJuyqkcYOXpaVEkSyKHLg5cXNJiEK+WCrMPIH/lmEgJDvyqipXMoquqN8rY53rRGjAQ==', 1, '444', 3, 'gustavoO@email.com', '2024-06-06', null),
('Matheus Nardi', 'matheus@email.com' , 'SbMcPs9kSbqdOa3VT0byJuyqkcYOXpaVEkSyKHLg5cXNJiEK+WCrMPIH/lmEgJDvyqipXMoquqN8rY53rRGjAQ==', 1, '555', 4, 'matheus@email.com', '2024-12-02' , 'goku.jpg'),
('Angela Amaral', 'angelaA@mail.co,' ,'SbMcPs9kSbqdOa3VT0byJuyqkcYOXpaVEkSyKHLg5cXNJiEK+WCrMPIH/lmEgJDvyqipXMoquqN8rY53rRGjAQ==', 2, '101', 5, 'angelaA@email.com', '2024-12-08' , null),
('Novo Funcionario', 'novo@email.com', 'SbMcPs9kSbqdOa3VT0byJuyqkcYOXpaVEkSyKHLg5cXNJiEK+WCrMPIH/lmEgJDvyqipXMoquqN8rY53rRGjAQ==', 2, '404', 6, 'novo@email.com', '2024-12-02', null),
('cliente', 'cliente@email.com', 'SbMcPs9kSbqdOa3VT0byJuyqkcYOXpaVEkSyKHLg5cXNJiEK+WCrMPIH/lmEgJDvyqipXMoquqN8rY53rRGjAQ==', 3, '777', 7, 'cliente@email.com', '2024-12-02', null);

insert into Funcionario(id_usuario, cargo, salario, datacontratacao) values
(1, 'Administrador', 1200, '2024-12-03'),
(2, 'Gerente', 50000, '2024-07-29'),
(4, 'Gerente', 50000, '2024-07-29'),
(5, 'Vendedora', 3400, '2025-07-29');

INSERT INTO endereco (logradouro, cep, bairro, complemento, numero, id_cidade, id_usuario)
VALUES 
('Rua das Palmeiras', '77020-000', 'Centro', 'Apto 101', 123, 1,1),
('Avenida Brasil', '77010-001', 'Jardim América', null, 456, 2,1),
('Rua João XXIII', '77015-222', 'São José', 'Casa 2', 78, 3,2),
('Travessa das Flores', '77030-987', 'Morada do Sol', null, 150, 4,3),
('Rua do Comércio', '77040-456', 'Centro', 'Fundos', 89, 5,4),
('Avenida Tocantins', '77050-321', 'Planalto', null, 321, 6,5),
('Rua Projetada A', '77060-654', 'Vila Nova', null, 101, 7,6),
('Rua das Acácias', '77070-789', 'Parque das Árvores', 'Bloco B', 222, 8, 2),
('Alameda das Rosas', '77080-111', 'Flor do Cerrado', null, 65, 9,2),
('Rua 12 de Outubro', '77090-222', 'Boa Vista', null, 333, 10,3),
('Rua Amazonas', '77100-333', 'Industrial', 'Galpão 1', 500, 11,4),
('Rua Maranhão', '77110-444', 'Bela Vista', null, 150, 12,3),
('Rua Pará', '77120-555', 'Santa Maria', 'Casa térrea', 42, 13,5),
('Rua Ceará', '77130-666', 'Setor Norte', null, 88, 14,6),
('Rua Goiás', '77140-777', 'Vila Rica', 'Apto 202', 77, 15,7);

insert into Cliente(id_usuario) values
(1),
(2),
(4),
(7);

insert into Lote (codigo, estoque, data, id_processador, id_fornecedor) values
('#PROC1-TBY-20241107', 20, '2024-11-07', 1, 1),  
('#PROC2-KBM-20241106', 40, '2024-11-06', 2, 2), 
('#PROC1-PCH-20241105', 30, '2024-11-05', 1, 3),  
('#PROC2-AMZ-20241105', 30, '2024-11-05', 3, 4),  
('#PROC1-NWG-20241105', 30, '2024-11-05', 4, 5),
 ('#PROC1-XYZ-20241108', 25, '2024-11-08', 5, 1),
('#PROC1-ABC-20241104', 15, '2024-11-04', 6, 2),

('#PROC2-DEF-20241107', 50, '2024-11-07', 6, 1),
('#PROC2-GHI-20241103', 10, '2024-11-03', 7, 3),

 
('#PROC3-JKL-20241110', 5,  '2024-11-10', 10, 2),
('#PROC3-MNO-20241030', 20, '2024-10-30', 3, 4),

  
('#PROC4-PQR-20241109', 30, '2024-11-09', 12, 1),
('#PROC4-STU-20241025', 12, '2024-10-25', 3, 5),

 
('#PROC5-VWX-20241106', 40, '2024-11-06', 20, 3),
('#PROC5-YZA-20241028', 18, '2024-10-28', 17, 4),

  
('#PROC6-BCD-20241105', 22, '2024-11-05', 15, 1),
('#PROC6-EFG-20241020',  8, '2024-10-20', 18, 5),


('#PROC7-HIJ-20241108', 16, '2024-11-08', 14, 2),
('#PROC7-KLM-20241022', 14, '2024-10-22', 7, 3);


-- CONTINUAÇÃO DO SEU IMPORT.SQL (APÓS OS LOTES)

-- Adicionando Enderecos de Entrega
INSERT INTO EnderecoEntrega (id, logradouro, cep, bairro, complemento, numero, id_cidade) VALUES
(1, 'Quadra 104 Sul, Rua SE 03', '77020-014', 'Plano Diretor Sul', 'Casa Amarela', 10, 1),
(2, 'Avenida Paulista', '01310-000', 'Bela Vista', 'Apto 505, Bloco A', 1500, 2),
(3, 'Rua dos Aimorés', '30140-070', 'Funcionários', NULL, 200, 3),
(4, 'Alameda dos Girassóis, Lote 5', '77015-020', 'Graciosa', 'Próximo ao Parque', 30, 1),          -- Novo para Pedido 4
(5, 'Rua da Consolação', '01220-000', 'Consolação', 'Conjunto 32', 800, 2),                    -- Novo para Pedido 6 (SP)
(6, 'Avenida Afonso Pena', '30130-001', 'Centro', 'Sala 1010', 120, 3),                        -- Novo para Pedido 8 (BH)
(7, 'Quadra 208 Norte, Alameda 10', '77002-100', 'Plano Diretor Norte', 'Casa com portão azul', 77, 1); -- Novo para Pedido 10
-- ALTER SEQUENCE enderecoentrega_seq RESTART WITH 8;


-- Adicionando Pagamentos
-- (IDs 1, 2, 3 já criados, adicionando mais para os novos pedidos)
INSERT INTO Pagamento (id, valor, pago) VALUES
(1, 2000.00, false),   -- Pedido 1
(2, 1600.00, true),    -- Pedido 2
(3, 800.00, true),     -- Pedido 3
(4, 800.00, false),    -- Pedido 4
(5, 1750.00, true),    -- Pedido 5
(6, 3600.00, true),    -- Pedido 6
(7, 2400.00, true),    -- Pedido 7
(8, 1100.00, false),   -- Pedido 8
(9, 2750.00, true),    -- Pedido 9
(10, 3300.00, false);  -- Pedido 10 (corrigido para bater com os itens)

INSERT INTO Pix(id, chave, destinatario, dataValidade) VALUES 
(3, '12345678909', 'Iron Dragon LTDA', '2025-12-31 10:30:00'),
(6, '98765432100', 'Tech Solutions', '2025-12-31 10:30:00');

INSERT INTO Boleto(id, codigoBarras, dataValidade ) VALUES 
(9, '12345678901234567890123456789012345678901234', '2025-12-31 10:30:00');


INSERT INTO Pedido (id, data, id_cliente, id_enderecoentrega, id_pagamento, valorTotal, statusPedido) VALUES
(1, '2024-07-20 10:00:00', 1, 1, 1, 2000.00, 3), 
(2, '2024-07-21 14:30:00', 2, 2, 2, 1600.00, 4), 
(3, '2024-07-15 09:15:00', 3, 3, 3, 800.00, 6), 
(4, '2024-07-22 11:00:00', 1, 4, 4, 800.00, 3),  
(5, '2024-07-23 16:00:00', 2, 2, 5, 1750.00, 5), 
(6, '2024-07-24 08:30:00', 3, 5, 6, 3600.00, 4), 
(7, '2024-07-18 13:15:00', 1, 1, 7, 2400.00, 6), 
(8, '2024-07-25 10:45:00', 2, 6, 8, 1100.00, 2), 
(9, '2024-07-10 17:00:00', 3, 3, 9, 2750.00, 7), 
(10, '2024-07-26 09:00:00', 1, 7, 10, 3300.00, 3); -- valor corrigido


-- Adicionando Itens de Pedido
INSERT INTO ItemPedido (id, id_lote, quantidade, preco, id_pedido) VALUES
-- Itens para Pedido 1 (Valor Total: 800 + 1200 = 2000.00)
(1, 1, 1, 800.00, 1),  -- Lote 1 (Proc ID 1: I5 11400F)
(2, 2, 3, 1200.00, 1), -- Lote 2 (Proc ID 2: I7 11700K)

-- Itens para Pedido 2 (Valor Total: 2 * 800 = 1600.00)
(3, 3, 2, 800.00, 2),  -- Lote 3 (Proc ID 1: I5 11400F) -> Repetição do I5 11400F

-- Itens para Pedido 3 (Valor Total: 1 * 800 = 800.00)
(4, 1, 1, 800.00, 3),  -- Lote 1 (Proc ID 1: I5 11400F) -> Repetição do I5 11400F

-- Itens para Pedido 4 (Valor Total: 800.00)
(5, 3, 1, 800.00, 4),  -- Lote 3 (Proc ID 1: I5 11400F) -> Repetição do I5 11400F

-- Itens para Pedido 5 (Valor Total: 800 + 950 = 1750.00)
(6, 1, 1, 800.00, 5),  -- Lote 1 (Proc ID 1: I5 11400F) -> Repetição do I5 11400F
(7, 12, 4, 950.00, 5), -- Lote 12 (Proc ID 12: Ryzen 5 5600X)

-- Itens para Pedido 6 (Valor Total: 2 * 1800 = 3600.00)
(8, 7, 2, 1800.00, 6), -- Lote 7 (Proc ID 6: I7 12700K)

-- Itens para Pedido 7 (Valor Total: 800 + 1600 = 2400.00)
(9, 3, 1, 800.00, 7),  -- Lote 3 (Proc ID 1: I5 11400F) -> Repetição do I5 11400F
(10, 4, 2, 1600.00, 7),-- Lote 4 (Proc ID 3: I9 11900K)

-- Itens para Pedido 8 (Valor Total: 1100.00)
(11, 10, 4, 1100.00, 8),-- Lote 10 (Proc ID 10: Ryzen 7 3700X)

-- Itens para Pedido 9 (Valor Total: 950 + 1800 = 2750.00)
(12, 6, 1, 950.00, 9),  -- Lote 6 (Proc ID 5: I5 12400)
(13, 8, 1, 1800.00, 9), -- Lote 8 (Proc ID 6: I7 12700K) -> Repetição do I7 12700K

-- Itens para Pedido 10 (Valor Total: (3*800) + 500 = 2400 + 500 = 2900.00)
(14, 1, 1, 800.00, 10), -- Lote 1 (Proc ID 1: I5 11400F) -> Repetição do I5 11400F
(15, 5, 5, 500.00, 10); -- Lote 5 (Proc ID 4: I3 10100)

-- ALTER SEQUENCE itempedido_seq RESTART WITH 16;


INSERT INTO cartao(id, nomeTitular, numero, cpf, validade, cvc, tipo, bandeira, id_cliente) values 
(1, 'Matheus Bolado' , '5228 1576 6460 7887' , '63961082081', '2030-08-01', 832, 1, 2, 3),
(2, 'Matheus Bolado' , '4485 8739 0651 0728' , '63961082081', '2030-08-01', 247, 1, 1, 3);