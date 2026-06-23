# Music Streaming API

API REST de streaming de música desenvolvida com **Spring Boot** e **Domain-Driven Design (DDD)**, implementando operações de conta, transações com antifraude, favoritos, playlists e assinaturas.

---

## Sobre o Projeto

Este projeto foi desenvolvido como parte da disciplina de **Design Patterns e Domain-Driven Design (DDD) com Java**, aplicando os conceitos de:

- **Bounded Contexts** e **Context Mapping** (Customer-Supplier)
- **Aggregates** e **Domain Services**
- **Strategy Pattern** para regras antifraude extensíveis
- **Repository Pattern** com banco H2 em memória
- **Linguagem Ubíqua** nos nomes de classes e métodos
- Princípios **S.O.L.I.D.** e **Clean Code**

---
## Tecnologias

| Tecnologia | Versão |
|---|---|
| Java | 17 |
| Spring Boot | 3.3.4 |
| Spring Data JPA | - |
| H2 Database | In-memory |
| Maven | - |

---

## Como Rodar

### Pré-requisitos
- Java 17+
- Maven

### Passos

```bash
# Clone o repositório
git clone <url-do-repositorio>

# Entre na pasta
cd music-streaming

# Rode o projeto
./mvnw spring-boot:run
```

A API sobe em `http://localhost:8080`

O H2 Console fica disponível em `http://localhost:8080/h2-console`
- **JDBC URL:** `jdbc:h2:mem:musicstreamdb`
- **User:** `sa`
- **Password:** *(vazio)*

---

## Endpoints

### Conta (Account)

| Método | Endpoint | Operação |
|---|---|---|
| POST | `/api/accounts` | Criação de conta |
| POST | `/api/accounts/{id}/subscriptions` | Assinatura de plano |
| POST | `/api/accounts/{id}/favorites` | Favoritar música |
| POST | `/api/accounts/{id}/playlists` | Criar playlist |

### Transação

| Método | Endpoint | Operação |
|---|---|---|
| POST | `/api/transactions` | Autorização de transação |

---
