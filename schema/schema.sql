CREATE schema if not exists architechDBO;

CREATE TABLE  if not exists architechDBO.User(ID INT PRIMARY KEY auto_increment, userName varchar(255), password varchar(255));

CREATE unique spatial INDEX if not exists IDXNAME ON architechDBO.User(userName)