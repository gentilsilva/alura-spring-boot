ALTER TABLE pacientes ADD COLUMN ativo TINYINT;
UPDATE pacientes SET ativo = 1;
ALTER TABLE pacientes MODIFY ativo TINYINT NOT NULL;