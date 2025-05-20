CREATE DATABASE `back_formation`;
USE `paymybuddy`;

CREATE TABLE `user_accounts` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `role` varchar(100) NOT NULL,
   PRIMARY KEY (`ID`)
   );



-- Inserting data into User_Account table
INSERT INTO `user_accounts` (`email`, `password`, `lastname`, `firstname`, `role`)
VALUES
  ('user_Account1@example.com', 'password1', 'Doe', 'John', 'USER'),
  ('user_Account2@example.com', 'password2', 'Smith', 'Jane', 'USER');


  COMMIT;