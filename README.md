# Twitter

### Esta aplicação tem como objetivo coletar dados dos últimos 100 tweets para cada hashtag lista a baixo:
### *#openbanking, #remediation, #devops, #sre, #microservices, #observability, #oauth, #metrics, #logmonitoring, #opentracing*

## Preparando o ambiente
### Docker
Para rodar esta aplicação é necessário ter o **Docker** instalado

Se a ainda não tem o Docker instalado, por favor, visite o link a baixo:

[Baixar Docker](https://docs.docker.com/get-docker/)

### Baixando imagens Docker
Precisamos de duas imagens Docker para rodar nossa aplicação

Aplicação Java
```
docker pull andrepradodev/twitter:1.0
```

Banco de dados Postgres
```
docker pull postgres
```

### Subindo a aplicação

Agora que já temos as duas imagens, é necessário o arquivo *docker-compose.yml* que foi enviado por e-mail.

Estando na mesma pasta que o arquivo docker-compose.yml execute o comando:

```
docker compose up
```

Se tudo ocorrer bem, a aplicação estará no ar! :)

## Testando a aplicação

### Postman

Para rodar a aplicação podemos fazer uso do Postman.

### /feed

A primeira rota que precisamos acessar é a rota **/feed**.

*É necessário informar no Header o Authorization passando o bearer token*

*O bearer token também foi informado no e-mail*

![feed](https://user-images.githubusercontent.com/49701005/159703051-c6a23f14-27a6-4e6e-8b2a-b5fe04d1f3b8.PNG)

Esta rota é responsável por preencher o banco de dados com informações sobre as hashtags que foram estabelidas internamente no sistema.

### /top-five

A segunda rota que podemos chamar é a rota **/users/top-five**

*É necessário informar no Header o Authorization passando o bearer token*

*O bearer token também foi informado no e-mail*

![top-five](https://user-images.githubusercontent.com/49701005/159705337-42fe5aa8-5159-4d54-bc9d-6760cc95822b.PNG)

Esta rota retorna os 5 usuários com mais seguidores dentre os usuários coletados através das hashtags.

![top-five-response](https://user-images.githubusercontent.com/49701005/159816195-c22e8315-b04d-4ac0-a491-c0034bf48995.PNG)

*Se a rota* **/users/top-five** *for chamada antes da rota* **/feed** *a resposta será uma lista vazia.*

## Modelagem do banco de dados

![ModelagemBD](https://user-images.githubusercontent.com/49701005/159817896-6ffcd48e-d743-4be8-bc49-a44d0f7028fc.png)

## Modelo arquitetural
### Tabela Tweets usada como exemplo

![Modelo Arquitetural](https://user-images.githubusercontent.com/49701005/159818892-e7bafbbc-3e8b-4fba-bcd4-e52af4cb7782.png)


