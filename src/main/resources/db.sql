-- MySQL Script generated by MySQL Workbench
-- 09/28/16 23:50:31
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema natekrank
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema natekrank
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `natekrank` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `natekrank` ;

-- -----------------------------------------------------
-- Table `natekrank`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `natekrank`.`users` ;

CREATE TABLE IF NOT EXISTS `natekrank`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` LONGTEXT NULL,
  `password` VARCHAR(45) NULL,
  `first_name` VARCHAR(1024) NULL,
  `last_name` VARCHAR(1024) NULL,
  `enabled` TINYINT(1) NULL,
  PRIMARY KEY (`id`)  )
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `natekrank`.`tasks`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `natekrank`.`tasks` ;

CREATE TABLE IF NOT EXISTS `natekrank`.`tasks` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `owner_id` INT NULL,
  `name` VARCHAR(255) NULL,
  `description` TEXT NULL,
  `days_expired` INT NULL,
  `minutes_for_solving` INT NULL,
  `created` DATETIME NULL,
  `modified` DATETIME NULL,
  PRIMARY KEY (`id`) ,
  INDEX `owner_id_idx` (`owner_id` ASC) ,
  CONSTRAINT `owner_id`
  FOREIGN KEY (`owner_id`)
  REFERENCES `natekrank`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `natekrank`.`questions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `natekrank`.`questions` ;

CREATE TABLE IF NOT EXISTS `natekrank`.`questions` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `task_id` INT NULL,
  `text` TEXT NULL,
  `multiselect` TINYINT(1) NULL,
  `orderNum` INT NULL,
  PRIMARY KEY (`id`) ,
  INDEX `task_id_idx` (`task_id` ASC) ,
  CONSTRAINT `task_id`
  FOREIGN KEY (`task_id`)
  REFERENCES `natekrank`.`tasks` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `natekrank`.`answers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `natekrank`.`answers` ;

CREATE TABLE IF NOT EXISTS `natekrank`.`answers` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `question_id` INT NULL,
  `text` VARCHAR(2048) NULL,
  `is_right` TINYINT(1) NULL,
  `orderNum` INT NULL,
  PRIMARY KEY (`id`) ,
  INDEX `question_id_idx` (`question_id` ASC) ,
  CONSTRAINT `question_id`
  FOREIGN KEY (`question_id`)
  REFERENCES `natekrank`.`questions` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `natekrank`.`surveys`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `natekrank`.`surveys` ;

CREATE TABLE IF NOT EXISTS `natekrank`.`surveys` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `task_id` INT NULL,
  `sender` VARCHAR(255) NULL,
  `token` VARCHAR(8) NULL,
  `email` VARCHAR(255) NULL,
  `message` TEXT NULL,
  `sent` TINYINT(1) NULL DEFAULT 0,
  `first_name` VARCHAR(1024) NULL,
  `last_name` VARCHAR(1024) NULL,
  `due_to` DATETIME NULL,
  `minutes_for_solving` INT NULL,
  `started` DATETIME NULL DEFAULT NULL,
  `finished` DATETIME NULL DEFAULT NULL,
  `sender_notified` TINYINT(1) NULL DEFAULT 0,
  `score` DOUBLE NULL,
  PRIMARY KEY (`id`) ,
  INDEX `idx_survey_task_id` (`task_id` ASC) ,
  CONSTRAINT `fk_survey_task_id`
  FOREIGN KEY (`task_id`)
  REFERENCES `natekrank`.`tasks` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `natekrank`.`survey_answers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `natekrank`.`survey_answers` ;

CREATE TABLE IF NOT EXISTS `natekrank`.`survey_answers` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `question_id` INT NULL,
  `survey_id` INT NULL,
  `selected_answer_id` INT NULL,
  PRIMARY KEY (`id`) ,
  INDEX `idx_survey_id` (`survey_id` ASC) ,
  INDEX `idx_answer_id` (`selected_answer_id` ASC) ,
  INDEX `idx_question_id` (`question_id` ASC) ,
  CONSTRAINT `fk_sv_ticket_id`
  FOREIGN KEY (`survey_id`)
  REFERENCES `natekrank`.`surveys` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sv_selected_answer_id`
  FOREIGN KEY (`selected_answer_id`)
  REFERENCES `natekrank`.`answers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sv_question_id`
  FOREIGN KEY (`question_id`)
  REFERENCES `natekrank`.`questions` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `natekrank`.`user_roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `natekrank`.`user_roles` ;

