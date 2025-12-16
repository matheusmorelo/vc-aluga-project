SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS estoque;
DROP TABLE IF EXISTS manutencao;
DROP TABLE IF EXISTS devolucao;
DROP TABLE IF EXISTS permissao;
DROP TABLE IF EXISTS programa_fidelidade;
DROP TABLE IF EXISTS transacao;
DROP TABLE IF EXISTS motorista;
DROP TABLE IF EXISTS funcionario;
DROP TABLE IF EXISTS veiculo;
DROP TABLE IF EXISTS cliente;
DROP TABLE IF EXISTS agencia;

-- TABELAS BASE
CREATE TABLE agencia (
  id_agencia INT AUTO_INCREMENT PRIMARY KEY,
  contato VARCHAR(100),
  relatorio VARCHAR(255),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE cliente (
  id_cliente INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(255) NOT NULL,
  cpf VARCHAR(11) NOT NULL,
  cnpj VARCHAR(14),
  idade INT NOT NULL,
  cnh INT NOT NULL,
  contato INT NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE funcionario (
  id_funcionario INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(255) NOT NULL,
  documento ENUM('cpf','rg') NOT NULL,
  contato VARCHAR(11) NOT NULL,
  cargo VARCHAR(155) NOT NULL,
  id_cliente INT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
);

CREATE TABLE motorista (
  id_motorista INT AUTO_INCREMENT PRIMARY KEY,
  cnh VARCHAR(40) NOT NULL,
  nome VARCHAR(50) NOT NULL,
  id_cliente INT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
);

CREATE TABLE veiculo (
  id_veiculo INT AUTO_INCREMENT PRIMARY KEY,
  placa VARCHAR(50),
  modelo VARCHAR(50) NOT NULL,
  cor VARCHAR(50) NOT NULL,
  ano INT NOT NULL,
  km INT NOT NULL,
  situacao ENUM('alugado','disponivel') NOT NULL,
  descricao VARCHAR(100) NOT NULL,
  id_agencia INT,
  id_cliente INT,
  id_motorista INT,
  id_funcionario INT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (id_agencia) REFERENCES agencia(id_agencia),
  FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente),
  FOREIGN KEY (id_motorista) REFERENCES motorista(id_motorista),
  FOREIGN KEY (id_funcionario) REFERENCES funcionario(id_funcionario)
);

CREATE TABLE estoque (
  id_estoque INT AUTO_INCREMENT PRIMARY KEY,
  quantidade_geral INT NOT NULL,
  id_veiculo INT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (id_veiculo) REFERENCES veiculo(id_veiculo)
);

CREATE TABLE manutencao (
  id_manutencao INT AUTO_INCREMENT PRIMARY KEY,
  valor VARCHAR(50) NOT NULL,
  descricao VARCHAR(100) NOT NULL,
  data_entrada DATE,
  data_saida DATE,
  id_funcionario INT,
  id_veiculo INT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (id_funcionario) REFERENCES funcionario(id_funcionario),
  FOREIGN KEY (id_veiculo) REFERENCES veiculo(id_veiculo)
);

CREATE TABLE devolucao (
  id_devolucao INT AUTO_INCREMENT PRIMARY KEY,
  cobranca VARCHAR(50),
  descricao VARCHAR(100),
  id_motorista INT,
  id_cliente INT,
  data_devolucao DATE,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (id_motorista) REFERENCES motorista(id_motorista),
  FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
);

CREATE TABLE permissao (
  id_permissao INT AUTO_INCREMENT PRIMARY KEY,
  cargo_permissao VARCHAR(50) NOT NULL,
  id_cliente INT NOT NULL,
  id_funcionario INT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente),
  FOREIGN KEY (id_funcionario) REFERENCES funcionario(id_funcionario)
);

CREATE TABLE programa_fidelidade (
  id_fidelidade INT AUTO_INCREMENT PRIMARY KEY,
  id_cliente INT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
);

CREATE TABLE transacao (
  id_transacao INT AUTO_INCREMENT PRIMARY KEY,
  forma ENUM('pix','debito','credito'),
  descricao VARCHAR(255),
  id_funcionario INT,
  id_cliente INT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (id_funcionario) REFERENCES funcionario(id_funcionario),
  FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
);

SET FOREIGN_KEY_CHECKS = 1;
