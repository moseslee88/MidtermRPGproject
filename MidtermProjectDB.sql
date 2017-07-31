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
-- Table `character`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `character` ;

CREATE TABLE IF NOT EXISTS `character` (
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
  PRIMARY KEY (`id`),
  INDEX `fk_character_player1_idx` (`player_id` ASC),
  CONSTRAINT `fk_character_player1`
    FOREIGN KEY (`player_id`)
    REFERENCES `player` (`id`)
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
-- Table `shop`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shop` ;

CREATE TABLE IF NOT EXISTS `shop` (
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventory` ;

CREATE TABLE IF NOT EXISTS `inventory` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `character_id` INT NULL,
  `shop_id` INT NULL,
  INDEX `fk_inventory_character1_idx` (`character_id` ASC),
  PRIMARY KEY (`id`),
  INDEX `fk_inventory_shop1_idx` (`shop_id` ASC),
  CONSTRAINT `fk_inventory_character1`
    FOREIGN KEY (`character_id`)
    REFERENCES `character` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_inventory_shop1`
    FOREIGN KEY (`shop_id`)
    REFERENCES `shop` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `item` ;

CREATE TABLE IF NOT EXISTS `item` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `item_level` INT NULL,
  `value` VARCHAR(45) NULL,
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
    REFERENCES `character` (`id`)
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
    REFERENCES `character` (`id`)
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
INSERT INTO `user_type` (`id`, `type`) VALUES (2, 'user');

COMMIT;


-- -----------------------------------------------------
-- Data for table `player`
-- -----------------------------------------------------
START TRANSACTION;
USE `MidtermProjectDB`;
INSERT INTO `player` (`id`, `user_type_id`, `email`, `password`, `display_name`) VALUES (1, 1, 'admin@admin.com', '74913F5CD5F61EC0BCFDB775414C2FB3D161B620', 'admin');
INSERT INTO `player` (`id`, `user_type_id`, `email`, `password`, `display_name`) VALUES (2, 2, 'user@user.com', '45F106EF4D5161E7AA38CF6C666607F25748B6CA', 'user');

COMMIT;


-- -----------------------------------------------------
-- Data for table `character`
-- -----------------------------------------------------
START TRANSACTION;
USE `MidtermProjectDB`;
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (1, 1, 'Banshee', 60, 100, 11, 19, 20, 10, 20, 10, 20, 1, 36, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (2, 1, 'Basilisk', 80, 100, 18, 12, 10, 20, 10, 20, 10, 1, 38, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (3, 1, 'Black Cat', 60, 100, 10, 20, 10, 20, 10, 10, 10, 1, 36, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (4, 1, 'Black Dog', 70, 100, 12, 18, 15, 10, 20, 10, 10, 1, 37, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (5, 1, 'Bogle', 60, 100, 10, 20, 10, 10, 10, 10, 20, 1, 36, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (6, 1, 'Boogyman', 100, 100, 15, 15, 15, 15, 15, 15, 15, 1, 40, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (7, 1, 'Centaur', 120, 100, 20, 10, 20, 10, 10, 10, 20, 1, 42, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (8, 1, 'Cerberus', 120, 100, 15, 15, 15, 10, 10, 15, 15, 1, 42, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (9, 1, 'Chimera', 110, 100, 10, 20, 10, 20, 20, 20, 15, 1, 41, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (10, 1, 'Cockatrice', 90, 100, 12, 15, 10, 10, 10, 20, 10, 1, 36, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (11, 1, 'Cyclops', 100, 100, 15, 15, 10, 10, 15, 20, 10, 1, 40, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (12, 1, 'Demon', 100, 100, 12, 18, 15, 20, 10, 15, 20, 1, 40, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (13, 1, 'Doppelganger', 100, 100, 15, 15, 10, 10, 10, 10, 10, 1, 40, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (14, 1, 'Dragon', 200, 100, 25, 15, 15, 25, 15, 15, 10, 1, 60, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (15, 1, 'Dwarf', 110, 100, 18, 12, 15, 10, 20, 10, 10, 1, 41, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (16, 1, 'Elf', 90, 100, 12, 18, 10, 10, 10, 20, 10, 1, 39, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (17, 1, 'Fairy', 50, 100, 10, 20, 15, 15, 15, 15, 10, 1, 35, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (18, 1, 'Gnome', 80, 100, 14, 16, 10, 10, 20, 15, 10, 1, 38, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (19, 1, 'Goblin', 80, 100, 14, 16, 10, 15, 10, 15, 15, 1, 38, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (20, 1, 'Golem', 160, 100, 25, 5, 20, 20, 20, 15, 10, 1, 46, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (21, 1, 'Gorgon', 110, 100, 13, 17, 10, 10, 15, 20, 15, 1, 41, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (22, 1, 'Griffin', 120, 100, 15, 15, 15, 10, 10, 10, 10, 1, 42, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (23, 1, 'Grim Reaper', 10, 100, 2, 28, 20, 20, 20, 20, 20, 1, 31, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (24, 1, 'Hurrikane', 130, 100, 20, 12, 20, 20, 20, 10, 20, 1, 45, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (25, 1, 'Hydra', 200, 100, 20, 10, 20, 15, 15, 15, 10, 1, 50, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (26, 1, 'Imp', 60, 100, 10, 20, 10, 20, 10, 15, 15, 1, 36, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (27, 1, 'Ladon', 80, 100, 18, 12, 10, 20, 10, 20, 10, 1, 38, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (28, 1, 'Leprechaun', 70, 100, 10, 20, 15, 10, 15, 15, 10, 1, 37, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (29, 1, 'Manticore', 110, 100, 18, 12, 15, 15, 10, 10, 10, 1, 41, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (30, 1, 'Mermaid', 80, 100, 14, 16, 15, 10, 20, 10, 10, 1, 38, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (31, 1, 'Minotaur', 120, 100, 17, 13, 15, 10, 10, 20, 10, 1, 42, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (32, 1, 'Nemean Lion', 120, 100, 16, 14, 10, 20, 10, 10, 10, 1, 42, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (34, 1, 'Nymph', 70, 100, 10, 20, 10, 10, 15, 20, 10, 1, 37, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (35, 1, 'Ogre', 130, 100, 21, 19, 20, 10, 20, 10, 15, 1, 53, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (36, 1, 'Orthos', 110, 100, 18, 12, 15, 10, 10, 15, 15, 1, 41, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (37, 1, 'Pegasus', 120, 100, 16, 14, 10, 20, 10, 10, 10, 1, 42, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (38, 1, 'Phoenix', 50, 100, 12, 18, 10, 30, 10, 15, 10, 1, 35, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (39, 1, 'Prisoner A', 130, 100, 12, 20, 20, 20, 10, 20, 20, 1, 45, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (40, 1, 'Satyr', 80, 100, 10, 20, 10, 10, 20, 15, 10, 1, 38, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (41, 1, 'Sea Monster', 150, 100, 25, 10, 15, 15, 25, 10, 15, 1, 50, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (42, 1, 'Shade', 90, 100, 15, 15, 15, 20, 10, 10, 10, 1, 39, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (43, 1, 'Siren', 90, 100, 13, 17, 10, 15, 25, 10, 10, 1, 39, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (44, 1, 'Sphinx', 110, 100, 17, 13, 15, 20, 10, 10, 10, 1, 41, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (45, 1, 'Sprite', 20, 100, 5, 25, 15, 20, 10, 20, 10, 1, 32, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (46, 1, 'Thunderbird', 50, 100, 12, 18, 10, 15, 10, 30, 10, 1, 35, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (47, 1, 'Typhon', 170, 100, 22, 10, 15, 20, 15, 15, 15, 1, 49, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (48, 1, 'Unicorn', 120, 100, 18, 12, 10, 10, 15, 15, 10, 1, 42, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (49, 1, 'Valkyrie', 110, 100, 16, 14, 15, 15, 15, 15, 15, 1, 41, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (50, 1, 'Vampire', 120, 100, 14, 16, 15, 10, 10, 10, 25, 1, 42, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (51, 1, 'Wendigo', 160, 100, 16, 16, 15, 10, 15, 20, 20, 1, 48, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (52, 1, 'Werewolf', 120, 100, 16, 14, 15, 10, 10, 10, 15, 1, 42, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (53, 1, 'Will-o\'-the-wisp', 20, 100, 5, 25, 15, 25, 10, 15, 10, 1, 32, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (54, 1, 'Wraith', 60, 100, 12, 18, 20, 10, 20, 10, 15, 1, 36, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (55, 1, 'Yeti', 110, 100, 20, 10, 20, 10, 10, 10, 20, 1, 41, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (56, 1, 'Zombie', 90, 100, 18, 12, 15, 10, 20, 15, 20, 1, 39, 0);
INSERT INTO `character` (`id`, `player_id`, `name`, `health`, `energy`, `power`, `critical`, `physical_r`, `fire_r`, `frost_r`, `lightning_r`, `blood_r`, `level`, `experience_given`, `experience_total`) VALUES (57, 2, 'User', 100, 100, 20, 20, 20, 20, 20, 20, 20, 1, 0, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `ability`
-- -----------------------------------------------------
START TRANSACTION;
USE `MidtermProjectDB`;
INSERT INTO `ability` (`id`, `name`, `element`, `power`, `energy_cost`) VALUES (1, 'Screech', 'frost', 3, 30);

COMMIT;


-- -----------------------------------------------------
-- Data for table `shop`
-- -----------------------------------------------------
START TRANSACTION;
USE `MidtermProjectDB`;
INSERT INTO `shop` (`id`) VALUES (1);
INSERT INTO `shop` (`id`) VALUES (2);
INSERT INTO `shop` (`id`) VALUES (3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `inventory`
-- -----------------------------------------------------
START TRANSACTION;
USE `MidtermProjectDB`;
INSERT INTO `inventory` (`id`, `character_id`, `shop_id`) VALUES (1, 57, 2);
INSERT INTO `inventory` (`id`, `character_id`, `shop_id`) VALUES (2, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `item`
-- -----------------------------------------------------
START TRANSACTION;
USE `MidtermProjectDB`;
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (1, 'Lesser Potion', 1, '10', 'edible', 'physical');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (2, 'Potion', 2, '30', 'edible', 'physical');
INSERT INTO `item` (`id`, `name`, `item_level`, `value`, `type`, `element`) VALUES (3, 'Greater Potion', 3, '50', 'edible', 'physical');

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
-- Data for table `character_ability`
-- -----------------------------------------------------
START TRANSACTION;
USE `MidtermProjectDB`;
INSERT INTO `character_ability` (`ability_id`, `character_id`, `id`) VALUES (1, 1, 1);

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

