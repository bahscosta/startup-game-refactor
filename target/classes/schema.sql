DROP TABLE IF EXISTS DECISAO_APLICADA;
DROP TABLE IF EXISTS RODADA;
DROP TABLE IF EXISTS STARTUP;

CREATE TABLE STARTUP (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    caixa DOUBLE,
    reputacao INT,
    moral INT,
    receita_base DOUBLE
);

CREATE TABLE RODADA (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero INT,
    startup_id INT,
    caixa DOUBLE,
    moral INT,
    reputacao INT,
    receita_base DOUBLE,
    FOREIGN KEY (startup_id) REFERENCES STARTUP(id)
);

CREATE TABLE DECISAO_APLICADA (
    id INT AUTO_INCREMENT PRIMARY KEY,
    startup_id INT,
    rodada INT,
    tipo_decisao VARCHAR(255),
    delta_caixa DOUBLE,
    delta_moral INT,
    delta_reputacao INT,
    delta_bonus DOUBLE,
    timestamp TIMESTAMP,
    FOREIGN KEY (startup_id) REFERENCES STARTUP(id)
);
