# API de Resenhas de Filmes

Esta é uma API REST em Java com Spring Boot que permite:
- Usuários se cadastrarem e autenticarem.
- Registrar, atualizar, visualizar e deletar resenhas de filmes.
- Curtir resenhas de outros usuários.
- Consultar filmes e resenhas associadas.

## Modelo de Domínio

```mermaid
classDiagram
    class Usuario {
        -UUID id
        -String nome
        -String email
        -String senhaHash
        -Role role
        -List~Resenha~ resenhas
        -List~Curtida~ curtidas
    }

    class Filme {
        -UUID id
        -String titulo
        -String diretor
        -Integer anoLancamento
        -String genero
        -List~Resenha~ resenhas
    }

    class Resenha {
        -UUID id
        -String conteudo
        -Integer nota
        -LocalDateTime criadoEm
        -Usuario autor
        -Filme filme
        -List~Curtida~ curtidas
    }

    class Curtida {
        -UUID id
        -LocalDateTime criadaEm
        -Usuario usuario
        -Resenha resenha
    }

    class Role {
        <<enumeration>>
        USER
        ADMIN
    }

    Usuario "1" -- "0..*" Resenha : escreve
    Usuario "1" -- "0..*" Curtida : usuario
    Filme "1" -- "0..*" Resenha : possui
    Resenha "1" -- "0..*" Curtida : curtida
    Role  --  Usuario : perfil
```
## Tecnologias

 - Java 21
 - Spring Boot
- H2 DataBase (dev)
- PostgreSQL (produção)
- Maven
