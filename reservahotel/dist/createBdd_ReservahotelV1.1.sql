-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema reservahotel
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `reservahotel` ;

-- -----------------------------------------------------
-- Schema reservahotel
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `reservahotel` DEFAULT CHARACTER SET utf8 ;
USE `reservahotel` ;

-- -----------------------------------------------------
-- Table `reservahotel`.`piso_habitacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reservahotel`.`piso_habitacion` (
  `id_pishab` INT NOT NULL AUTO_INCREMENT,
  `nombre_pishab` VARCHAR(25) NOT NULL,
  `descripcion_pishab` VARCHAR(45) NULL,
  PRIMARY KEY (`id_pishab`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reservahotel`.`estado_habitacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reservahotel`.`estado_habitacion` (
  `id_esthab` INT NOT NULL AUTO_INCREMENT,
  `nombre_esthab` VARCHAR(15) NOT NULL,
  `descripcion_esthab` VARCHAR(45) NULL,
  PRIMARY KEY (`id_esthab`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reservahotel`.`tipo_habitacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reservahotel`.`tipo_habitacion` (
  `id_tiphab` INT NOT NULL AUTO_INCREMENT,
  `nombre_tiphab` VARCHAR(20) NOT NULL,
  `descripcion_tiphab` VARCHAR(45) NULL,
  PRIMARY KEY (`id_tiphab`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reservahotel`.`habitacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reservahotel`.`habitacion` (
  `id_hab` INT NOT NULL AUTO_INCREMENT COMMENT 'Llava primaria identificador de la tabla trabajador.',
  `numero_hab` VARCHAR(4) NOT NULL COMMENT 'Número de habitación.',
  `descripcion_hab` VARCHAR(225) NULL COMMENT 'Descripción de la habitación.',
  `caracteristicas_hab` VARCHAR(512) NULL COMMENT 'Caracteristicas de la habiatación.',
  `precio_diario_hab` DECIMAL(7,2) NOT NULL COMMENT 'Precio de la habitación.',
  `pisohabitacion_id_pishab` INT NOT NULL,
  `estadohabitacion_id_esthab` INT NOT NULL,
  `tipohabitacion_id_tiphab` INT NOT NULL,
  PRIMARY KEY (`id_hab`),
  INDEX `fk_habitacion_pisohabitacion1_idx` (`pisohabitacion_id_pishab` ASC),
  INDEX `fk_habitacion_estadohabitacion1_idx` (`estadohabitacion_id_esthab` ASC),
  INDEX `fk_habitacion_tipohabitacion1_idx` (`tipohabitacion_id_tiphab` ASC),
  CONSTRAINT `fk_habitacion_pisohabitacion1`
    FOREIGN KEY (`pisohabitacion_id_pishab`)
    REFERENCES `reservahotel`.`piso_habitacion` (`id_pishab`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_habitacion_estadohabitacion1`
    FOREIGN KEY (`estadohabitacion_id_esthab`)
    REFERENCES `reservahotel`.`estado_habitacion` (`id_esthab`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_habitacion_tipohabitacion1`
    FOREIGN KEY (`tipohabitacion_id_tiphab`)
    REFERENCES `reservahotel`.`tipo_habitacion` (`id_tiphab`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reservahotel`.`persona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reservahotel`.`persona` (
  `id_per` INT NOT NULL AUTO_INCREMENT COMMENT 'LLave primaria identificador de la tabla persona.',
  `nombre_per` VARCHAR(20) NOT NULL COMMENT 'nombre de la persona.',
  `apellido_per` VARCHAR(20) NOT NULL COMMENT 'apellido de la persona.',
  `tipo_documento_per` VARCHAR(15) NOT NULL COMMENT 'Tipo de documento, identificación cedula, ruc, etc.',
  `numero_documento_per` VARCHAR(13) NOT NULL COMMENT 'Número del tipo de documento, nuemro de cedula o RUC.',
  `direccion_per` VARCHAR(10) NULL COMMENT 'Descripcion de la dirección.\n',
  `telefono_per` VARCHAR(13) NOT NULL COMMENT 'Número de teléfono.',
  `email_per` VARCHAR(100) NOT NULL COMMENT 'Correo electronico de la persona.',
  PRIMARY KEY (`id_per`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reservahotel`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reservahotel`.`cliente` (
  `id_per` INT NOT NULL,
  `codigo_cli` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id_per`),
  UNIQUE INDEX `id_codigo_UNIQUE` (`codigo_cli` ASC),
  CONSTRAINT `fk_persona_cliente`
    FOREIGN KEY (`id_per`)
    REFERENCES `reservahotel`.`persona` (`id_per`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reservahotel`.`trabajador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reservahotel`.`trabajador` (
  `id_per` INT NOT NULL,
  `sueldo_tra` DECIMAL(7,2) NOT NULL,
  `acceso_tra` VARCHAR(15) NOT NULL,
  `estado_tra` VARCHAR(1) NOT NULL,
  PRIMARY KEY (`id_per`),
  INDEX `fk_trabajador_persona_idx` (`id_per` ASC),
  CONSTRAINT `fk_trabajador_persona`
    FOREIGN KEY (`id_per`)
    REFERENCES `reservahotel`.`persona` (`id_per`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reservahotel`.`producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reservahotel`.`producto` (
  `id_pro` INT NOT NULL AUTO_INCREMENT,
  `nombre_pro` VARCHAR(45) NOT NULL,
  `descripcion_pro` VARCHAR(45) NULL,
  `unidad_medida_pro` VARCHAR(45) NOT NULL,
  `precio_venta_pro` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_pro`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reservahotel`.`reserva`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reservahotel`.`reserva` (
  `id_res` INT NOT NULL AUTO_INCREMENT,
  `id_hab` INT NOT NULL,
  `id-cli` INT NOT NULL,
  `id_tra` INT NOT NULL,
  `tipo_res` VARCHAR(45) NOT NULL,
  `fecha_res` DATE NOT NULL,
  `fecha_ingresa_res` DATE NOT NULL,
  `fecha_salida_res` DATE NOT NULL,
  `costo_alojamiento_res` DECIMAL(7,2) NOT NULL,
  `estado_res` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id_res`),
  INDEX `fk_reserva_habitacion_idx` (`id_hab` ASC),
  INDEX `fk_reserva_cliente_idx` (`id-cli` ASC),
  INDEX `fk_reserva_trabajador_idx` (`id_tra` ASC),
  CONSTRAINT `fk_reserva_habitacion`
    FOREIGN KEY (`id_hab`)
    REFERENCES `reservahotel`.`habitacion` (`id_hab`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reserva_cliente`
    FOREIGN KEY (`id-cli`)
    REFERENCES `reservahotel`.`cliente` (`id_per`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reserva_trabajador`
    FOREIGN KEY (`id_tra`)
    REFERENCES `reservahotel`.`trabajador` (`id_per`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reservahotel`.`consumo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reservahotel`.`consumo` (
  `id_com` INT NOT NULL AUTO_INCREMENT,
  `id_res` INT NOT NULL,
  `id_pro` INT NOT NULL,
  `cantidad_con` DECIMAL(7,2) NOT NULL,
  `precio_venta_con` DECIMAL(7,2) NOT NULL,
  `estado_con` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id_com`),
  INDEX `fk_consumo_producto_idx` (`id_pro` ASC),
  INDEX `fk_consumo_reserva_idx` (`id_res` ASC),
  CONSTRAINT `fk_consumo_producto`
    FOREIGN KEY (`id_pro`)
    REFERENCES `reservahotel`.`producto` (`id_pro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_consumo_reserva`
    FOREIGN KEY (`id_res`)
    REFERENCES `reservahotel`.`reserva` (`id_res`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reservahotel`.`pago`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reservahotel`.`pago` (
  `id_pag` INT NOT NULL AUTO_INCREMENT,
  `id_res` INT NOT NULL,
  `tipo_comprobante_pag` VARCHAR(20) NOT NULL,
  `num_comprobante_pag` VARCHAR(20) NOT NULL,
  `iva_pag` DECIMAL(7,2) NOT NULL,
  `total_pag` DECIMAL(7,2) NOT NULL,
  `fecha_emision_pag` DATE NOT NULL,
  `fecha_pag` DATE NOT NULL,
  PRIMARY KEY (`id_pag`),
  INDEX `fk_pago_reserva_idx` (`id_res` ASC),
  CONSTRAINT `fk_pago_reserva`
    FOREIGN KEY (`id_res`)
    REFERENCES `reservahotel`.`reserva` (`id_res`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reservahotel`.`rol`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reservahotel`.`rol` (
  `id_rol` INT NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria identificador de la tabla rol.',
  `nombre_rol` VARCHAR(45) NOT NULL COMMENT 'Nombre del rol que el usuario puede tener.',
  `descripcion_rol` VARCHAR(45) NOT NULL COMMENT 'Descripción del rol, describe el acceso a los diferentes acceso al sistema.',
  PRIMARY KEY (`id_rol`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reservahotel`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reservahotel`.`usuario` (
  `id_usu` INT NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria identificador de la tabla usuario.',
  `nombre_usu` VARCHAR(10) NOT NULL COMMENT 'Nombre o login del usuario que se ingresa al sistema.',
  `clave_usu` VARCHAR(10) NOT NULL COMMENT 'Clave del usuario que se ingresa al sistema.',
  `rol_id_rol` INT NOT NULL,
  PRIMARY KEY (`id_usu`),
  INDEX `fk_usuario_rol1_idx` (`rol_id_rol` ASC),
  CONSTRAINT `fk_usuario_rol1`
    FOREIGN KEY (`rol_id_rol`)
    REFERENCES `reservahotel`.`rol` (`id_rol`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
