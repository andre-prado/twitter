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
