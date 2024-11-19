# LuaCoffee

LuaCoffee é uma aplicação que permite criar, gerenciar e executar scripts Lua via API. O projeto fornece uma interface RESTful que suporta a execução de scripts, criação de APIs personalizadas e rotas para várias operações, como `GET`, `POST`, `PUT` e `DELETE`.

## Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Arquitetura](#arquitetura)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Instalação e Configuração](#instalação-e-configuração)
- [Uso](#uso)
  - [Execução de Scripts Lua](#execução-de-scripts-lua)
  - [Gerenciamento de APIs](#gerenciamento-de-apis)
- [Contribuição](#contribuição)
- [Licença](#licença)

## Sobre o Projeto

LuaCoffee é uma plataforma baseada em **LuaJ** que facilita a execução e o gerenciamento de scripts Lua em um ambiente Java. Ele permite que os desenvolvedores criem APIs dinâmicas que podem ser chamadas via HTTP, o que possibilita interações flexíveis com sistemas externos através de scripts personalizados.

### Principais Funcionalidades

- **Execução de scripts Lua**: Integração com o interpretador Lua para carregar e executar scripts personalizados.
- **Gerenciamento de APIs**: Permite criar, listar, atualizar e deletar APIs com base em rotas e métodos HTTP.
- **Suporte a múltiplos métodos HTTP**: Suporta rotas `GET`, `POST`, `PUT` e `DELETE`.
- **Armazenamento de Scripts**: Os scripts são armazenados em um banco de dados e podem ser recuperados e executados dinamicamente.

## Arquitetura

O projeto segue uma arquitetura **RESTful** com separação clara entre controladores, serviços, entidades e repositórios.

- **Controllers**: Responsáveis por gerenciar as requisições HTTP e encaminhá-las para os serviços.
- **Services**: Contêm a lógica de negócios e tratam as operações com scripts Lua e APIs.
- **Repositories**: Fazem a comunicação com o banco de dados, utilizando JPA para manipulação de entidades.
- **Entities**: Representam as entidades do banco de dados, como APIs e Scripts Lua.

## Tecnologias Utilizadas

- **Java 17**: Linguagem principal do projeto.
- **Spring Boot 3**: Framework para criação de APIs e gerenciamento de dependências.
- **LuaJ**: Biblioteca para execução de scripts Lua em Java.
- **PostgreSQL**: Banco de dados utilizado para armazenar APIs e scripts.
- **Maven**: Gerenciador de dependências e automação de builds.

## Instalação e Configuração

Siga os passos abaixo para configurar o projeto localmente.

### Pré-requisitos

- **Java 17** ou superior instalado
- **Maven** instalado
- **PostgreSQL** configurado

### Passos para Instalação

1. **Clone o repositório**:
    ```bash
    git clone https://github.com/username/LuaCoffee.git
    cd LuaCoffee
    ```

2. **Configure o banco de dados**:
    - Crie um banco de dados PostgreSQL.
    - Atualize o arquivo `application.properties` com suas credenciais de banco de dados:
      ```properties
      spring.datasource.url=jdbc:postgresql://localhost:5432/luacoffee_db
      spring.datasource.username=seu_usuario
      spring.datasource.password=sua_senha
      spring.jpa.hibernate.ddl-auto=update
      ```

3. **Instale as dependências e compile o projeto**:
    ```bash
    mvn clean install
    ```

4. **Execute a aplicação**:
    ```bash
    mvn spring-boot:run
    ```

A aplicação estará rodando em `http://localhost:8080`.

## Uso

### Execução de Scripts Lua

Para executar scripts Lua, use o seguinte endpoint:

#### **POST** `/luabridge/post/api`

- **Body**: 
  ```json
  {
    "route": "/meuScript",
    "params": {
      "nome": "Mundo"
    }
  }
