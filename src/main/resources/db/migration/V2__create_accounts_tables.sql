CREATE TABLE tb_accounts (

    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    name VARCHAR(50) NOT NULL,
    initial_balance NUMERIC(10, 2) NOT NULL DEFAULT 0.00,

    CONSTRAINT fk_account_user FOREIGN KEY (user_id) REFERENCES tb_users(id)

);