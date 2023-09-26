/*
SQLyog Enterprise v12.13 (64 bit)
MySQL - 10.1.19-MariaDB : Database - algafood
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`algafood` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `algafood`;

/*Table structure for table `cidade` */

DROP TABLE IF EXISTS `cidade`;

CREATE TABLE `cidade` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  `estado_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkworrwk40xj58kevvh3evi500` (`estado_id`),
  CONSTRAINT `FKkworrwk40xj58kevvh3evi500` FOREIGN KEY (`estado_id`) REFERENCES `estado` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Table structure for table `cozinha` */

DROP TABLE IF EXISTS `cozinha`;

CREATE TABLE `cozinha` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Table structure for table `estado` */

DROP TABLE IF EXISTS `estado`;

CREATE TABLE `estado` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Table structure for table `forma_pagamento` */

DROP TABLE IF EXISTS `forma_pagamento`;

CREATE TABLE `forma_pagamento` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Table structure for table `grupo` */

DROP TABLE IF EXISTS `grupo`;

CREATE TABLE `grupo` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;

/*Table structure for table `grupo_permissao` */

DROP TABLE IF EXISTS `grupo_permissao`;

CREATE TABLE `grupo_permissao` (
  `grupo_id` BIGINT(20) NOT NULL,
  `permissao_id` BIGINT(20) NOT NULL,
  KEY `FKh21kiw0y0hxg6birmdf2ef6vy` (`permissao_id`),
  KEY `FKta4si8vh3f4jo3bsslvkscc2m` (`grupo_id`),
  CONSTRAINT `FKh21kiw0y0hxg6birmdf2ef6vy` FOREIGN KEY (`permissao_id`) REFERENCES `permissao` (`id`),
  CONSTRAINT `FKta4si8vh3f4jo3bsslvkscc2m` FOREIGN KEY (`grupo_id`) REFERENCES `grupo` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;

/*Table structure for table `hibernate_sequence` */

DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
  `next_val` BIGINT(20) DEFAULT NULL
) ENGINE=INNODB DEFAULT CHARSET=latin1;

/*Table structure for table `permissao` */

DROP TABLE IF EXISTS `permissao`;

CREATE TABLE `permissao` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(255) NOT NULL,
  `nome` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Table structure for table `produto` */

DROP TABLE IF EXISTS `produto`;

CREATE TABLE `produto` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `ativo` BIT(1) NOT NULL,
  `descricao` VARCHAR(255) NOT NULL,
  `nome` VARCHAR(255) NOT NULL,
  `preco` DECIMAL(19,2) NOT NULL,
  `restaurante_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb9jhjyghjcn25guim7q4pt8qx` (`restaurante_id`),
  CONSTRAINT `FKb9jhjyghjcn25guim7q4pt8qx` FOREIGN KEY (`restaurante_id`) REFERENCES `restaurante` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

/*Table structure for table `restaurante` */

DROP TABLE IF EXISTS `restaurante`;

CREATE TABLE `restaurante` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `data_atualizacao` DATETIME NOT NULL,
  `data_cadastro` DATETIME NOT NULL,
  `endereco_bairro` VARCHAR(255) DEFAULT NULL,
  `endereco_cep` VARCHAR(255) DEFAULT NULL,
  `endereco_complemento` VARCHAR(255) DEFAULT NULL,
  `endereco_logradouro` VARCHAR(255) DEFAULT NULL,
  `endereco_numero` VARCHAR(255) DEFAULT NULL,
  `nome` VARCHAR(255) NOT NULL,
  `taxa_frete` DECIMAL(19,2) NOT NULL,
  `cozinha_id` BIGINT(20) NOT NULL,
  `endereco_cidade_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK76grk4roudh659skcgbnanthi` (`cozinha_id`),
  KEY `FKbc0tm7hnvc96d8e7e2ulb05yw` (`endereco_cidade_id`),
  CONSTRAINT `FK76grk4roudh659skcgbnanthi` FOREIGN KEY (`cozinha_id`) REFERENCES `cozinha` (`id`),
  CONSTRAINT `FKbc0tm7hnvc96d8e7e2ulb05yw` FOREIGN KEY (`endereco_cidade_id`) REFERENCES `cidade` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Table structure for table `restaurante_forma_pagamento` */

DROP TABLE IF EXISTS `restaurante_forma_pagamento`;

CREATE TABLE `restaurante_forma_pagamento` (
  `restaurante_id` BIGINT(20) NOT NULL,
  `forma_pagamento_id` BIGINT(20) NOT NULL,
  KEY `FK7aln770m80358y4olr03hyhh2` (`forma_pagamento_id`),
  KEY `FKa30vowfejemkw7whjvr8pryvj` (`restaurante_id`),
  CONSTRAINT `FK7aln770m80358y4olr03hyhh2` FOREIGN KEY (`forma_pagamento_id`) REFERENCES `forma_pagamento` (`id`),
  CONSTRAINT `FKa30vowfejemkw7whjvr8pryvj` FOREIGN KEY (`restaurante_id`) REFERENCES `restaurante` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;

/*Table structure for table `usuario` */

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `data_cadastro` DATETIME NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `nome` VARCHAR(255) NOT NULL,
  `senha` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;

/*Table structure for table `usuario_grupo` */

DROP TABLE IF EXISTS `usuario_grupo`;

CREATE TABLE `usuario_grupo` (
  `usuario_id` BIGINT(20) NOT NULL,
  `grupo_id` BIGINT(20) NOT NULL,
  KEY `FKk30suuy31cq5u36m9am4om9ju` (`grupo_id`),
  KEY `FKdofo9es0esuiahyw2q467crxw` (`usuario_id`),
  CONSTRAINT `FKdofo9es0esuiahyw2q467crxw` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FKk30suuy31cq5u36m9am4om9ju` FOREIGN KEY (`grupo_id`) REFERENCES `grupo` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
