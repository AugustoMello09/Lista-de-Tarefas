CREATE TABLE tb_tarefa (
    id BIGINT AUTO_INCREMENT,
    name VARCHAR(255) UNIQUE,
    cost NUMERIC(38,2),
    due_date DATE,
    position INTEGER,
    PRIMARY KEY (id)
);