-- Criação das tabelas principais
CREATE TABLE Usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    telefone VARCHAR(20),
    endereco VARCHAR (255),
    municipio VARCHAR(100),
    uf VARCHAR(2),
    senha VARCHAR(20) NOT NULL,
    tipo VARCHAR(1) NOT NULL, 
    situacao BOOLEAN NOT NULL, 
    data_cadastro datetime NOT NULL
);

CREATE TABLE Farmacia (
    id SERIAL PRIMARY KEY,
    nome_fantasia VARCHAR(100) NOT NULL,
    cnpj VARCHAR(18) UNIQUE NOT NULL,
    cep VARCHAR(9),
    endereco varchar (255),
    municipio VARCHAR(100),
    UF VARCHAR(2),
    email VARCHAR(100) UNIQUE NOT NULL,
    telefone VARCHAR(20),
    horario_funcionamento VARCHAR(50),
    data_cadastro datetime NOT NULL,
    id_usuario_admin INT NOT NULL,
    FOREIGN KEY (id_usuario_admin) REFERENCES Usuario(id) 
);

CREATE TABLE Medicamento (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    principio_ativo VARCHAR(150),
    descricao TEXT,
    categoria VARCHAR(20) NOT NULL, 
    data_cadastro datetime NOT NULL
);

CREATE TABLE estoque (
    id SERIAL PRIMARY KEY,
    id_farmacia INT NOT NULL,
    id_medicamento INT NOT NULL,
    quantidade INT NOT NULL DEFAULT 0,
    data_atualizacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_farmacia) REFERENCES farmacia(id),
    FOREIGN KEY (id_medicamento) REFERENCES medicamento(id),
    UNIQUE (id_farmacia, id_medicamento)
);

CREATE TABLE favorito (
    id SERIAL PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_medicamento INT NOT NULL,
    data_cadastro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id),
    FOREIGN KEY (id_medicamento) REFERENCES medicamento(id),
    UNIQUE (id_usuario, id_medicamento)
);

