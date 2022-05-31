# Wish List API

Simple REST API using Java, Spring Boot & PostgreSQL/H2 databases - you are able to simply configure which one you want to use. 

### Database configuration

Edit [application.properties](https://github.com/MadarsD/wishlistAPI/blob/ce86e52e73b451563702af132144fda06ece56c6/src/main/resources/application.properties)

Set `wish-list.store-type=[H2Database or postgresql]` to use either H2 in-memory database or PostgreSQL database. 

If `wish-list.store-type=H2Database` then further configuration is not required. 

In case of `wish-list.store-type=postgresql` you will need to change `spring.datasource.url`, `spring.datasource.username` and `spring.datasource.password` for the actual database you are going to use.


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
