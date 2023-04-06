CREATE TABLE medico
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    nombre       VARCHAR(100)          NULL,
    email        VARCHAR(100)          NULL,
    documento    VARCHAR(10)          NULL,
    especialidad VARCHAR(10)          NULL,
    calle        VARCHAR(10)          NULL,
    numero       VARCHAR(10)          NULL,
    complemento  VARCHAR(10)          NULL,
    ciudad       VARCHAR(10)          NULL,
    distrito     VARCHAR(10)          NULL,
    CONSTRAINT pk_medico PRIMARY KEY (id)
);