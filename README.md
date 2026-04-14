Bora. Te deixo tudo pronto pra colar.

## README.md

````markdown
# API Usuário JWT

API REST desenvolvida com Java e Spring Boot para cadastro e autenticação de usuários com JWT.

## Funcionalidades

- Cadastro de usuário
- Listagem de usuários
- Busca de usuário por ID
- Atualização de usuário
- Remoção de usuário
- Login com geração de token JWT
- Senha criptografada com BCrypt

## Tecnologias utilizadas

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- MySQL
- JWT
- Maven
- Lombok

## Estrutura do projeto

```text
src/
pom.xml
mvnw
mvnw.cmd
````

## Como executar o projeto

### 1. Clonar o repositório

```bash
git clone https://github.com/JoaoPedroGalleMartins/api-usuario-jwtt.git
cd api-usuario-jwtt
```

### 2. Configurar o banco de dados

No arquivo `application.properties`, configure:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/seu_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

### 3. Rodar a aplicação

No Linux:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

## Endpoints principais

### Criar usuário

`POST /usuario`

Exemplo de body:

```json
{
  "name": "Joao",
  "email": "joao@email.com",
  "password": "123456"
}
```

### Login

`POST /usuario/login`

Exemplo de body:

```json
{
  "email": "joao@email.com",
  "password": "123456"
}
```

Exemplo de resposta:

```json
{
  "email": "joao@email.com",
  "token": "seu-jwt-aqui"
}
```

### Listar usuários

`GET /usuario`

### Buscar usuário por ID

`GET /usuario/{id}`

### Atualizar usuário

`PUT /usuario/{id}`

### Deletar usuário

`DELETE /usuario/{id}`

## Objetivo do projeto

Este projeto foi desenvolvido com foco em prática de:

* criação de APIs REST
* organização em camadas
* uso de DTOs
* autenticação com JWT
* criptografia de senha com Spring Security

## Melhorias futuras

* Testes unitários com JUnit e Mockito
* Tratamento global de exceções com ControllerAdvice
* Proteção de rotas com filtro JWT
* Documentação com Swagger

## Autor

João Pedro Galle Martins

* GitHub: [JoaoPedroGalleMartins](https://github.com/JoaoPedroGalleMartins)
* LinkedIn: [João Pedro Galle Martins](https://br.linkedin.com/in/jo%C3%A3o-pedro-galle-martins-19a933351)



