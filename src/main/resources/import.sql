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
('23', '40090090'),
('24', '40081192');  




insert into Usuario (username, senha, perfil, cpf, id_telefone, email, dataCriacao) values
('Ahri', 'SbMcPs9kSbqdOa3VT0byJuyqkcYOXpaVEkSyKHLg5cXNJiEK+WCrMPIH/lmEgJDvyqipXMoquqN8rY53rRGjAQ==', 2, '123', 1, 'ahri@email.com', '2024-02-04'),
('Fernando', 'SbMcPs9kSbqdOa3VT0byJuyqkcYOXpaVEkSyKHLg5cXNJiEK+WCrMPIH/lmEgJDvyqipXMoquqN8rY53rRGjAQ==', 3, '321', 2, 'corno@gmail.com', '2024-12-05'),
('GustavoOliveira', 'SbMcPs9kSbqdOa3VT0byJuyqkcYOXpaVEkSyKHLg5cXNJiEK+WCrMPIH/lmEgJDvyqipXMoquqN8rY53rRGjAQ==', 1, '444', 3, 'gustavoO@email.com', '2024-06-06'),
('MatheusNardi', 'SbMcPs9kSbqdOa3VT0byJuyqkcYOXpaVEkSyKHLg5cXNJiEK+WCrMPIH/lmEgJDvyqipXMoquqN8rY53rRGjAQ==', 1, '555', 4, 'matheusN@email.com', '2024-12-02'),
('AngelaAmaral', 'SbMcPs9kSbqdOa3VT0byJuyqkcYOXpaVEkSyKHLg5cXNJiEK+WCrMPIH/lmEgJDvyqipXMoquqN8rY53rRGjAQ==', 2, '101', 5, 'angelaA@email.com', '2024-12-08'),
('NovoFuncionario', 'SbMcPs9kSbqdOa3VT0byJuyqkcYOXpaVEkSyKHLg5cXNJiEK+WCrMPIH/lmEgJDvyqipXMoquqN8rY53rRGjAQ==', 2, '404', 6, 'novo@email.com', '2024-12-02');

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
('Rua Goiás', '77140-777', 'Vila Rica', 'Apto 202', 77, 15,5);

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