
# API de Usuarios



- [API de Usuarios](#api-de-usuarios)
  - [Endpoints](#endpoints)
    - [1. Buscar Produto Do Usuario Pelo ID](#1-buscar-produto-do-usuario-pelo-id)
    - [2. Buscar Produtos de Um Usuario](#2-buscar-produtos-de-um-usuario)
    - [3. Buscar  Usuário Pelo Username](#3-buscar--usuário-pelo-username)
    - [4. Deletar Usuário](#4-deletar-usuário)
    - [5. Atualizar Usuário](#5-atualizar-usuário)
    - [6. Registrar Usuário](#6-registrar-usuário)
    - [7. Buscar Usuários](#7-buscar-usuários)



## Endpoints


--------



### 1. Buscar Produto Do Usuario Pelo ID


Busca um produto especifico de um usuário pelo id do produto


***Endpoint:***

```bash
Method: GET
Type: none
URL: https://api-produtos.leooresende.tk/api/v1/usuarios/:username/produtos/:produtoId
Authorization-Type: Bearer
```



***URL variables:***

| Key       | Value | Description     |
| --------- | ----- | --------------- |
| username  |       | Nome de usuario |
| produtoId |       | ID do produto   |



### 2. Buscar Produtos de Um Usuario


Busca todos os produtos registrado por um usuário especifico


***Endpoint:***

```bash
Method: GET
Type: none
URL: https://api-produtos.leooresende.tk/api/v1/usuarios/:username/produtos
Authorization-Type: Bearer
```



***URL variables:***

| Key      | Value | Description     |
| -------- | ----- | --------------- |
| username |       | Nome de usuario |



### 3. Buscar  Usuário Pelo Username



***Endpoint:***

```bash
Method: GET
Type: none
URL: https://api-produtos.leooresende.tk/api/v1/usuarios/:username
Authorization-Type: Bearer
```



***URL variables:***

| Key      | Value | Description     |
| -------- | ----- | --------------- |
| username |       | Nome de usuario |



### 4. Deletar Usuário


Deleta um usuário existente apenas se o usuário autenticado for o mesmo que está sendo deletado


***Endpoint:***

```bash
Method: DELETE
Type: none
URL: https://api-produtos.leooresende.tk/api/v1/usuarios/:username
Authorization-Type: Bearer
```



***URL variables:***

| Key      | Value | Description     |
| -------- | ----- | --------------- |
| username |       | Nome de usuario |



### 5. Atualizar Usuário


Atualizar alguma informação de um usuário registrado, porém, a mudança só será realizada caso o usuário que estiver sendo atualizado for o mesmo que está autenticado ou o usuário autenticado for um ADMIN


***Endpoint:***

```bash
Method: PUT
Type: RAW
URL: https://api-produtos.leooresende.tk/api/v1/usuarios/:username
Authorization-Type: Bearer
```



***URL variables:***

| Key      | Value | Description     |
| -------- | ----- | --------------- |
| username |       | Nome de usuario |



***Body:***

```js        
{
    "username": "{{username}}",
    "password": "{{password}}",
    "idade": {{idade}}
}
```



### 6. Registrar Usuário


Criar um novo usuário, que é necessário para acessar recursos protegidos da API


***Endpoint:***

```bash
Method: POST
Type: RAW
URL: https://api-produtos.leooresende.tk/api/v1/usuarios
Authorization-Type: none
```



***Body:***

```js        
{
    "username": "{{username}}",
    "password": "{{password}}",
    "idade": {{idade}}
}
```



### 7. Buscar Usuários


Buscar usuários registrados na API, é necessário ser um usuário e estar autenticado para poder acessar o recurso, será mostrado a lista de usuários com a lista de produtos registrados por eles


***Endpoint:***

```bash
Method: GET
Type: none
URL: https://api-produtos.leooresende.tk/api/v1/usuarios
Authorization-Type: Bearer
```



---
[Voltar para o topo](#usuarios)
