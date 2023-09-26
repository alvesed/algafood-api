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

/*Data for the table `cidade` */

insert  into `cidade`(`id`,`nome`,`estado_id`) values (1,'Uberlândia',1),(2,'Belo Horizonte',1),(3,'São Paulo',2),(4,'Campinas',2),(5,'Fortaleza',3);

/*Data for the table `cozinha` */

insert  into `cozinha`(`id`,`nome`) values (1,'Tailandesa'),(2,'Indiana'),(3,'Argentina'),(4,'Brasileira');

/*Data for the table `estado` */

insert  into `estado`(`id`,`nome`) values (1,'Minas Gerais'),(2,'São Paulo'),(3,'Ceará');

/*Data for the table `forma_pagamento` */

insert  into `forma_pagamento`(`id`,`descricao`) values (1,'Cartão de crédito'),(2,'Cartão de débito'),(3,'Dinheiro');

/*Data for the table `grupo` */

/*Data for the table `grupo_permissao` */

/*Data for the table `hibernate_sequence` */

insert  into `hibernate_sequence`(`next_val`) values (1);

/*Data for the table `permissao` */

insert  into `permissao`(`id`,`descricao`,`nome`) values (1,'Permite consultar cozinhas','CONSULTAR_COZINHAS'),(2,'Permite editar cozinhas','EDITAR_COZINHAS');

/*Data for the table `produto` */

insert  into `produto`(`id`,`ativo`,`descricao`,`nome`,`preco`,`restaurante_id`) values (1,'','Deliciosa carne suína ao molho especial','Porco com molho agridoce','78.90',1),(2,'','16 camarões grandes ao molho picante','Camarão tailandês','110.00',1),(3,'','Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha','Salada picante com carne grelhada','87.20',2),(4,'','Pão tradicional indiano com cobertura de alho','Garlic Naan','21.00',3),(5,'','Cubos de frango preparados com molho curry e especiarias','Murg Curry','43.00',3),(6,'','Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé','Bife Ancho','79.00',4),(7,'','Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon','T-Bone','89.00',4),(8,'','Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese','Sanduíche X-Tudo','19.00',5),(9,'','Acompanha farinha, mandioca e vinagrete','Espetinho de Cupim','8.00',6);

/*Data for the table `restaurante` */

insert  into `restaurante`(`id`,`data_atualizacao`,`data_cadastro`,`endereco_bairro`,`endereco_cep`,`endereco_complemento`,`endereco_logradouro`,`endereco_numero`,`nome`,`taxa_frete`,`cozinha_id`,`endereco_cidade_id`) values (1,'2022-04-05 18:51:10','2022-04-05 18:51:10','Bairro de Uberlandia','12345-123','','Rua do Bairro','123','Thai Gourmet','10.00',1,1),(2,'2022-04-05 18:51:10','2022-04-05 18:51:10','Bairro de SP','12345-123','','Rua do Bairro','123','Thai Delivery','9.50',1,3),(3,'2022-04-05 18:51:10','2022-04-05 18:51:10','Bairro de SP','12345-123','','Rua do Bairro','123','Tuk Tuk Comida Indiana','15.00',2,3),(4,'2022-04-05 18:51:10','2022-04-05 18:51:10','Bairro de Fortaleza','12345-123','','Rua do Bairro','123','Java Steakhouse','12.00',3,5),(5,'2022-04-05 18:51:10','2022-04-05 18:51:10','Bairro de Fortaleza','12345-123','','Rua do Bairro','123','Lanchonete do Tio Sam','11.00',4,5),(6,'2022-04-05 18:51:10','2022-04-05 18:51:10','Bairro de Fortaleza','12345-123','','Rua do Bairro','123','Bar da Maria','6.00',4,5);

/*Data for the table `restaurante_forma_pagamento` */

insert  into `restaurante_forma_pagamento`(`restaurante_id`,`forma_pagamento_id`) values (1,1),(1,2),(1,3),(2,3),(3,2),(3,3),(4,1),(4,2),(5,1),(5,2),(6,3);

/*Data for the table `usuario` */

/*Data for the table `usuario_grupo` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
