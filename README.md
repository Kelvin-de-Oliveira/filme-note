# API de Reviews de Movies

Esta é uma API REST em Java com Spring Boot que permite:
- Usuários se cadastrarem e autenticarem.
- Registrar, atualizar, visualizar e deletar resenhas de filmes.
- Curtir resenhas de outros usuários.
- Consultar filmes e resenhas associadas.

## Modelo de Domínio

```mermaid
classDiagram
    class User {
        -UUID id
        -String name
        -String email
        -String passwordHash
        -Role role
    }

    class Movie {
        -UUID id
        -String title
        -String director
        -Date releaseYear
        -String genre
        -String sinopse
    }

    class Review {
        -UUID id
        -String description
        -Integer score
        -LocalDateTime createAt
        -User author
        -Movie movie
    }
    
    class Comment{
        -UUID id
        -String content
        -LocalDateTime creaAt
        -User author
        -Review review
    }   

    class ReviewLike {
        -UUID id
        -LocalDateTime createAt
        -User user
        -Review review
    }
    
    class CommentLike {
        -UUID id
        -LocalDateTime createAt
        -User user
        -Comment comment
    }

    class Role {
        <<enumeration>>
        USER
        ADMIN
    }

    User "1" -- "0..*" Review : Write
    User "1" -- "0..*" ReviewLike : User
    User "1" -- "0..*" CommentLike: User
    User "1" -- "0..*" Comment: User
    Movie "1" -- "0..*" Review : Has
    Review "1" -- "0..*" ReviewLike : reviewLike
    Review "1" -- "0..*" Comment: Has 
    
    Role  --  User : Role
```
## Tecnologias

 - Java 21
 - Spring Boot
- H2 DataBase (dev)
- PostgreSQL (produção)
- Maven
