# API de Avaliações de Filmes

Uma API RESTful construída com **Java 21 e Spring Boot** que permite aos
usuários avaliar filmes, comentar em avaliações e interagir com outros
usuários por meio de curtidas.

O projeto foi desenvolvido para praticar conceitos de desenvolvimento
backend como APIs REST, autenticação e autorização, modelagem de banco
de dados e arquitetura limpa utilizando o ecossistema Spring.

------------------------------------------------------------------------

## Funcionalidades

-   Registro e autenticação de usuários
-   Autenticação e autorização baseadas em JWT
-   Criar, atualizar, excluir e visualizar avaliações de filmes
-   Comentar em avaliações
-   Curtir avaliações e comentários
-   Navegar pelos filmes e suas avaliações associadas
-   Controle de acesso baseado em papéis (USER / ADMIN)

------------------------------------------------------------------------

## Modelo de Domínio

``` mermaid
classDiagram
    class User {
        -UUID id
        -String name
        -String email
        -String passwordHash
        -Role role
        -Userstatus status
    }

    class Movie {
        -UUID id
        -String title
        -String director
        -Integer releaseYear
        -Set~MovieGenre~ genres
        -String synopsis
    }

    class Review {
        -UUID id
        -String content
        -BigDecimal score
        -LocalDateTime createdAt
        -LocalDateTime updatedAt
        -User author
        -Movie movie
    }
    
    class Comment{
        -UUID id
        -String content
        -LocalDateTime createdAt
        -LocalDateTime updatedAt
        -User author
        -Review review
    }   

    class ReviewLike {
        -UUID id
        -LocalDateTime createdAt
        -User user
        -Review review
    }
    
    class CommentLike {
        -UUID id
        -LocalDateTime createdAt
        -User user
        -Comment comment
    }
```

------------------------------------------------------------------------

## Tecnologias Utilizadas

### Backend

-   **Java 21**
-   **Spring Boot**

### Persistência

-   **Spring Data JPA**
-   **PostgreSQL** (produção)
-   **H2 Database** (desenvolvimento)

### Segurança

-   **Spring Security**
-   **Autenticação JWT**

### Ferramenta de Build

-   **Maven**

### Documentação da API

-   **Swagger / OpenAPI**

------------------------------------------------------------------------

## Arquitetura

A aplicação segue uma arquitetura em camadas:

Controller → Service → Repository → Database

### Camadas

-   **Controller** -- expõe os endpoints REST
-   **Service** -- contém a lógica de negócio
-   **Repository** -- gerencia a persistência de dados usando Spring
    Data JPA
-   **Domain** -- entidades e modelos de domínio

------------------------------------------------------------------------

## Como Executar o Projeto

### Pré-requisitos

-   Java 21
-   Maven
-   PostgreSQL (opcional para ambiente de produção)

### Executando o Projeto

Clone o repositório:

```bash
      git clone https://github.com/yourusername/movie-reviews-api
      cd movie-reviews-api
```

Execute a aplicação:

```bash
    mvn spring-boot:run
```

A API será iniciada em:

http://localhost:8080

------------------------------------------------------------------------

## Documentação da API

A interface do Swagger estará disponível em:

http://localhost:8080/swagger-ui.html
