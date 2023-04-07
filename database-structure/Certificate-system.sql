-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema certificate-system
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema certificate-system
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `certificate-system` DEFAULT CHARACTER SET utf8 ;
USE `certificate-system` ;

-- -----------------------------------------------------
-- Table `gift_certificates`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gift_certificates` ;

CREATE TABLE IF NOT EXISTS `gift_certificates` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT(500) NULL,
  `price` FLOAT NOT NULL,
  `duration` DATETIME NOT NULL,
  `create_date` TIMESTAMP NOT NULL,
  `last_update_date` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tags`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tags` ;

CREATE TABLE IF NOT EXISTS `tags` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gift_certificates_tags`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gift_certificates_tags` ;

CREATE TABLE IF NOT EXISTS `gift_certificates_tags` (
  `gift_certificate_id` INT UNSIGNED NOT NULL,
  `tag_id` INT UNSIGNED NOT NULL,
  INDEX `fk_gift_certificates_tags_gift_certificates_idx` (`gift_certificate_id` ASC) VISIBLE,
  INDEX `fk_gift_certificates_tags_tags1_idx` (`tag_id` ASC) VISIBLE,
  CONSTRAINT `fk_gift_certificates_tags_gift_certificates`
    FOREIGN KEY (`gift_certificate_id`)
    REFERENCES `gift_certificates` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_gift_certificates_tags_tags1`
    FOREIGN KEY (`tag_id`)
    REFERENCES `tags` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
