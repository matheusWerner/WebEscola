DROP DATABASE IF EXISTS web_escola;
CREATE DATABASE IF NOT EXISTS web_escola;
USE web_escola;

CREATE TABLE alunos(
id INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100),
codigo_matricula VARCHAR(7),
nota_1 FLOAT,
nota_2 FLOAT,
nota_3 FLOAT,
media FLOAT,
frequencia TINYINT
);

INSERT INTO alunos (nome, codigo_matricula, nota_1, nota_2, nota_3,
media, frequencia) VALUES
("Matheus", 1515, 7.0, 8.0, 9.0,8.0, 75); 
