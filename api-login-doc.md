
# Login



<!--- If we have only one group/collection, then no need for the "ungrouped" heading -->
- [Login](#login)
  - [Endpoints](#endpoints)
    - [1. Fazer Login](#1-fazer-login)



## Endpoints


--------



### 1. Fazer Login


Faz login na API e retorna o token JWT que pode ser usado para autenticar em rotas/end-points protegidos


***Endpoint:***

```bash
Method: POST
Type: RAW
URL: https://api-produtos.leooresende.tk/api/v1/login
```



***Body:***

```js        
{
    "username": "{{username}}",
    "password": "{{password}}"
}
```



---
[Voltar para o topo](#login)
