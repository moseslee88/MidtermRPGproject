-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema MidtermProjectDB
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `MidtermProjectDB` ;

-- -----------------------------------------------------
-- Schema MidtermProjectDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `MidtermProjectDB` DEFAULT CHARACTER SET utf8 ;
USE `MidtermProjectDB` ;

-- -----------------------------------------------------
-- Table `user_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_type` ;

CREATE TABLE IF NOT EXISTS `user_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` ENUM('admin', 'player') NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `player`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `player` ;

CREATE TABLE IF NOT EXISTS `player` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_type_id` INT NOT NULL,
  `email` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `display_name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_player_user_type_idx` (`user_type_id` ASC),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `password_UNIQUE` (`password` ASC),
  UNIQUE INDEX `display_name_UNIQUE` (`display_name` ASC),
  CONSTRAINT `fk_player_user_type`
    FOREIGN KEY (`user_type_id`)
    REFERENCES `user_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventory` ;

CREATE TABLE IF NOT EXISTS `inventory` (
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `game_character`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `game_character` ;

CREATE TABLE IF NOT EXISTS `game_character` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `player_id` INT NULL,
  `name` VARCHAR(45) NOT NULL,
  `health` INT NULL,
  `energy` INT NULL,
  `power` INT NULL,
  `critical` INT NULL,
  `physical_r` INT NULL,
  `fire_r` INT NULL,
  `frost_r` INT NULL,
  `lightning_r` INT NULL,
  `blood_r` INT NULL,
  `level` INT NULL,
  `experience_given` INT NULL,
  `experience_total` INT NULL,
  `ability_points` INT NULL,
  `stat_points` INT NULL,
  `active` TINYINT(1) NOT NULL,
  `inventory_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_character_player1_idx` (`player_id` ASC),
  INDEX `fk_game_character_inventory1_idx` (`inventory_id` ASC),
  CONSTRAINT `fk_character_player1`
    FOREIGN KEY (`player_id`)
    REFERENCES `player` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_game_character_inventory1`
    FOREIGN KEY (`inventory_id`)
    REFERENCES `inventory` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ability`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ability` ;

CREATE TABLE IF NOT EXISTS `ability` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `element` ENUM('physical', 'fire', 'frost', 'lightning', 'blood', 'dark') NULL,
  `power` INT NULL,
  `energy_cost` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `item` ;

CREATE TABLE IF NOT EXISTS `item` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `item_level` INT NULL,
  `value` INT NULL,
  `type` ENUM('weapon', 'armor', 'edible', 'trash') NULL,
  `element` ENUM('physical', 'fire', 'frost', 'lightning', 'blood', 'dark') NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventory_item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventory_item` ;

CREATE TABLE IF NOT EXISTS `inventory_item` (
  `inventory_id` INT NOT NULL,
  `item_id` INT NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  INDEX `fk_inventory_item_item1_idx` (`item_id` ASC),
  CONSTRAINT `fk_inventory_item_inventory1`
    FOREIGN KEY (`inventory_id`)
    REFERENCES `inventory` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_inventory_item_item1`
    FOREIGN KEY (`item_id`)
    REFERENCES `item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shop`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shop` ;

CREATE TABLE IF NOT EXISTS `shop` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `inventory_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_shop_inventory1_idx` (`inventory_id` ASC),
  CONSTRAINT `fk_shop_inventory1`
    FOREIGN KEY (`inventory_id`)
    REFERENCES `inventory` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `character_ability`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `character_ability` ;

CREATE TABLE IF NOT EXISTS `character_ability` (
  `ability_id` INT NOT NULL,
  `character_id` INT NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  INDEX `fk_character_ability_character1_idx` (`character_id` ASC),
  CONSTRAINT `fk_character_ability_ability1`
    FOREIGN KEY (`ability_id`)
    REFERENCES `ability` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_character_ability_character1`
    FOREIGN KEY (`character_id`)
    REFERENCES `game_character` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quest`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `quest` ;

CREATE TABLE IF NOT EXISTS `quest` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(200) NULL,
  `intro` VARCHAR(200) NULL,
  `conclusion` VARCHAR(45) NULL,
  `level_min` INT NULL,
  `level_max` INT NULL,
  `completed` TINYINT(1) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `player_quest`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `player_quest` ;

CREATE TABLE IF NOT EXISTS `player_quest` (
  `player_id` INT NOT NULL,
  `quest_id` INT NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  INDEX `fk_player_quest_quest1_idx` (`quest_id` ASC),
  CONSTRAINT `fk_player_quest_player1`
    FOREIGN KEY (`player_id`)
    REFERENCES `player` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_player_quest_quest1`
    FOREIGN KEY (`quest_id`)
    REFERENCES `quest` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `friend`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `friend` ;

