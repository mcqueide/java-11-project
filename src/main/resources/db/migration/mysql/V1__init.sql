CREATE TABLE pessoa (
  id int not null AUTO_INCREMENT,
  nome varchar(100),
  data_nascimento date,
  PRIMARY KEY (id)
);

CREATE TABLE telefone (
  id int not null AUTO_INCREMENT,
  numero varchar(11),
  pessoa_id int not null,
  PRIMARY KEY (id),
  FOREIGN KEY (pessoa_id) REFERENCES pessoa(id)
);