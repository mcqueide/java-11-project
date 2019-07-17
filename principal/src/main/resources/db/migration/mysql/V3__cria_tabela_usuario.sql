CREATE TABLE usuario (
  id int not null AUTO_INCREMENT,
  login varchar(100),
  email varchar(100),
  senha varchar(255),
  PRIMARY KEY (id)
);

CREATE TABLE perfil (
  id int not null AUTO_INCREMENT,
  nome varchar(50),
  PRIMARY KEY (id)
);

CREATE TABLE usuario_perfil (
  id int not null AUTO_INCREMENT,
  usuario_id int not null,
  perfil_id int not null,
  PRIMARY KEY (id),
  FOREIGN KEY (usuario_id) REFERENCES usuario(id),
  FOREIGN KEY (perfil_id) REFERENCES perfil(id)
);

ALTER TABLE pessoa ADD usuario_id int;
ALTER TABLE pessoa ADD FOREIGN KEY (usuario_id) REFERENCES usuario(id);