CREATE TABLE IF NOT EXISTS `friend` (
  `player_id1` INT NOT NULL,
  `player_id2` INT NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  INDEX `fk_player_has_player_player2_idx` (`player_id2` ASC),
  INDEX `fk_player_has_player_player1_idx` (`player_id1` ASC),
  CONSTRAINT `fk_player_has_player_player1`
    FOREIGN KEY (`player_id1`)
    REFERENCES `player` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_player_has_player_player2`
    FOREIGN KEY (`player_id2`)
    REFERENCES `player` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stage`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `stage` ;

CREATE TABLE IF NOT EXISTS `stage` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `level` INT NULL,
  `intro` VARCHAR(45) NULL,
  `conclusion` VARCHAR(45) NULL,
  `choice` VARCHAR(45) NULL,
  `character_id` INT NOT NULL,
  `completed` TINYINT(1) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_stage_character1_idx` (`character_id` ASC),
  CONSTRAINT `fk_stage_character1`
    FOREIGN KEY (`character_id`)
    REFERENCES `game_character` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quest_stage`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `quest_stage` ;

CREATE TABLE IF NOT EXISTS `quest_stage` (
  `quest_id` INT NOT NULL,
  `stage_id` INT NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  INDEX `fk_quest_has_stage_stage1_idx` (`stage_id` ASC),
  INDEX `fk_quest_has_stage_quest1_idx` (`quest_id` ASC),
  CONSTRAINT `fk_quest_has_stage_quest1`
    FOREIGN KEY (`quest_id`)
    REFERENCES `quest` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_quest_has_stage_stage1`
    FOREIGN KEY (`stage_id`)
    REFERENCES `stage` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
GRANT USAGE ON *.* TO dbadmin;
 DROP USER dbadmin;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'dbadmin' IDENTIFIED BY 'dbadmin';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'dbadmin';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `MidtermProjectDB`;
INSERT INTO `user_type` (`id`, `type`) VALUES (1, 'admin');
INSERT INTO `user_type` (`id`, `type`) VALUES (2, 'player');

COMMIT;


-- -----------------------------------------------------
-- Data for table `player`
-- -----------------------------------------------------
START TRANSACTION;
USE `MidtermProjectDB`;
INSERT INTO `player` (`id`, `user_type_id`, `email`, `password`, `display_name`) VALUES (1, 1, 'admin@admin.com', 'adminpass', 'admin');
INSERT INTO `player` (`id`, `user_type_id`, `email`, `password`, `display_name`) VALUES (2, 2, 'ghost@ghost.com', 'ghostpass', 'ghost');
INSERT INTO `player` (`id`, `user_type_id`, `email`, `password`, `display_name`) VALUES (3, 2, 'user@user.com', 'userpass', 'user');

COMMIT;


-- -----------------------------------------------------
-- Data for table `inventory`
-- -----------------------------------------------------
START TRANSACTION;
USE `MidtermProjectDB`;
INSERT INTO `inventory` (`id`) VALUES (1);
INSERT INTO `inventory` (`id`) VALUES (2);
INSERT INTO `inventory` (`id`) VALUES (3);
INSERT INTO `inventory` (`id`) VALUES (4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `game_character`
-- -----------------------------------------------------
START TRANSACTION;
USE `MidtermProjectDB`;
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (1, 1, 'Banshee', 60, 100, 11, 19, 20, 10, 20, 10, 20, 1, 36, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (2, 1, 'Basilisk', 80, 100, 18, 12, 10, 20, 10, 20, 10, 1, 38, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (3, 1, 'Black Cat', 60, 100, 10, 20, 10, 20, 10, 10, 10, 1, 36, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (4, 1, 'Black Dog', 70, 100, 12, 18, 15, 10, 20, 10, 10, 1, 37, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (5, 1, 'Bogle', 60, 100, 10, 20, 10, 10, 10, 10, 20, 1, 36, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (6, 1, 'Boogyman', 100, 100, 15, 15, 15, 15, 15, 15, 15, 1, 40, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (7, 1, 'Centaur', 120, 100, 20, 10, 20, 10, 10, 10, 20, 1, 42, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (8, 1, 'Cerberus', 120, 100, 15, 15, 15, 10, 10, 15, 15, 1, 42, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (9, 1, 'Chimera', 110, 100, 10, 20, 10, 20, 20, 20, 15, 1, 41, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (10, 1, 'Cockatrice', 90, 100, 12, 15, 10, 10, 10, 20, 10, 1, 36, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (11, 1, 'Cyclops', 100, 100, 15, 15, 10, 10, 15, 20, 10, 1, 40, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (12, 1, 'Demon', 100, 100, 12, 18, 15, 20, 10, 15, 20, 1, 40, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (13, 1, 'Doppelganger', 100, 100, 15, 15, 10, 10, 10, 10, 10, 1, 40, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (14, 1, 'Dragon', 200, 100, 25, 15, 15, 25, 15, 15, 10, 1, 60, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (15, 1, 'Dwarf', 110, 100, 18, 12, 15, 10, 20, 10, 10, 1, 41, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (16, 1, 'Elf', 90, 100, 12, 18, 10, 10, 10, 20, 10, 1, 39, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (17, 1, 'Fairy', 50, 100, 10, 20, 15, 15, 15, 15, 10, 1, 35, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (18, 1, 'Gnome', 80, 100, 14, 16, 10, 10, 20, 15, 10, 1, 38, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (19, 1, 'Goblin', 80, 100, 14, 16, 10, 15, 10, 15, 15, 1, 38, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (20, 1, 'Golem', 160, 100, 25, 5, 20, 20, 20, 15, 10, 1, 46, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (21, 1, 'Gorgon', 110, 100, 13, 17, 10, 10, 15, 20, 15, 1, 41, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (22, 1, 'Griffin', 120, 100, 15, 15, 15, 10, 10, 10, 10, 1, 42, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (23, 1, 'Grim Reaper', 10, 100, 2, 28, 20, 20, 20, 20, 20, 1, 31, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (24, 1, 'Hurrikane', 130, 100, 20, 12, 20, 20, 20, 10, 20, 1, 45, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (25, 1, 'Hydra', 200, 100, 20, 10, 20, 15, 15, 15, 10, 1, 50, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (26, 1, 'Imp', 60, 100, 10, 20, 10, 20, 10, 15, 15, 1, 36, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (27, 1, 'Ladon', 80, 100, 18, 12, 10, 20, 10, 20, 10, 1, 38, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (28, 1, 'Leprechaun', 70, 100, 10, 20, 15, 10, 15, 15, 10, 1, 37, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (29, 1, 'Manticore', 110, 100, 18, 12, 15, 15, 10, 10, 10, 1, 41, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (30, 1, 'Mermaid', 80, 100, 14, 16, 15, 10, 20, 10, 10, 1, 38, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (31, 1, 'Minotaur', 120, 100, 17, 13, 15, 10, 10, 20, 10, 1, 42, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (32, 1, 'Nemean Lion', 120, 100, 16, 14, 10, 20, 10, 10, 10, 1, 42, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (34, 1, 'Nymph', 70, 100, 10, 20, 10, 10, 15, 20, 10, 1, 37, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (35, 1, 'Ogre', 130, 100, 21, 19, 20, 10, 20, 10, 15, 1, 53, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (36, 1, 'Orthos', 110, 100, 18, 12, 15, 10, 10, 15, 15, 1, 41, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (37, 1, 'Pegasus', 120, 100, 16, 14, 10, 20, 10, 10, 10, 1, 42, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (38, 1, 'Phoenix', 50, 100, 12, 18, 10, 30, 10, 15, 10, 1, 35, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (39, 1, 'Prisoner A', 130, 100, 12, 20, 20, 20, 10, 20, 20, 1, 45, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (40, 1, 'Satyr', 80, 100, 10, 20, 10, 10, 20, 15, 10, 1, 38, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (41, 1, 'Sea Monster', 150, 100, 25, 10, 15, 15, 25, 10, 15, 1, 50, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (42, 1, 'Shade', 90, 100, 15, 15, 15, 20, 10, 10, 10, 1, 39, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (43, 1, 'Siren', 90, 100, 13, 17, 10, 15, 25, 10, 10, 1, 39, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (44, 1, 'Sphinx', 110, 100, 17, 13, 15, 20, 10, 10, 10, 1, 41, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (45, 1, 'Sprite', 20, 100, 5, 25, 15, 20, 10, 20, 10, 1, 32, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (46, 1, 'Thunderbird', 50, 100, 12, 18, 10, 15, 10, 30, 10, 1, 35, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (47, 1, 'Typhon', 170, 100, 22, 10, 15, 20, 15, 15, 15, 1, 49, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (48, 1, 'Unicorn', 120, 100, 18, 12, 10, 10, 15, 15, 10, 1, 42, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (49, 1, 'Valkyrie', 110, 100, 16, 14, 15, 15, 15, 15, 15, 1, 41, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (50, 1, 'Vampire', 120, 100, 14, 16, 15, 10, 10, 10, 25, 1, 42, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (51, 1, 'Wendigo', 160, 100, 16, 16, 15, 10, 15, 20, 20, 1, 48, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (52, 1, 'Werewolf', 120, 100, 16, 14, 15, 10, 10, 10, 15, 1, 42, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (53, 1, 'Will-o\'-the-wisp', 20, 100, 5, 25, 15, 25, 10, 15, 10, 1, 32, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (54, 1, 'Wraith', 60, 100, 12, 18, 20, 10, 20, 10, 15, 1, 36, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (55, 1, 'Yeti', 110, 100, 20, 10, 20, 10, 10, 10, 20, 1, 41, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (56, 1, 'Zombie', 90, 100, 18, 12, 15, 10, 20, 15, 20, 1, 39, 0, 0, 0, TRUE, 1);
INSERT INTO `game_character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`, `ability_points`, `stat_points`, `active`, `inventory_id`) VALUES (57, 2, 'User', 100, 100, 20, 20, 20, 20, 20, 20, 20, 1, 0, 1, 0, 2, TRUE, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `ability`
-- -----------------------------------------------------
START TRANSACTION;
USE `MidtermProjectDB`;
INSERT INTO `ability` (`id`, `name`, `element`, `power`, `energy_cost`) VALUES (1, 'Screech', 'frost', 3, 30);
INSERT INTO `ability` (`id`, `name`, `element`, `power`, `energy_cost`) VALUES (2, 'Snowball', 'frost', 1, 10);
INSERT INTO `ability` (`id`, `name`, `element`, `power`, `energy_cost`) VALUES (3, 'Ice spear', 'frost', 5, 50);
INSERT INTO `ability` (`id`, `name`, `element`, `power`, `energy_cost`) VALUES (4, 'Hail', 'frost', 7, 70);
INSERT INTO `ability` (`id`, `name`, `element`, `power`, `energy_cost`) VALUES (5, 'Blizzard', 'frost', 9, 90);
INSERT INTO `ability` (`id`, `name`, `element`, `power`, `energy_cost`) VALUES (6, 'Burn', 'fire', 1, 10);
INSERT INTO `ability` (`id`, `name`, `element`, `power`, `energy_cost`) VALUES (7, 'Fireball', 'fire', 3, 30);
INSERT INTO `ability` (`id`, `name`, `element`, `power`, `energy_cost`) VALUES (8, 'Crisp', 'fire', 5, 50);
INSERT INTO `ability` (`id`, `name`, `element`, `power`, `energy_cost`) VALUES (9, 'Torch', 'fire', 7, 70);
INSERT INTO `ability` (`id`, `name`, `element`, `power`, `energy_cost`) VALUES (10, 'Atomic', 'fire', 9, 90);
INSERT INTO `ability` (`id`, `name`, `element`, `power`, `energy_cost`) VALUES (11, 'Hit', 'physical', 1, 10);
INSERT INTO `ability` (`id`, `name`, `element`, `power`, `energy_cost`) VALUES (12, 'Wrap', 'physical', 3, 30);
INSERT INTO `ability` (`id`, `name`, `element`, `power`, `energy_cost`) VALUES (13, 'Headbutt', 'physical', 5, 50);
INSERT INTO `ability` (`id`, `name`, `element`, `power`, `energy_cost`) VALUES (14, 'Slam', 'physical', 7, 70);
INSERT INTO `ability` (`id`, `name`, `element`, `power`, `energy_cost`) VALUES (15, 'Crunch', 'physical', 9, 90);
INSERT INTO `ability` (`id`, `name`, `element`, `power`, `energy_cost`) VALUES (16, 'Shock', 'lightning', 1, 10);
INSERT INTO `ability` (`id`, `name`, `element`, `power`, `energy_cost`) VALUES (17, 'Zap', 'lightning', 3, 30);
INSERT INTO `ability` (`id`, `name`, `element`, `power`, `energy_cost`) VALUES (18, 'Fry', 'lightning', 5, 50);
INSERT INTO `ability` (`id`, `name`, `element`, `power`, `energy_cost`) VALUES (19, 'Thunderbolt', 'lightning', 7, 70);
INSERT INTO `ability` (`id`, `name`, `element`, `power`, `energy_cost`) VALUES (20, 'Electrocute', 'lightning', 9, 90);
INSERT INTO `ability` (`id`, `name`, `element`, `power`, `energy_cost`) VALUES (21, 'Stab', 'blood', 1, 10);
INSERT INTO `ability` (`id`, `name`, `element`, `power`, `energy_cost`) VALUES (22, 'Bite', 'blood', 3, 30);
INSERT INTO `ability` (`id`, `name`, `element`, `power`, `energy_cost`) VALUES (23, 'Gouge', 'blood', 5, 50);
INSERT INTO `ability` (`id`, `name`, `element`, `power`, `energy_cost`) VALUES (24, 'Flay', 'blood', 7, 70);
INSERT INTO `ability` (`id`, `name`, `element`, `power`, `energy_cost`) VALUES (25, 'Eat', 'blood', 9, 90);
INSERT INTO `ability` (`id`, `name`, `element`, `power`, `energy_cost`) VALUES (26, 'Scare', 'dark', 1, 10);
INSERT INTO `ability` (`id`, `name`, `element`, `power`, `energy_cost`) VALUES (27, 'Haunt', 'dark', 3, 30);
INSERT INTO `ability` (`id`, `name`, `element`, `power`, `energy_cost`) VALUES (28, 'Decimate', 'dark', 5, 50);
INSERT INTO `ability` (`id`, `name`, `element`, `power`, `energy_cost`) VALUES (29, 'Petrify', 'dark', 7, 70);
INSERT INTO `ability` (`id`, `name`, `element`, `power`, `energy_cost`) VALUES (30, 'Finish', 'dark', 9, 90);

COMMIT;


-- -----------------------------------------------------
-- Data for table `item`
-- -----------------------------------------------------
START TRANSACTION;
USE `MidtermProjectDB`;
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (1, 'Lesser Potion', 1, 5, 'edible', 'physical');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (2, 'Potion', 2, 8, 'edible', 'physical');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (3, 'Greater Potion', 3, 13, 'edible', 'physical');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (4, 'Lesser Draught', 1, 5, 'edible', 'physical');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (5, 'Draught', 2, 8, 'edible', 'physical');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (6, 'Greater Draught', 3, 13, 'edible', 'physical');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (7, 'Lesser Elixer', 1, 8, 'edible', 'physical');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (8, 'Elixer', 2, 13, 'edible', 'physical');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (9, 'Greater Elixer', 3, 21, 'edible', 'physical');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (10, 'Sitck', 1, 13, 'weapon', 'physical');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (11, 'Mace', 2, 21, 'weapon', 'physical');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (12, 'Hammer', 3, 34, 'weapon', 'physical');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (13, 'Shield', 1, 13, 'armor', 'physical');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (14, 'Mail', 2, 21, 'armor', 'physical');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (15, 'Armor', 3, 34, 'armor', 'physical');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (16, 'Earring of Burning', 1, 21, 'weapon', 'fire');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (17, 'Tiara of Fire', 2, 34, 'weapon', 'fire');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (18, 'Crown of Flame', 3, 55, 'weapon', 'fire');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (19, 'Fire Resistant Hat', 1, 21, 'armor', 'fire');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (20, 'Fire Retardant Hood', 2, 34, 'armor', 'fire');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (21, 'Fireproof Helm', 3, 55, 'armor', 'fire');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (22, 'Ring of Snow', 1, 21, 'weapon', 'frost');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (23, 'Band of Freezing', 2, 34, 'weapon', 'frost');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (24, 'Circle of Permafrost', 3, 55, 'weapon', 'frost');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (25, 'Warm Gloves', 1, 21, 'armor', 'frost');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (26, 'Frostbite Mittens', 2, 34, 'armor', 'frost');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (27, 'Freezeproof Gauntlets', 3, 55, 'armor', 'frost');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (28, 'Sandals of Shocking', 1, 21, 'weapon', 'lightning');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (29, 'Footgear of Electricity', 2, 34, 'weapon', 'lightning');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (30, 'Closed-toe Shoes of Zaps', 3, 55, 'weapon', 'lightning');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (31, 'Rubber Shoes', 1, 21, 'armor', 'lightning');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (32, 'Shock Resistant Boots', 2, 34, 'armor', 'lightning');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (33, 'Staticproof Socks', 3, 55, 'armor', 'lightning');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (34, 'Shiv of Bleeding', 1, 32, 'weapon', 'blood');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (35, 'Fang', 2, 45, 'weapon', 'blood');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (36, 'Cleaver', 3, 66, 'weapon', 'blood');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (37, 'Moisture Wicking Shirt', 1, 32, 'armor', 'blood');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (38, 'Stain Resistant Cloak', 2, 45, 'armor', 'blood');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (39, 'Bloody Chestplate', 3, 66, 'armor', 'blood');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (40, 'Knife of the Dark', 1, 32, 'weapon', 'dark');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (41, 'Hidden Dagger', 2, 45, 'weapon', 'dark');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (42, 'Blade of Obscurity', 3, 66, 'weapon', 'dark');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (43, 'Twilight Necklace', 1, 32, 'armor', 'dark');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (44, 'Dusk Collar', 2, 45, 'armor', 'dark');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (45, 'Amulet of …', 3, 66, 'armor', 'dark');

COMMIT;


-- -----------------------------------------------------
-- Data for table `inventory_item`
-- -----------------------------------------------------
START TRANSACTION;
USE `MidtermProjectDB`;
INSERT INTO `inventory_item` (`inventory_id`, `item_id`, `id`) VALUES (1, 1, 1);
INSERT INTO `inventory_item` (`inventory_id`, `item_id`, `id`) VALUES (2, 2, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `shop`
-- -----------------------------------------------------
START TRANSACTION;
USE `MidtermProjectDB`;
INSERT INTO `shop` (`id`, `inventory_id`) VALUES (1, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `character_ability`
-- -----------------------------------------------------
START TRANSACTION;
USE `MidtermProjectDB`;
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (1, 1, 1);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (12, 2, 2);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (26, 3, 3);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (23, 4, 4);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (9, 5, 5);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (21, 6, 6);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (15, 7, 7);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (14, 8, 8);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (3, 9, 9);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (1, 10, 10);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (30, 11, 11);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (10, 12, 12);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (11, 13, 13);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (9, 14, 14);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (3, 15, 15);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (19, 16, 16);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (13, 17, 17);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (1, 18, 18);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (7, 19, 19);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (15, 20, 20);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (21, 21, 21);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (14, 22, 22);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (24, 23, 23);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (1, 24, 24);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (11, 25, 25);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (1, 26, 26);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (11, 27, 27);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (11, 28, 28);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (11, 29, 29);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (1, 30, 30);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (11, 31, 31);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (6, 32, 32);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (21, 34, 33);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (11, 35, 34);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (16, 36, 35);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (11, 37, 36);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (6, 38, 37);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (26, 39, 38);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (11, 40, 39);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (22, 41, 40);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (26, 42, 41);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (1, 43, 42);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (6, 44, 43);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (1, 45, 44);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (16, 46, 45);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (11, 47, 46);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (11, 48, 47);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (21, 49, 48);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (21, 50, 49);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (11, 51, 50);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (22, 52, 51);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (16, 53, 52);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (26, 54, 53);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (11, 55, 54);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (21, 56, 55);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (26, 1, 56);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (22, 2, 57);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (24, 3, 58);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (15, 4, 59);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (4, 5, 60);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (27, 6, 61);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (8, 7, 62);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (13, 8, 63);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (9, 9, 64);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (11, 10, 65);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (11, 11, 66);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (7, 12, 67);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (13, 13, 68);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (12, 14, 69);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (14, 15, 70);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (11, 16, 71);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (9, 17, 72);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (21, 18, 73);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (22, 19, 74);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (10, 20, 75);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (29, 21, 76);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (19, 22, 77);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (28, 23, 78);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (3, 24, 79);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (14, 25, 80);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (6, 26, 81);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (8, 27, 82);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (23, 28, 83);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (12, 29, 84);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (3, 30, 85);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (23, 31, 86);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (22, 32, 87);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (27, 34, 88);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (14, 35, 89);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (18, 36, 90);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (13, 37, 91);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (8, 38, 92);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (22, 39, 93);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (22, 40, 94);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (18, 41, 95);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (27, 42, 96);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (17, 43, 97);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (13, 44, 98);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (27, 45, 99);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (17, 46, 100);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (3, 47, 101);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (13, 48, 102);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (18, 49, 103);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (23, 50, 104);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (23, 51, 105);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (12, 52, 106);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (2, 53, 107);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (26, 54, 108);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (3, 55, 109);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (12, 56, 110);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (6, 1, 111);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (29, 2, 112);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (11, 3, 113);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (14, 4, 114);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (19, 5, 115);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (14, 6, 116);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (23, 7, 117);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (22, 8, 118);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (20, 9, 119);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (26, 10, 120);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (23, 11, 121);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (28, 12, 122);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (15, 13, 123);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (25, 14, 124);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (21, 15, 125);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (21, 16, 126);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (29, 17, 127);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (23, 18, 128);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (24, 19, 129);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (14, 20, 130);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (23, 21, 131);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (15, 22, 132);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (29, 23, 133);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (20, 24, 134);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (25, 25, 135);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (23, 26, 136);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (29, 27, 137);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (23, 28, 138);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (10, 29, 139);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (29, 30, 140);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (24, 31, 141);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (25, 32, 142);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (28, 34, 143);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (15, 35, 144);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (17, 36, 145);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (19, 37, 146);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (10, 38, 147);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (22, 39, 148);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (14, 40, 149);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (24, 41, 150);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (29, 42, 151);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (4, 43, 152);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (9, 44, 153);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (18, 45, 154);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (20, 46, 155);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (5, 47, 156);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (24, 48, 157);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (28, 49, 158);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (25, 50, 159);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (30, 51, 160);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (25, 52, 161);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (5, 53, 162);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (3, 54, 163);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (5, 55, 164);
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (24, 56, 165);

COMMIT;


-- -----------------------------------------------------
-- Data for table `quest`
-- -----------------------------------------------------
START TRANSACTION;
USE `MidtermProjectDB`;
INSERT INTO `quest` (`id`, `name`, `description`, `intro`, `conclusion`, `level_min`, `level_max`, `completed`) VALUES (1, 'Beginning Again', 'You were trained to fight, so do that!', 'quest intro', 'quest conclusion', 1, 10, FALSE);

COMMIT;


-- -----------------------------------------------------
-- Data for table `player_quest`
-- -----------------------------------------------------
START TRANSACTION;
USE `MidtermProjectDB`;
INSERT INTO `player_quest` (`player_id`, `quest_id`, `id`) VALUES (1, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `friend`
-- -----------------------------------------------------
START TRANSACTION;
USE `MidtermProjectDB`;
INSERT INTO `friend` (`player_id1`, `player_id2`, `id`) VALUES (1, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `stage`
-- -----------------------------------------------------
START TRANSACTION;
USE `MidtermProjectDB`;
INSERT INTO `stage` (`id`, `name`, `level`, `intro`, `conclusion`, `choice`, `character_id`, `completed`) VALUES (1, 'The First Stage', 1, 'You entered the world under suspicious circumstances', 'They trained you to fight, and you did.', 'placeholder choice', 57, FALSE);

COMMIT;


-- -----------------------------------------------------
-- Data for table `quest_stage`
-- -----------------------------------------------------
START TRANSACTION;
USE `MidtermProjectDB`;
INSERT INTO `quest_stage` (`quest_id`, `stage_id`, `id`) VALUES (1, 1, 1);

COMMIT;

