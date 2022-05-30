# Wish List API

Simple REST API using Java, Spring Boot & PostgreSQL database. 

### Running

```shell
mvn spring-boot:run
```
Point your browser to http://localhost:8080/ to use api.

### Available endpoints

- /list/add [POST] - To add new wish to DB
- /update/{id}/{update} [PUT] - To update existing wish based on ID
- /delete/{id} [DELETE] - To delete wish based on ID
- /get/{id} [GET] - To get wish based on ID
- /all [GET] - To get all wishes from DB
