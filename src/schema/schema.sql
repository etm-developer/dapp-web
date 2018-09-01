CREATE DATABASE dapp_center;

USE dapp_center;

CREATE TABLE member(
    memberId INT AUTO_INCREMENT PRIMARY KEY,
	account VARCHAR(32),
	PASSWORD VARCHAR(32),
	phone VARCHAR(32),
	email VARCHAR(32),
	userType TINYINT DEFAULT 0 COMMENT '',
	createTime DATETIME,
	lastLogin DATETIME
) ENGINE INNODB DEFAULT CHARSET 'utf8';


CREATE TABLE etm_node (
    nodeId INT AUTO_INCREMENT PRIMARY KEY,
    ownerId INT,
	ip  VARCHAR(32),
	PORT INT,
	masterPassword VARCHAR(32),
	os VARCHAR(32),	
	state TINYINT COMMENT "(0: offline, 1:online)",
	createTime DATETIME,
	updateTime DATETIME
)ENGINE INNODB DEFAULT CHARSET 'utf8';


CREATE TABLE dapp(
	dappId INT AUTO_INCREMENT PRIMARY KEY,
	creatorId INT,
	transactionId VARCHAR(64),
	NAME VARCHAR(64),
	category TINYINT,
	description VARCHAR(256),
	link VARCHAR(256),
	delegates VARCHAR(1024),
	secrets VARCHAR(1024),
	unlockDelegates INT,
	state TINYINT default 0 COMMENT "0:created, 1: registered, 2:installed, 3:launched, 4:stoped",
	createTime DATETIME,
	updateTime DATETIME
)ENGINE INNODB DEFAULT CHARSET 'utf8';


CREATE TABLE dapp_deploy(
	deployId INT AUTO_INCREMENT PRIMARY KEY,
	transactionId VARCHAR(64),
	nodeId INT,
	ownerId INT,
	state INT,
	createTime DATETIME
)ENGINE INNODB DEFAULT CHARSET 'utf8';
