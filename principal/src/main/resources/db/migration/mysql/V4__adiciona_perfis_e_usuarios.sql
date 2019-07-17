INSERT INTO perfil (id, nome) VALUES (1, 'ROLE_ADMIN');
INSERT INTO perfil (id, nome) VALUES (2, 'ROLE_USER');

INSERT INTO usuario (id, login, email, senha) VALUES (1, 'cabral', 'cabral@email.com', '$2a$10$.vfilze.9WySvybaw2Y/w.6BGDV0r806CMcX29uBCvB/ejWxUCbB2');
INSERT INTO usuario (id, login, email, senha) VALUES (2, 'colombo', 'colombo@email.com', '$2a$10$CM8hMQBU9GK6dblBDsDyR.l0OIwwL/Y6mAOf2cBLMgDmfvwr6azNy');

UPDATE pessoa SET usuario_id=1 WHERE id=1;
UPDATE pessoa SET usuario_id=2 WHERE id=2;

INSERT INTO usuario_perfil (id, usuario_id, perfil_id) values (1, 1, 1);
INSERT INTO usuario_perfil (id, usuario_id, perfil_id) values (2, 2, 2);