<p align="center">
  <img src="banner.png" alt="Finanças API Banner" width="100%"/>
</p>

<h1 align="center">💰 Finanças API</h1>

<p align="center">
  API REST para gerenciamento de finanças pessoais, desenvolvida com Java e Spring Boot.
</p>

<p align="center">
  <a href="https://www.java.com"><img src="https://img.shields.io/badge/Java-17-blue?style=flat-square&logo=java" alt="Java 17"/></a>
  <a href="https://spring.io/projects/spring-boot"><img src="https://img.shields.io/badge/Spring%20Boot-3.5.11-brightgreen?style=flat-square&logo=spring" alt="Spring Boot 3.5.11"/></a>
  <a href="LICENSE"><img src="https://img.shields.io/badge/License-MIT-yellow?style=flat-square" alt="License MIT"/></a>
</p>

---

## 📑 Índice

- [🚀 Tecnologias](#-tecnologias)
- [📋 Funcionalidades](#-funcionalidades)
- [📥 Instalação](#-instalação)
- [🔧 Como executar](#-como-executar)
- [🌐 Variáveis de Ambiente](#-variáveis-de-ambiente)
- [📌 Endpoints](#-endpoints)
- [📡 Exemplos com cURL](#-exemplos-com-curl)
- [⚡ Códigos HTTP](#-códigos-http)
- [📁 Estrutura do Projeto](#-estrutura-do-projeto)
- [✔️ Validações](#️-validações)
- [⚠️ Tratamento de Erros](#️-tratamento-de-erros)
- [🗺️ Roadmap](#️-roadmap)
- [🤝 Contribuindo](#-contribuindo)
- [📜 Licença](#-licença)
- [👨‍💻 Autor](#-autor)

---

## 🚀 Tecnologias

- Java 17
- Spring Boot 3.5.11
- Spring Data JPA
- PostgreSQL
- Maven
- Lombok

---

## 📋 Funcionalidades

- Cadastro de transações financeiras (receitas e despesas)
- Listagem de todas as transações
- Busca de transação por ID
- Filtro de transações por tipo (RECEITA ou DESPESA)
- Atualização de transações
- Exclusão de transações

---

## 📥 Instalação

### Clone o repositório

```bash
git clone https://github.com/Niicson/financas-api.git
cd financas-api
```

---

## 🔧 Como executar

### Pré-requisitos

- Java 17+
- PostgreSQL
- Maven

### Configuração do banco de dados

Crie um banco de dados PostgreSQL chamado `financas_db`:

```sql
CREATE DATABASE financas_db;
```

Configure as credenciais no arquivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/financas_db
spring.datasource.username=${DB_USERNAME:postgres}
spring.datasource.password=${DB_PASSWORD:sua_senha}
```

### Executando o projeto

```bash
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`

---

## 🌐 Variáveis de Ambiente

É recomendado usar variáveis de ambiente em vez de senhas fixas no código:

**Exportando no terminal:**

```bash
export DB_USERNAME=postgres
export DB_PASSWORD=sua_senha
./mvnw spring-boot:run
```

**Usando arquivo `.env`:**

```env
DB_USERNAME=postgres
DB_PASSWORD=sua_senha
```

> ⚠️ Nunca commite senhas ou credenciais no repositório. Adicione `.env` ao `.gitignore`.

---

## 📌 Endpoints

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | /transacoes | Lista todas as transações |
| GET | /transacoes/{id} | Busca transação por ID |
| GET | /transacoes/tipo/{tipo} | Filtra por tipo (RECEITA/DESPESA) |
| POST | /transacoes | Cria nova transação |
| PUT | /transacoes/{id} | Atualiza transação |
| DELETE | /transacoes/{id} | Remove transação |

---

## 📡 Exemplos com cURL

### ➕ Criar transação (POST)

```bash
curl -X POST http://localhost:8080/transacoes \
  -H "Content-Type: application/json" \
  -d '{
    "descricao": "Salário",
    "valor": 3500.00,
    "data": "2026-03-21",
    "tipo": "RECEITA",
    "categoria": "Salário"
  }'
```

**Resposta esperada (201 Created):**

```json
{
  "id": 1,
  "descricao": "Salário",
  "valor": 3500.00,
  "data": "2026-03-21",
  "tipo": "RECEITA",
  "categoria": "Salário"
}
```

---

### 📋 Listar todas as transações (GET)

```bash
curl http://localhost:8080/transacoes
```

**Resposta esperada (200 OK):**

```json
[
  {
    "id": 1,
    "descricao": "Salário",
    "valor": 3500.00,
    "data": "2026-03-21",
    "tipo": "RECEITA",
    "categoria": "Salário"
  }
]
```

---

### 🔍 Buscar por ID (GET)

```bash
curl http://localhost:8080/transacoes/1
```

**Resposta esperada (200 OK):**

```json
{
  "id": 1,
  "descricao": "Salário",
  "valor": 3500.00,
  "data": "2026-03-21",
  "tipo": "RECEITA",
  "categoria": "Salário"
}
```

---

### ✏️ Atualizar transação (PUT)

```bash
curl -X PUT http://localhost:8080/transacoes/1 \
  -H "Content-Type: application/json" \
  -d '{
    "descricao": "Salário atualizado",
    "valor": 4000.00,
    "data": "2026-03-21",
    "tipo": "RECEITA",
    "categoria": "Salário"
  }'
```

**Resposta esperada (200 OK):**

```json
{
  "id": 1,
  "descricao": "Salário atualizado",
  "valor": 4000.00,
  "data": "2026-03-21",
  "tipo": "RECEITA",
  "categoria": "Salário"
}
```

---

### 🗑️ Remover transação (DELETE)

```bash
curl -X DELETE http://localhost:8080/transacoes/1
```

**Resposta esperada: `204 No Content`**

---

## ⚡ Códigos HTTP

| Código | Status | Descrição |
|--------|--------|-----------|
| 200 | OK | Requisição bem-sucedida |
| 201 | Created | Recurso criado com sucesso |
| 204 | No Content | Recurso removido com sucesso |
| 400 | Bad Request | Dados inválidos na requisição |
| 404 | Not Found | Recurso não encontrado |
| 500 | Internal Server Error | Erro interno do servidor |

---

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/nicson/financasapi/
│   │   ├── controller/         # Camada de entrada (endpoints REST)
│   │   ├── service/            # Regras de negócio
│   │   ├── repository/         # Acesso ao banco de dados (JPA)
│   │   ├── model/              # Entidades JPA
│   │   ├── exception/          # Tratamento global de erros
│   │   ├── enums/              # Enumerações (ex: TipoTransacao)
│   │   └── FinancasApiApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/nicson/financasapi/
        └── FinancasApiApplicationTests.java
```

---

## ✔️ Validações

| Campo | Regra | Mensagem de Erro |
|-------|-------|-----------------|
| `descricao` | Obrigatória, não pode ser vazia | "Descrição é obrigatória" |
| `valor` | Obrigatório, deve ser positivo (> 0) | "Valor deve ser maior que zero" |
| `data` | Obrigatória | "Data é obrigatória" |
| `tipo` | Deve ser `RECEITA` ou `DESPESA` | "Tipo inválido" |
| `categoria` | Opcional | — |

---

## ⚠️ Tratamento de Erros

O projeto utiliza um `GlobalExceptionHandler` centralizado para tratar exceções:

### `TransacaoNaoEncontradaException`
Lançada quando uma transação não é encontrada pelo ID. Retorna `404 Not Found`:

```json
{
  "erro": "Transação não encontrada com ID: 1",
  "status": "404"
}
```

### Erros de Validação (`MethodArgumentNotValidException`)
Lançados quando os dados da requisição são inválidos. Retorna `400 Bad Request`:

```json
{
  "erro": "Erro de validação",
  "descricao": "Descrição é obrigatória",
  "valor": "Valor deve ser maior que zero"
}
```

### Erros Gerais
Qualquer erro não tratado retorna `500 Internal Server Error`:

```json
{
  "erro": "Erro interno do servidor",
  "mensagem": "Detalhes do erro"
}
```

---

## 🗺️ Roadmap

- [x] CRUD completo de transações
- [x] Filtro por tipo (RECEITA/DESPESA)
- [x] Tratamento global de exceções
- [x] Validações com Bean Validation
- [ ] Filtro por período (data início / data fim)
- [ ] Relatórios e estatísticas financeiras
- [ ] Autenticação e autorização (Spring Security + JWT)
- [ ] Dashboard com resumo financeiro

---

## 🤝 Contribuindo

Sugestões e pull requests são bem-vindos!

1. Faça um **fork** do projeto
2. Crie uma branch para sua feature:
   ```bash
   git checkout -b feature/minha-feature
   ```
3. Commit suas mudanças:
   ```bash
   git commit -m 'feat: adiciona minha feature'
   ```
4. Push para a branch:
   ```bash
   git push origin feature/minha-feature
   ```
5. Abra um **Pull Request**

---

## 📜 Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

---

## 👨‍💻 Autor

Nicson — [LinkedIn](https://www.linkedin.com/in/nicson-souza/) | [GitHub](https://github.com/Niicson)
