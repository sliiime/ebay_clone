-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema ebay_clone
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ebay_clone
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ebay_clone` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `ebay_clone` ;

-- -----------------------------------------------------
-- Table `ebay_clone`.`country`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ebay_clone`.`country` ;

CREATE TABLE IF NOT EXISTS `ebay_clone`.`country` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(120) NOT NULL,
  `iso` VARCHAR(2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  UNIQUE INDEX `code_UNIQUE` (`iso` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ebay_clone`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ebay_clone`.`user` ;

CREATE TABLE IF NOT EXISTS `ebay_clone`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(70) NULL,
  `name` VARCHAR(45) NULL,
  `surname` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `AFM` VARCHAR(45) NULL,
  `rating` FLOAT NULL,
  `registered` ENUM("ACCEPTED", "DECLINED", "PENDING") NULL,
  `phone` VARCHAR(45) NULL,
  `country_id` INT NOT NULL,
  PRIMARY KEY (`id`, `country_id`),
  INDEX `fk_user_country1_idx` (`country_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_country1`
    FOREIGN KEY (`country_id`)
    REFERENCES `ebay_clone`.`country` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ebay_clone`.`item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ebay_clone`.`item` ;

CREATE TABLE IF NOT EXISTS `ebay_clone`.`item` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `buy_price` FLOAT NULL,
  `min_bid` FLOAT NULL,
  `latitude` DECIMAL(10,8) NULL,
  `longitude` DECIMAL(10,8) NULL,
  `start_date` DATETIME NULL,
  `end_date` DATETIME NULL,
  `description` VARCHAR(200) NULL,
  `category` VARCHAR(45) NULL,
  `country_id` INT NOT NULL DEFAULT 1,
  `seller` INT NOT NULL DEFAULT 1,
  `status` ENUM("PREVIEW", "ONGOING", "NOT_BOUGHT", "BOUGHT_TIMEOUT", "BOUGHT_BUYOUT") NULL,
  PRIMARY KEY (`id`, `country_id`, `seller`),
  INDEX `fk_Item_Country1_idx` (`country_id` ASC) VISIBLE,
  INDEX `fk_Item_User1_idx` (`seller` ASC) VISIBLE,
  CONSTRAINT `fk_Item_Country1`
    FOREIGN KEY (`country_id`)
    REFERENCES `ebay_clone`.`country` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Item_User1`
    FOREIGN KEY (`seller`)
    REFERENCES `ebay_clone`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ebay_clone`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ebay_clone`.`category` ;

CREATE TABLE IF NOT EXISTS `ebay_clone`.`category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ebay_clone`.`item_has_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ebay_clone`.`item_has_category` ;

CREATE TABLE IF NOT EXISTS `ebay_clone`.`item_has_category` (
  `Item_id` INT NOT NULL,
  `Category_id` INT NOT NULL,
  PRIMARY KEY (`Item_id`, `Category_id`),
  INDEX `fk_Item_has_Category_Category1_idx` (`Category_id` ASC) VISIBLE,
  INDEX `fk_Item_has_Category_Item1_idx` (`Item_id` ASC) VISIBLE,
  CONSTRAINT `fk_Item_has_Category_Item1`
    FOREIGN KEY (`Item_id`)
    REFERENCES `ebay_clone`.`item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Item_has_Category_Category1`
    FOREIGN KEY (`Category_id`)
    REFERENCES `ebay_clone`.`category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ebay_clone`.`bid`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ebay_clone`.`bid` ;

CREATE TABLE IF NOT EXISTS `ebay_clone`.`bid` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `submission_date` DATETIME NULL,
  `price` FLOAT NULL,
  `item_id` INT NOT NULL,
  `bidder` INT NOT NULL,
  PRIMARY KEY (`id`, `item_id`, `bidder`),
  INDEX `fk_Bids_Item1_idx` (`item_id` ASC) VISIBLE,
  INDEX `fk_Bids_User1_idx` (`bidder` ASC) VISIBLE,
  CONSTRAINT `fk_Bids_Item1`
    FOREIGN KEY (`item_id`)
    REFERENCES `ebay_clone`.`item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Bids_User1`
    FOREIGN KEY (`bidder`)
    REFERENCES `ebay_clone`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ebay_clone`.`rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ebay_clone`.`rating` ;

CREATE TABLE IF NOT EXISTS `ebay_clone`.`rating` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rating` INT NULL,
  `ratedBy` INT NOT NULL,
  `rated` INT NOT NULL,
  PRIMARY KEY (`id`, `ratedBy`, `rated`),
  INDEX `fk_Rating_User1_idx` (`ratedBy` ASC) VISIBLE,
  INDEX `fk_Rating_User2_idx` (`rated` ASC) VISIBLE,
  CONSTRAINT `fk_Rating_User1`
    FOREIGN KEY (`ratedBy`)
    REFERENCES `ebay_clone`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Rating_User2`
    FOREIGN KEY (`rated`)
    REFERENCES `ebay_clone`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ebay_clone`.`message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ebay_clone`.`message` ;

CREATE TABLE IF NOT EXISTS `ebay_clone`.`message` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `body` VARCHAR(200) NULL,
  `sender` INT NOT NULL,
  `receiver` INT NOT NULL,
  `seen` TINYINT(1) NULL,
  PRIMARY KEY (`id`, `sender`, `receiver`),
  INDEX `fk_Message_User1_idx` (`sender` ASC) VISIBLE,
  INDEX `fk_Message_User2_idx` (`receiver` ASC) VISIBLE,
  CONSTRAINT `fk_Message_User1`
    FOREIGN KEY (`sender`)
    REFERENCES `ebay_clone`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Message_User2`
    FOREIGN KEY (`receiver`)
    REFERENCES `ebay_clone`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ebay_clone`.`admin`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ebay_clone`.`admin` ;

CREATE TABLE IF NOT EXISTS `ebay_clone`.`admin` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ebay_clone`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ebay_clone`.`role` ;

CREATE TABLE IF NOT EXISTS `ebay_clone`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ebay_clone`.`user_has_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ebay_clone`.`user_has_role` ;

CREATE TABLE IF NOT EXISTS `ebay_clone`.`user_has_role` (
  `user_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  INDEX `fk_user_has_role_role1_idx` (`role_id` ASC) VISIBLE,
  INDEX `fk_user_has_role_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_role_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `ebay_clone`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_role_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `ebay_clone`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
