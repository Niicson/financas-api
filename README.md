# 💰 Finanças API

API REST para gerenciamento de finanças pessoais, desenvolvida com Java e Spring Boot.

## 🚀 Tecnologias

- Java 17
- Spring Boot 3.5.11
- Spring Data JPA
- PostgreSQL
- Maven
- Lombok

## 📋 Funcionalidades

- Cadastro de transações financeiras (receitas e despesas)
- Listagem de todas as transações
- Busca de transação por ID
- Filtro de transações por tipo (RECEITA ou DESPESA)
- Atualização de transações
- Exclusão de transações

## 🔧 Como executar

### Pré-requisitos
- Java 17+
- PostgreSQL
- Maven

### Configuração do banco de dados
Crie um banco de dados PostgreSQL chamado `financas_db` e configure as credenciais no arquivo `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/financas_db
spring.datasource.username=postgres
spring.datasource.password=sua_senha
```

### Executando o projeto
```bash
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`

## 📌 Endpoints

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | /transacoes | Lista todas as transações |
| GET | /transacoes/{id} | Busca transação por ID |
| GET | /transacoes/tipo/{tipo} | Filtra por tipo (RECEITA/DESPESA) |
| POST | /transacoes | Cria nova transação |
| PUT | /transacoes/{id} | Atualiza transação |
| DELETE | /transacoes/{id} | Remove transação |

## 📝 Exemplo de requisição
```json
{
    "descricao": "Salário",
    "valor": 3500.00,
    "data": "2026-03-21",
    "tipo": "RECEITA",
    "categoria": "Salário"
}
```

## 👨‍💻 Autor

Nicson — [LinkedIn](https://www.linkedin.com/in/nicson-souza/) | [GitHub](https://github.com/Niicson)
