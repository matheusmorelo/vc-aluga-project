-- MySQL dump 10.13  Distrib 8.0.32, for Linux (x86_64)
--
-- Host: localhost    Database: voce_aluga
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `agencia`
--

DROP TABLE IF EXISTS `agencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `agencia` (
                           `id_agencia` int NOT NULL AUTO_INCREMENT,
                           `contato` varchar(100) DEFAULT NULL,
                           `relatorio` varchar(255) DEFAULT NULL,
                           `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                           `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           PRIMARY KEY (`id_agencia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agencia`
--

LOCK TABLES `agencia` WRITE;
/*!40000 ALTER TABLE `agencia` DISABLE KEYS */;
/*!40000 ALTER TABLE `agencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
                           `id_cliente` int NOT NULL AUTO_INCREMENT,
                           `nome` varchar(255) NOT NULL,
                           `cpf` varchar(11) NOT NULL,
                           `cnpj` varchar(14) DEFAULT NULL,
                           `idade` int NOT NULL,
                           `cnh` int NOT NULL,
                           `contato` int NOT NULL,
                           `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                           `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `devolucao`
--

DROP TABLE IF EXISTS `devolucao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `devolucao` (
                             `id_devolucao` int NOT NULL AUTO_INCREMENT,
                             `cobranca` varchar(50) DEFAULT NULL,
                             `descricao` varchar(100) DEFAULT NULL,
                             `id_motorista` int DEFAULT NULL,
                             `id_cliente` int DEFAULT NULL,
                             `data_devolucao` date DEFAULT NULL,
                             `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                             `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                             PRIMARY KEY (`id_devolucao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `devolucao`
--

