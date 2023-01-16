CREATE SCHEMA `nota_fiscal_venda`;
USE `nota_fiscal_venda`;

CREATE TABLE `Clientes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cpf` VARCHAR(45) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE
);

CREATE TABLE `NotaFiscalVenda` (
  `numero_da_nota` INT NOT NULL AUTO_INCREMENT,
  `emissao` DATE NULL,
  `expedicao` DATE NULL,
  `Clientes_id` INT NOT NULL,
  PRIMARY KEY (`numero_da_nota`),
  UNIQUE INDEX `id_UNIQUE` (`numero_da_nota` ASC) VISIBLE,
  INDEX `fk_NotaFiscalVenda_Clientes1_idx` (`Clientes_id` ASC) VISIBLE,
  CONSTRAINT `fk_NotaFiscalVenda_Clientes1` FOREIGN KEY (`Clientes_id`) REFERENCES `Clientes` (`id`)
);

CREATE TABLE `Produtos` (
  `codigo_identificador` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `descricao_completa` VARCHAR(100) NULL,
  `preco_unitario` FLOAT NULL,
  PRIMARY KEY (`codigo_identificador`),
  UNIQUE INDEX `id_UNIQUE` (`codigo_identificador` ASC) VISIBLE
);

CREATE TABLE `NotaFiscalVenda_has_Produtos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `NotaFiscalVenda_numero_da_nota` INT NOT NULL,
  `Produtos_codigo_identificador` INT NOT NULL,
  `unidades_vendida` INT NULL,
  PRIMARY KEY (`NotaFiscalVenda_numero_da_nota`, `id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_NotaFiscalVenda_has_Produtos_Produtos1_idx` (`Produtos_codigo_identificador` ASC) VISIBLE,
  INDEX `fk_NotaFiscalVenda_has_Produtos_NotaFiscalVenda1_idx` (`NotaFiscalVenda_numero_da_nota` ASC) VISIBLE,
  CONSTRAINT `fk_NotaFiscalVenda_has_Produtos_NotaFiscalVenda1` FOREIGN KEY (`NotaFiscalVenda_numero_da_nota`) REFERENCES `NotaFiscalVenda` (`numero_da_nota`),
  CONSTRAINT `fk_NotaFiscalVenda_has_Produtos_Produtos1` FOREIGN KEY (`Produtos_codigo_identificador`) REFERENCES `Produtos` (`codigo_identificador`)
);
