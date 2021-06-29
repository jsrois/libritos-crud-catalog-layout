### Postgres Database

Esta aplicación necesita conectarse a una base de datos postgres (ver `application.properties`).

Puedes instalar postgres en tu ordenador o usar docker: 

``` 
docker run --name postgres-container --rm -e POSTGRES_PASSWORD=mysecretpassword -p 5432:5432 -e POSTGRES_USER=default -d postgres
```