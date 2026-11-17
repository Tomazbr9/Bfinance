CREATE TABLE tb_categories (
    id UUID PRIMARY KEY,
    userId
    name VARCHAR(50) NOT NULL,
    parent_id UUID NOT NULL
);


# verificar regra de negocio antes de prosseguir aqui 