LOCK TABLES `devolucao` WRITE;
/*!40000 ALTER TABLE `devolucao` DISABLE KEYS */;
/*!40000 ALTER TABLE `devolucao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estoque`
--

DROP TABLE IF EXISTS `estoque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estoque` (
                           `id_estoque` int NOT NULL AUTO_INCREMENT,
                           `quantidade_geral` int NOT NULL,
                           `id_veiculo` int DEFAULT NULL,
                           `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                           `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           PRIMARY KEY (`id_estoque`),
                           KEY `id_veiculo` (`id_veiculo`),
                           CONSTRAINT `estoque_ibfk_1` FOREIGN KEY (`id_veiculo`) REFERENCES `veiculo` (`id_veiculo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estoque`
--

LOCK TABLES `estoque` WRITE;
/*!40000 ALTER TABLE `estoque` DISABLE KEYS */;
/*!40000 ALTER TABLE `estoque` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funcionario` (
                               `id_funcionario` int NOT NULL AUTO_INCREMENT,
                               `nome` varchar(255) NOT NULL,
                               `documento` enum('cpf','rg') NOT NULL,
                               `contato` varchar(11) NOT NULL,
                               `cargo` varchar(155) NOT NULL,
                               `id_cliente` int DEFAULT NULL,
                               `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                               `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                               PRIMARY KEY (`id_funcionario`),
                               KEY `id_cliente` (`id_cliente`),
                               CONSTRAINT `funcionario_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mauntencao`
--

DROP TABLE IF EXISTS manutencao;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mauntencao` (
                              `id_manutencao` int NOT NULL AUTO_INCREMENT,
                              `valor` varchar(50) NOT NULL,
                              `descricao` varchar(100) NOT NULL,
                              `data_entrada` date DEFAULT NULL,
                              `data_saida` date DEFAULT NULL,
                              `id_funcionario` int DEFAULT NULL,
                              `id_veiculo` int DEFAULT NULL,
                              `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                              `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              PRIMARY KEY (`id_manutencao`),
                              KEY `id_veiculo` (`id_veiculo`),
                              KEY `id_funcionario` (`id_funcionario`),
                              CONSTRAINT `mauntencao_ibfk_1` FOREIGN KEY (`id_veiculo`) REFERENCES `veiculo` (`id_veiculo`),
                              CONSTRAINT `mauntencao_ibfk_2` FOREIGN KEY (`id_funcionario`) REFERENCES `funcionario` (`id_funcionario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mauntencao`
--

LOCK TABLES manutencao WRITE;
/*!40000 ALTER TABLE manutencao DISABLE KEYS */;
/*!40000 ALTER TABLE manutencao ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `motorista`
--

DROP TABLE IF EXISTS `motorista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `motorista` (
                             `id_motorista` int NOT NULL AUTO_INCREMENT,
                             `cnh` varchar(40) NOT NULL,
                             `nome` varchar(50) NOT NULL,
                             `id_cliente` int DEFAULT NULL,
                             `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                             `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                             PRIMARY KEY (`id_motorista`),
                             KEY `id_cliente` (`id_cliente`),
                             CONSTRAINT `motorista_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `motorista`
--

LOCK TABLES `motorista` WRITE;
/*!40000 ALTER TABLE `motorista` DISABLE KEYS */;
/*!40000 ALTER TABLE `motorista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissao`
--

DROP TABLE IF EXISTS `permissao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permissao` (
                             `id_permissao` int NOT NULL AUTO_INCREMENT,
                             `cargo_permissao` varchar(50) NOT NULL,
                             `id_cliente` int NOT NULL,
                             `id_funcionario` int DEFAULT NULL,
                             `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                             `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                             PRIMARY KEY (`id_permissao`),
                             KEY `id_cliente` (`id_cliente`),
                             KEY `id_funcionario` (`id_funcionario`),
                             CONSTRAINT `permissao_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`),
                             CONSTRAINT `permissao_ibfk_2` FOREIGN KEY (`id_funcionario`) REFERENCES `funcionario` (`id_funcionario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissao`
--

LOCK TABLES `permissao` WRITE;
/*!40000 ALTER TABLE `permissao` DISABLE KEYS */;
/*!40000 ALTER TABLE `permissao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `programa_fidelidade`
--

DROP TABLE IF EXISTS `programa_fidelidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `programa_fidelidade` (
                                       `id_fidelidade` int NOT NULL AUTO_INCREMENT,
                                       `id_cliente` int DEFAULT NULL,
                                       `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                       `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                       PRIMARY KEY (`id_fidelidade`),
                                       KEY `id_cliente` (`id_cliente`),
                                       CONSTRAINT `programa_fidelidade_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `programa_fidelidade`
--

LOCK TABLES `programa_fidelidade` WRITE;
/*!40000 ALTER TABLE `programa_fidelidade` DISABLE KEYS */;
/*!40000 ALTER TABLE `programa_fidelidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transacao`
--

DROP TABLE IF EXISTS `transacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transacao` (
                             `id_transacao` int NOT NULL AUTO_INCREMENT,
                             `forma` enum('pix','debito','credito') DEFAULT NULL,
                             `descricao` varchar(255) DEFAULT NULL,
                             `id_funcionario` int DEFAULT NULL,
                             `id_cliente` int DEFAULT NULL,
                             `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                             `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                             PRIMARY KEY (`id_transacao`),
                             KEY `id_cliente` (`id_cliente`),
                             KEY `id_funcionario` (`id_funcionario`),
                             CONSTRAINT `transacao_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`),
                             CONSTRAINT `transacao_ibfk_2` FOREIGN KEY (`id_funcionario`) REFERENCES `funcionario` (`id_funcionario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transacao`
--

LOCK TABLES `transacao` WRITE;
/*!40000 ALTER TABLE `transacao` DISABLE KEYS */;
/*!40000 ALTER TABLE `transacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `veiculo`
--

DROP TABLE IF EXISTS `veiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `veiculo` (
                           `id_veiculo` int NOT NULL AUTO_INCREMENT,
                           `placa` varchar(50) DEFAULT NULL,
                           `modelo` varchar(50) NOT NULL,
                           `cor` varchar(50) NOT NULL,
                           `ano` int NOT NULL,
                           `km` int NOT NULL,
                           `situacao` enum('alugado','disponivel') NOT NULL,
                           `descricao` varchar(100) NOT NULL,
                           `id_agencia` int DEFAULT NULL,
                           `id_cliente` int DEFAULT NULL,
                           `id_motorista` int DEFAULT NULL,
                           `id_funcionario` int DEFAULT NULL,
                           `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                           `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           PRIMARY KEY (`id_veiculo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `veiculo`
--

LOCK TABLES `veiculo` WRITE;
/*!40000 ALTER TABLE `veiculo` DISABLE KEYS */;
/*!40000 ALTER TABLE `veiculo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-21 17:45:31