-- Inserindo clientes
INSERT INTO cliente_tb (cartao_cliente, cpf_cliente, email_cliente, endreco_cliente, nome_cliente, telefone_cliente)
VALUES
('1234-5678-9012-3456', '11111111111', 'joao@email.com', 'Rua A, 123', 'João Silva', '11999999999'),
('2345-6789-0123-4567', '22222222222', 'maria@email.com', 'Rua B, 456', 'Maria Oliveira', '11888888888');

-- Inserindo fornecedores
INSERT INTO fornecedor_tb (fornecedo_endereco, fornecedor_cnpj, fornecedor_nome, fornecedor_telefone)
VALUES
('Avenida X, 100', '12345678000199', 'Fornecedor XPTO', '11977777777'),
('Avenida Y, 200', '98765432000188', 'Fornecedor ABC', '11966666666');

-- Inserindo produtos
INSERT INTO produto_tb (nome_produto, preco_produto, produto_quantidade, produto_categoria)
VALUES
('Produto A', 50.0, 10, 1),
('Produto B', 100.0, 5, 2);

-- Relacionando produtos e fornecedores
INSERT INTO produto_fornecedo_tb (produto_id, fornecedo_id)
VALUES
(1, 1), -- Produto A com Fornecedor XPTO
(2, 2); -- Produto B com Fornecedor ABC

-- Inserindo compras
INSERT INTO compra_tb (horario_compra, preco_compra, cliente_id)
VALUES
('2024-02-20 10:30:00', 150.0, 1),
('2024-02-20 15:45:00', 200.0, 2);

-- Relacionando produtos às compras
INSERT INTO produtos_compra_tb (produto_id, compra_id)
VALUES
(1, 1), -- Produto A na Compra 1
(2, 2); -- Produto B na Compra 2
