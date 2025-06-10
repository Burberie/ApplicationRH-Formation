DROP TABLE IF EXISTS dbuser;

CREATE TABLE dbuser (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  email VARCHAR(250) NOT NULL,
  username VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  role VARCHAR(250) NOT NULL
);

--INSERT INTO dbuser (email, username, password, role) VALUES
--('collaborator@email.com', 'collaborator', '$2a$10$SXhFTaySGM5fhps5jSQrEunNJKB3eH1IQdYcFvFN/wVfDqKw0dSl.', 'COLLABORATOR'),
--('manager@email.com', 'manager', '$2a$10$fwcd.ds.IIKVtoBr60RvYe8Qn.ejHKRG30HIRxIiNoeAnematc7Aq', 'MANAGER'),
--('trainer@email.com', 'trainer', '$2a$10$u27UUoZWV.aUAszRAMsb2OdIMnh/lzECREO2OwaWQ.svFlFqGnVQS', 'TRAINER'),
--('admin@email.com', 'admin', '$2a$10$Ntv83Euk.VKneIp5.ZtNwuuRJzyG68/5BeSIpK55BZwRzniz4wi26', 'ADMIN'),
--('system@email.com', 'system', '$2a$10$pTyq/7VEWpF6KozQYDXUKeVv.AT94VcJJYL2wlNCByfDOIJgPwCrO', 'SYSTEM');