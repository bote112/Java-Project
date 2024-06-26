create database racing;
use racing;

CREATE TABLE `car` (
                       `carID` int NOT NULL AUTO_INCREMENT,
                       `model` varchar(45) NOT NULL,
                       `color` varchar(45) NOT NULL,
                       `maxSpeed` varchar(45) NOT NULL,
                       `power` varchar(45) NOT NULL,
                       PRIMARY KEY (`carID`)
);

CREATE TABLE `driver` (
                          `driverID` int NOT NULL AUTO_INCREMENT,
                          `carID` int NOT NULL,
                          `name` varchar(45) NOT NULL,
                          `age` int NOT NULL,
                          `country` varchar(45) NOT NULL,
                          PRIMARY KEY (`driverID`),
                          KEY `carID_idx` (`carID`),
                          CONSTRAINT `carID` FOREIGN KEY (`carID`) REFERENCES `car` (`carID`)
);

CREATE TABLE `circuit` (
                           `circuitID` int NOT NULL AUTO_INCREMENT,
                           `name` varchar(45) NOT NULL,
                           `length` varchar(45) NOT NULL,
                           `location` varchar(45) NOT NULL,
                           `record` varchar(45) NOT NULL,
                           PRIMARY KEY (`circuitID`)
);


CREATE TABLE `circuit_asphalt` (
                                   `circuitID` int NOT NULL,
                                   `type` varchar(45) NOT NULL,
                                   `turns` int NOT NULL,
                                   `tire` varchar(45) NOT NULL,
                                   KEY `circuitID_asphalt_idx` (`circuitID`),
                                   CONSTRAINT `circuitID_asphalt` FOREIGN KEY (`circuitID`) REFERENCES `circuit` (`circuitID`) ON DELETE CASCADE ON UPDATE CASCADE
);




CREATE TABLE `circuit_dirt` (
                                `circuitID` int NOT NULL,
                                `terrain` varchar(45) NOT NULL,
                                `jumps` int NOT NULL,
                                `obstacles` int NOT NULL,
                                PRIMARY KEY (`circuitID`),
                                CONSTRAINT `circuitID_dirt` FOREIGN KEY (`circuitID`) REFERENCES `circuit` (`circuitID`) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE `team` (
                        `teamID` int NOT NULL AUTO_INCREMENT,
                        `driverID` int NOT NULL,
                        `name` varchar(45) NOT NULL,
                        `sponsor` varchar(45) NOT NULL,
                        `trophies` int NOT NULL,
                        `budget` int NOT NULL,
                        PRIMARY KEY (`teamID`),
                        KEY `driverID_idx` (`driverID`),
                        CONSTRAINT `driverID` FOREIGN KEY (`driverID`) REFERENCES `driver` (`driverID`)
);


CREATE TABLE `race` (
                        `raceID` int NOT NULL AUTO_INCREMENT,
                        `circuitID` int NOT NULL,
                        `laps` int NOT NULL,
                        `winnerTeamID` int NOT NULL,
                        PRIMARY KEY (`raceID`),
                        KEY `circuitID_idx` (`circuitID`),
                        KEY `winnerTeamID_idx` (`winnerTeamID`),
                        CONSTRAINT `circuitID` FOREIGN KEY (`circuitID`) REFERENCES `circuit` (`circuitID`),
                        CONSTRAINT `winnerTeamID` FOREIGN KEY (`winnerTeamID`) REFERENCES `team` (`teamID`)
);


CREATE TABLE `race_team` (
                             `raceID` int NOT NULL,
                             `teamID` int NOT NULL,
                             KEY `raceID_idx` (`raceID`),
                             KEY `teamID_idx` (`teamID`),
                             CONSTRAINT `raceID` FOREIGN KEY (`raceID`) REFERENCES `race` (`raceID`),
                             CONSTRAINT `teamID` FOREIGN KEY (`teamID`) REFERENCES `team` (`teamID`)
);


CREATE TABLE `championship` (
                                `championshipID` int NOT NULL AUTO_INCREMENT,
                                `name` varchar(45) NOT NULL,
                                `year` int NOT NULL,
                                `championTeamID` int NOT NULL,
                                PRIMARY KEY (`championshipID`),
                                KEY `championTeamID_idx` (`championTeamID`),
                                CONSTRAINT `championTeamID` FOREIGN KEY (`championTeamID`) REFERENCES `team` (`teamID`)
);


CREATE TABLE `championship_race` (
                                     `championshipID` int NOT NULL,
                                     `raceID` int NOT NULL,
                                     KEY `championshipID_idx` (`championshipID`),
                                     KEY `raceID_idx` (`raceID`),
                                     CONSTRAINT `championship_raceID` FOREIGN KEY (`raceID`) REFERENCES `race` (`raceID`),
                                     CONSTRAINT `championshipID` FOREIGN KEY (`championshipID`) REFERENCES `championship` (`championshipID`)
);




