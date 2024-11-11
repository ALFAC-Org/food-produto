USE fooddb;

CREATE TABLE
    IF NOT EXISTS item (
        id INT AUTO_INCREMENT PRIMARY KEY,
        uuid BINARY(16) NOT NULL default(UNHEX(REPLACE(UUID(), '-', ''))),
        nome VARCHAR(255),
        preco DECIMAL(10, 2),
        categoria ENUM (
            'LANCHE',
            'COMPLEMENTO',
            'ACOMPANHAMENTO',
            'BEBIDA',
            'SOBREMESA'
        )
    );

create unique index item_uuid_uindex on item (uuid);

create unique index item_id_uk
    on item (id);

INSERT INTO
    item (nome, preco, categoria)
VALUES
    ('Hamburguer', 15.00, 'LANCHE'),
    ('Hot Dog', 10.00, 'LANCHE'),
    ('Sanduíche', 12.00, 'LANCHE'),
    ('Pizza', 20.00, 'LANCHE'),
    ('Taco', 8.00, 'LANCHE'),
    ('Queijo Extra', 2.00, 'COMPLEMENTO'),
    ('Bacon Extra', 3.00, 'COMPLEMENTO'),
    ('Batata Frita', 5.00, 'ACOMPANHAMENTO'),
    ('Anéis de Cebola', 6.00, 'ACOMPANHAMENTO'),
    ('Salada', 7.00, 'ACOMPANHAMENTO'),
    ('Refrigerante', 4.00, 'BEBIDA'),
    ('Suco', 3.00, 'BEBIDA'),
    ('Água', 2.00, 'BEBIDA'),
    ('Sorvete', 5.00, 'SOBREMESA'),
    ('Bolo', 6.00, 'SOBREMESA');