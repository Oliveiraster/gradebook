# Boletim do Aluno – Backend (Spring Boot 3 / Java 17)

Este projeto implementa o **backend** do sistema de boletim escolar, responsável por gerenciar **alunos, turmas, disciplinas, avaliações e lançamentos de notas**.  
Ele fornece uma **API REST completa**, pronta para integração com um frontend (Angular ou outro).

---

## 📌 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA** (persistência)
- **H2 Database** (em memória)
- **Jakarta Validation** (validação de dados)
- **Swagger / OpenAPI** (documentação de API)
- **Lombok** (redução de boilerplate)
- **Maven** (gerenciamento de dependências)

---

## 🏗️ Estrutura do Backend

O backend foi organizado de forma **modular e escalável**:

src/main/java/com/example/gradebook/ </br>
├─ modules/ </br>
│ ├─ student/ # Alunos (model, repository, service, controller) </br>
│ ├─ class_school/ # Turmas (model, repository)  </br>
│ ├─ subject/ # Disciplinas e Avaliações (model, repository, DTOs) </br>
│ ├─ gradebook/ # Lançamento de notas e boletim (model, repository, service, controller) </br>
│ └─ reference_data/ # Endpoints de referência (turmas e disciplinas) </br>
├─ GradebookApplication.java </br>
└─ resources/ </br>
├─ application.properties </br>
└─ data.sql # Seed inicial </br>

### Camadas principais:

- **Controller:** expõe endpoints REST, documentados com Swagger.
- **Service:** contém regras de negócio (ex.: criação de alunos, cálculo de média ponderada).
- **Repository:** abstração do acesso a dados usando Spring Data JPA.
- **Model:** entidades JPA mapeadas para o banco H2.
- **DTO:** transferência de dados entre backend e frontend (limita exposição de entidades).

---

## 🗄️ Banco de Dados

- Banco **H2** em memória, inicializado com seed via `data.sql`.
- Dados de exemplo:
  - Turmas: Turma A, Turma B
  - Disciplinas: Matemática, História
  - Alunos: João, Maria, Pedro
  - Avaliações: Prova, Trabalho, Atividade
- Console H2 disponível em: `http://localhost:8080/h2-console`  
  - JDBC URL: `jdbc:h2:mem:testdb`  
  - Usuário: `sa`  
  - Senha: (vazio)

---

## 📝 Endpoints Disponíveis

### **Alunos**
| Método | URL | Descrição |
|--------|-----|-----------|
| POST   | `/alunos/turma/{turmaId}` | Cria um aluno em uma turma |
| PUT    | `/alunos/{id}`             | Atualiza nome de um aluno |
| DELETE | `/alunos/{id}`             | Remove aluno e todos os lançamentos de notas |

### **Turmas e Disciplinas (Reference Data)**
| Método | URL | Descrição |
|--------|-----|-----------|
| GET    | `/turmas`      | Lista todas as turmas |
| GET    | `/disciplinas` | Lista todas as disciplinas |

### **Boletim / Lançamento de Notas**
| Método | URL | Descrição |
|--------|-----|-----------|
| GET    | `/grades/turma/{turmaId}/disciplina/{disciplinaId}` | Lista boletim de uma turma e disciplina, com notas e média ponderada |
| POST   | `/grades`                  | Salva notas em lote (cria ou atualiza) |

---

## 🖥️ Executando o Backend

1. Clonar o repositório:

```bash
git clone [https://github.com/Oliveiraster/gradebook]
cd gradebook-backend
Executar via Maven:

bash
Copiar código
mvn spring-boot:run
Acessar a documentação Swagger:

bash
Copiar código
http://localhost:8080/api.html
🔢 Regras de Negócio
Notas devem estar entre 0 e 10 (validação no front e backend)

Cada aluno tem no máximo uma nota por avaliação

Média ponderada:

Copiar código
Média = (Σ nota × peso) ÷ (Σ pesos)
Se não houver notas, retorna null ou "-" no frontend

💾 Seed Inicial (data.sql)
sql
Copiar código
INSERT INTO turma (id, nome) VALUES (1, 'Turma A'), (2, 'Turma B');

INSERT INTO disciplina (id, nome) VALUES (1, 'Matemática'), (2, 'História');

INSERT INTO student (id, nome, turma_id) VALUES 
  (1, 'João', 1),
  (2, 'Maria', 1),
  (3, 'Pedro', 2);

INSERT INTO avaliacao (id, titulo, peso, disciplina_id) VALUES
  (1, 'Prova', 5, 1),
  (2, 'Trabalho', 3, 1),
  (3, 'Atividade', 2, 1);
🏗️ Decisões Técnicas
Arquitetura modular: facilita escalabilidade e manutenção.

DTOs: evitam exposição direta de entidades.

Service Layer: concentra regras de negócio (cálculo de média, validações, CRUD).

Swagger/OpenAPI: documenta todos os endpoints com parâmetros e respostas.

Tratamento de exceções: via ResponseStatusException para consistência de respostas.

Seed inicial: permite testar rapidamente todas as funcionalidades sem precisar criar dados manualmente.

💡 Melhorias Futuras
Adicionar autenticação e autorização (JWT, roles)

Testes unitários para serviços (ex.: cálculo de média ponderada)

Paginação e filtros nos endpoints de listagem

Logging centralizado e tratamento global de exceções

Versionamento da API (/api/v1/)

📨 Contato
Raphael Oliveira – oliveiraster.dev@gmail.com
