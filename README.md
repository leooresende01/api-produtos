# API-Produtos


## API para que usuarios possam registrar, deletar, atualizar e ver produtos registrados
---
## Documentações:

### [API Produtos](https://github.com/leooresende01/api-produtos/blob/main/api-produtos.doc.md)

### [API Usuarios](https://github.com/leooresende01/api-produtos/blob/main/api-usuarios-doc.md)

### [API Login](https://github.com/leooresende01/api-produtos/blob/main/api-login-doc.md)
---
## Como usar:

#### 1. É necessario primeiramente se registrar como usuario da API seguindo a documentação abaixo
[Registrar na API](https://github.com/leooresende01/api-produtos/blob/main/api-usuarios-doc.md#6-registrar-usu%C3%A1rio)

#### 2. Logo após é necessario gerar um JSON Web Token (JWT) para que ele seja usado para fazer a autenticação a cada comunicação com a API seguindo a documentação abaixo
[Fazer login na API](https://github.com/leooresende01/api-produtos/blob/main/api-login-doc.md#1-fazer-login)

#### 3. Após isso, esse token pode ser enviado para o servidor a cada comunicação para possibilitar o acesso a end-points ou rotas protegidas da API, possibilitando fazer operações de CRUD em produtos da API
[Como usar autenticação Bearer](https://swagger.io/docs/specification/authentication/bearer-authentication/)

---
## Observações:
- É necessario substituir as expressões :produtoId e {{ username }} (Ou qualquer outra que use elas) por valores correspondentes
