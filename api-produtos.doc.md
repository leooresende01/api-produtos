
# Produtos



<!--- If we have only one group/collection, then no need for the "ungrouped" heading -->
- [Produtos](#produtos)
  - [Endpoints](#endpoints)
    - [1. Buscar Produto Pelo ID](#1-buscar-produto-pelo-id)
    - [2. Deletar Produto](#2-deletar-produto)
    - [3. Atualizar Produto](#3-atualizar-produto)
    - [4. Registrar Produto](#4-registrar-produto)
    - [5. Buscar Produtos](#5-buscar-produtos)



## Endpoints


--------



### 1. Buscar Produto Pelo ID


Busca um produto registrado pelo id, porém, é necessário ser um usuário e estar autenticado


***Endpoint:***

```bash
Method: GET
Type: none
URL: https://api-produtos.leooresende.tk/api/v1/produtos/:produtoId
Authorization-Type: Bearer
```



***URL variables:***

|    Key    |  Value |  Description  |
| --------- | ------ | ------------- |
| produtoId |        | ID do Produto |



### 2. Deletar Produto


Deleta um produto registrado, porém, é necessário ser um usuário e estar autenticado, e além disso o usuário autenticado deve ser o mesmo que registrou o produto ou ser um ADMIN


***Endpoint:***

```bash
Method: DELETE
Type: none
URL: https://api-produtos.leooresende.tk/api/v1/produtos/:produtoId
Authorization-Type: Bearer
```



***URL variables:***

|    Key    |  Value |  Description  |
| --------- | ------ | ------------- |
| produtoId |        | ID do Produto |



### 3. Atualizar Produto


Atualiza as informações de um produto registrado, porém, é necessário ser um usuário e estar autenticado, e além disso o usuário autenticado deve ser o mesmo que registrou o produto ou ser um ADMIN


***Endpoint:***

```bash
Method: PUT
Type: RAW
URL: https://api-produtos.leooresende.tk/api/v1/produtos/:produtoId
Authorization-Type: Bearer
```



***URL variables:***

|    Key    |  Value |  Description  |
| --------- | ------ | ------------- |
| produtoId |        | ID do Produto |



***Body:***

```js        
{
    "nome": "{{nomeDoProduto}}",
    "preco": {{preco}},
    "tipo": "TODOS"
}
```



### 4. Registrar Produto


Registra um novo produto, porém, é necessário ser um usuário e estar autenticado
Authorization-Type: Bearer


***Endpoint:***

```bash
Method: POST
Type: RAW
URL: https://api-produtos.leooresende.tk/api/v1/produtos
Authorization-Type: Bearer
```



***Body:***

```js        
{
    "nome": "{{nomeDoProduto}}",
    "preco": {{preco}},
    "tipo": "TODOS"
}
```



### 5. Buscar Produtos


Busca todos os produtos de todos os usuários da API, juntamente com o username de quem criou eles, porém, é necessário ser um usuário e estar autenticado para poder acessar esse recurso


***Endpoint:***

```bash
Method: GET
Type: none
URL: https://api-produtos.leooresende.tk/api/v1/produtos
Authorization-Type: Bearer
```



---
[Voltar para o topo](#produtos)