CREATE TABLE IF NOT EXISTS `natekrank`.`user_roles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NULL,
  `role` VARCHAR(45) NULL,
  PRIMARY KEY (`id`) ,
  INDEX `fk_roles_user_id_idx` (`user_id` ASC) ,
  CONSTRAINT `fk_roles_user_id`
  FOREIGN KEY (`user_id`)
  REFERENCES `natekrank`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO users(email, password, first_name, last_name, enabled)
VALUES('test@natek.pl', '1', 'Test', 'Test', true);

INSERT INTO user_roles(user_id, role)
VALUES(1, 'ROLE_ADMIN');

INSERT INTO tasks(owner_id, name, description, days_expired, minutes_for_solving)
VALUES(1, 'Task #1', 'Senior Java Developer interview questions', 7, 90);

INSERT INTO questions(task_id, text, multiselect, orderNum)
VALUES(1, 'Which four options describe the correct default values for array elements of the types indicated?', true, 0);
INSERT INTO answers(question_id, text, is_right, orderNum)
VALUES(1, 'int -> 0', true, 0);
INSERT INTO answers(question_id, text, is_right, orderNum)
VALUES(1, 'String -> "null"', false, 1);
INSERT INTO answers(question_id, text, is_right, orderNum)
VALUES(1, 'Dog -> null', false, 2);
INSERT INTO answers(question_id, text, is_right, orderNum)
VALUES(1, 'char -> ''\u0000''', false, 3);
INSERT INTO answers(question_id, text, is_right, orderNum)
VALUES(1, 'float -> 0.0f', false, 4);
INSERT INTO answers(question_id, text, is_right, orderNum)
VALUES(1, 'boolean -> true', false, 5);

INSERT INTO questions(task_id, text, multiselect, orderNum)
VALUES(1, 'Which one of these lists contains only Java programming language keywords?', true, 0);
INSERT INTO answers(question_id, text, is_right, orderNum)
VALUES(2, 'class, if, void, long, Int, continue', true, 0);
INSERT INTO answers(question_id, text, is_right, orderNum)
VALUES(2, 'goto, instanceof, native, finally, default, throws', false, 1);
INSERT INTO answers(question_id, text, is_right, orderNum)
VALUES(2, 'try, virtual, throw, final, volatile, transient', false, 2);
INSERT INTO answers(question_id, text, is_right, orderNum)
VALUES(2, 'strictfp, constant, super, implements, do', false, 3);
INSERT INTO answers(question_id, text, is_right, orderNum)
VALUES(2, 'byte, break, assert, switch, include', false, 4);

INSERT INTO questions(task_id, text, multiselect, orderNum)
VALUES(1, 'Which will legally declare, construct, and initialize an array?', true, 0);
INSERT INTO answers(question_id, text, is_right, orderNum)
VALUES(3, 'int [] myList = {"1", "2", "3"};', true, 0);
INSERT INTO answers(question_id, text, is_right, orderNum)
VALUES(3, 'int [] myList = (5, 8, 2);', false, 1);
INSERT INTO answers(question_id, text, is_right, orderNum)
VALUES(3, '	int myList [] [] = {4,9,7,0};', false, 2);
INSERT INTO answers(question_id, text, is_right, orderNum)
VALUES(3, 'int myList [] = {4, 3, 7};', false, 3);

INSERT INTO questions(task_id, text, multiselect, orderNum)
VALUES(1, 'Which is a reserved word in the Java programming language?', true, 0);
INSERT INTO answers(question_id, text, is_right, orderNum)
VALUES(4, 'method', true, 0);
INSERT INTO answers(question_id, text, is_right, orderNum)
VALUES(4, 'native', false, 1);
INSERT INTO answers(question_id, text, is_right, orderNum)
VALUES(4, 'subclasses', false, 2);
INSERT INTO answers(question_id, text, is_right, orderNum)
VALUES(4, 'reference', false, 3);
INSERT INTO answers(question_id, text, is_right, orderNum)
VALUES(4, 'array', false, 4);

INSERT INTO questions(task_id, text, multiselect, orderNum)
VALUES(1, 'Which is a valid keyword in java?', true, 0);
INSERT INTO answers(question_id, text, is_right, orderNum)
VALUES(5, 'interface', true, 0);
INSERT INTO answers(question_id, text, is_right, orderNum)
VALUES(5, 'string', false, 1);
INSERT INTO answers(question_id, text, is_right, orderNum)
VALUES(5, 'Float', false, 2);
INSERT INTO answers(question_id, text, is_right, orderNum)
VALUES(5, 'unsigned', false, 3);

ALTER TABLE surveys
ADD COLUMN `days_expired` INT NULL;