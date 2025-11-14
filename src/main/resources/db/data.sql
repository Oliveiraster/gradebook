
-- TURMAS
INSERT INTO turma (nome) VALUES ('Turma A');
INSERT INTO turma (nome) VALUES ('Turma B');
INSERT INTO turma (nome) VALUES ('Turma C');

-- DISCIPLINAS
INSERT INTO disciplina (nome) VALUES ('Matemática');
INSERT INTO disciplina (nome) VALUES ('Português');
INSERT INTO disciplina (nome) VALUES ('História');
INSERT INTO disciplina (nome) VALUES ('Ciências');

-- ALUNOS ( por turma; 1 aluno por turma ficará sem notas
INSERT INTO aluno (nome, turma_id) VALUES ('Ana Souza', 1);    -- id = 1
INSERT INTO aluno (nome, turma_id) VALUES ('Bruno Lima', 1);   -- id = 2
INSERT INTO aluno (nome, turma_id) VALUES ('Carla Menezes', 1); -- id = 3 (sem notas)

INSERT INTO aluno (nome, turma_id) VALUES ('Diego Martins', 2);  -- id = 4
INSERT INTO aluno (nome, turma_id) VALUES ('Eduarda Farias', 2); -- id = 5
INSERT INTO aluno (nome, turma_id) VALUES ('Fernanda Lopes', 2); -- id = 6 (sem notas)

INSERT INTO aluno (nome, turma_id) VALUES ('Felipe Rocha', 3);   -- id = 7
INSERT INTO aluno (nome, turma_id) VALUES ('Gabriela Nunes', 3); -- id = 8
INSERT INTO aluno (nome, turma_id) VALUES ('Henrique Prado', 3); -- id = 9 (sem notas)

-- AVALIAÇÕES (3 por disciplina)
-- Matemática (disciplina_id = 1) -> avaliacao ids 1,2,3
INSERT INTO avaliacao (titulo, peso, disciplina_id) VALUES ('Prova', 5, 1); -- id = 1
INSERT INTO avaliacao (titulo, peso, disciplina_id) VALUES ('Trabalho', 2, 1); -- id = 2
INSERT INTO avaliacao (titulo, peso, disciplina_id) VALUES ('Prova', 5, 1); -- id = 3

-- Português (disciplina_id = 2) -> avaliacao ids 4,5,6
INSERT INTO avaliacao (titulo, peso, disciplina_id) VALUES ('Prova', 5, 2);      -- id = 4
INSERT INTO avaliacao (titulo, peso, disciplina_id) VALUES ('Trabalho', 2, 2);   -- id = 5
INSERT INTO avaliacao (titulo, peso, disciplina_id) VALUES ('Atividade', 1, 2);  -- id = 6

-- História (disciplina_id = 3) -> avaliacao ids 7,8,9
INSERT INTO avaliacao (titulo, peso, disciplina_id) VALUES ('Prova', 5, 3); -- id = 7
INSERT INTO avaliacao (titulo, peso, disciplina_id) VALUES ('Trabalho', 2, 3);-- id = 8
INSERT INTO avaliacao (titulo, peso, disciplina_id) VALUES ('Prova', 5, 3); -- id = 9

-- Ciências (disciplina_id = 4) -> avaliacao ids 10,11,12
INSERT INTO avaliacao (titulo, peso, disciplina_id) VALUES ('Prova', 5, 4); -- id = 10
INSERT INTO avaliacao (titulo, peso, disciplina_id) VALUES ('Atividade', 1, 4);     -- id = 11
INSERT INTO avaliacao (titulo, peso, disciplina_id) VALUES ('Prova', 5, 4);   -- id = 12

-- ==========================================
-- Ana (1)
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (1,1,8.5);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (1,2,7.0);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (1,3,9.0);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (1,4,7.8);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (1,5,8.2);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (1,6,7.5);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (1,7,9.0);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (1,8,8.5);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (1,9,9.5);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (1,10,8.0);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (1,11,7.5);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (1,12,8.0);

-- Bruno (2)
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (2,1,6.0);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (2,2,8.0);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (2,3,7.5);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (2,4,6.5);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (2,5,7.0);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (2,6,6.8);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (2,7,7.0);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (2,8,8.0);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (2,9,7.5);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (2,10,6.8);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (2,11,7.2);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (2,12,6.9);

-- Carla (3) -> sem notas (intencional)

-- =========================================================
-- TURMA B: alunos 4,5 (6 sem notas) -> também recebem notas para 1..12
-- =========================================================

-- Diego (4)
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (4,1,7.5);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (4,2,7.0);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (4,3,6.5);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (4,4,7.5);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (4,5,8.0);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (4,6,7.0);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (4,7,6.5);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (4,8,7.2);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (4,9,6.8);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (4,10,7.0);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (4,11,6.5);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (4,12,7.3);

-- Eduarda (5)
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (5,1,9.0);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (5,2,8.5);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (5,3,9.5);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (5,4,9.0);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (5,5,8.5);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (5,6,9.0);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (5,7,8.0);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (5,8,8.5);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (5,9,8.8);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (5,10,8.2);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (5,11,7.9);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (5,12,8.0);

-- Fernanda (6) -> sem notas (intencional)

-- =========================================================
-- TURMA C: alunos 7,8 (9 sem notas) -> notas para 1..12
-- =========================================================

-- Felipe (7)
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (7,1,8.0);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (7,2,7.5);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (7,3,8.0);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (7,4,8.5);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (7,5,7.8);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (7,6,8.2);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (7,7,7.0);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (7,8,7.5);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (7,9,7.8);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (7,10,8.0);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (7,11,7.5);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (7,12,8.0);

-- Gabriela (8)
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (8,1,9.0);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (8,2,8.5);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (8,3,9.5);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (8,4,9.0);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (8,5,8.5);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (8,6,9.0);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (8,7,9.0);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (8,8,8.5);
-- Gabriela deixa prova 9 sem nota para testar '-' (intencional)
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (8,10,9.0);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (8,11,8.5);
INSERT INTO lancamento_nota (aluno_id, avaliacao_id, nota) VALUES (8,12,9.5);

-- Henrique (9) -> sem notas (intencional)
