SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `dbskymail` DEFAULT CHARACTER SET utf8 ;
USE `dbskymail` ;

-- -----------------------------------------------------
-- Table `dbskymail`.`tbaccount`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbskymail`.`tbaccount` (
  `acc_id` INT(11) NOT NULL AUTO_INCREMENT,
  `acc_name` VARCHAR(45) NULL DEFAULT NULL,
  `acc_mail` VARCHAR(45) NULL DEFAULT NULL,
  `acc_pwd` VARCHAR(45) NULL DEFAULT NULL,
  `acc_type` VARCHAR(7) NULL DEFAULT NULL,
  `acc_inchost` VARCHAR(20) NULL DEFAULT NULL,
  `acc_outhost` VARCHAR(20) NULL DEFAULT NULL,
  `inport` CHAR(5) NULL DEFAULT NULL,
  `outport` CHAR(5) NULL DEFAULT NULL,
  `status` CHAR(2) NULL DEFAULT NULL,
  PRIMARY KEY (`acc_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 51
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `dbskymail`.`tbdrafts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbskymail`.`tbdrafts` (
  `draft_id` INT(11) NOT NULL,
  `msg_from` VARCHAR(150) NULL DEFAULT NULL,
  `msg_sub` VARCHAR(150) NULL DEFAULT NULL,
  `msg_dte` VARCHAR(100) NULL DEFAULT NULL,
  `msg_size` VARCHAR(45) NULL DEFAULT NULL,
  INDEX `draft_id_idx` (`draft_id` ASC),
  CONSTRAINT `draft_id`
    FOREIGN KEY (`draft_id`)
    REFERENCES `dbskymail`.`tbaccount` (`acc_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `dbskymail`.`tbinbox`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbskymail`.`tbinbox` (
  `inbox_id` INT(11) NOT NULL,
  `msg_from` VARCHAR(150) NULL DEFAULT NULL,
  `msg_sub` VARCHAR(150) NULL DEFAULT NULL,
  `msg_dte` VARCHAR(100) NULL DEFAULT NULL,
  `msg_size` VARCHAR(45) NULL DEFAULT NULL,
  INDEX `acc_id_idx` (`inbox_id` ASC),
  CONSTRAINT `inbox_id`
    FOREIGN KEY (`inbox_id`)
    REFERENCES `dbskymail`.`tbaccount` (`acc_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `dbskymail`.`tbsent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbskymail`.`tbsent` (
  `sent_id` INT(11) NOT NULL,
  `msg_from` VARCHAR(150) NULL DEFAULT NULL,
  `msg_sub` VARCHAR(150) NULL DEFAULT NULL,
  `msg_dte` VARCHAR(100) NULL DEFAULT NULL,
  `msg_size` VARCHAR(45) NULL DEFAULT NULL,
  INDEX `acc_id_idx` (`sent_id` ASC),
  CONSTRAINT `sent_id`
    FOREIGN KEY (`sent_id`)
    REFERENCES `dbskymail`.`tbaccount` (`acc_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `dbskymail`.`tbtrash`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbskymail`.`tbtrash` (
  `trash_id` INT(11) NULL DEFAULT NULL,
  `msg_from` VARCHAR(150) NULL DEFAULT NULL,
  `msg_sub` VARCHAR(150) NULL DEFAULT NULL,
  `msg_dte` VARCHAR(100) NULL DEFAULT NULL,
  `msg_size` VARCHAR(45) NULL DEFAULT NULL,
  INDEX `trash_id_idx` (`trash_id` ASC),
  CONSTRAINT `trash_id`
    FOREIGN KEY (`trash_id`)
    REFERENCES `dbskymail`.`tbaccount` (`acc_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
