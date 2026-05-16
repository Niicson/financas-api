# 💰 Finanças API

API REST para gerenciamento de finanças pessoais com foco em SOLID e TDD.

## 🚀 Stack
- Java 17
- Spring Boot 3.5.11
- Spring Data JPA
- Spring Validation
- PostgreSQL
- Maven

## ✅ Melhorias aplicadas
- API desacoplada da entidade (`TransacaoDTO` e `TransacaoResponseDTO`)
- `TransacaoMapper` para conversões entre camadas
- tratamento de erro padronizado com `BusinessException` e `ErrorResponse`
- constructor injection em controller e service
- testes unitários (service/mapper) e integração web do controller

## 🔧 Setup
### Pré-requisitos
- Java 17+
- PostgreSQL
- Maven

### Configuração
Atualize `src/main/resources/application.properties` com suas credenciais do banco:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/financas_db
spring.datasource.username=postgres
spring.datasource.password=sua_senha
```

### Executar
```bash
mvn spring-boot:run
```

### Testes
```bash
mvn test
```

## 📌 Endpoints
| Método | Endpoint | Payload |
|---|---|---|
| GET | `/transacoes` | - |
| GET | `/transacoes/{id}` | - |
| GET | `/transacoes/tipo/{tipo}` | - |
| POST | `/transacoes` | `TransacaoDTO` |
| PUT | `/transacoes/{id}` | `TransacaoDTO` |
| DELETE | `/transacoes/{id}` | - |

### Exemplo de request (`TransacaoDTO`)
```json
{
  "descricao": "Salário",
  "valor": 3500.00,
  "data": "2026-03-21",
  "tipo": "RECEITA",
  "categoria": "Salário"
}
```

### Exemplo de erro (`ErrorResponse`)
```json
{
  "timestamp": "2026-05-16T03:00:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Erro de validação",
  "path": "/transacoes",
  "validationErrors": {
    "descricao": "Descrição é obrigatória"
  }
}
```
