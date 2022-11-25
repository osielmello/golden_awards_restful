# Getting Started

### Documentação
Projeto gerado com Gradle e Spring Boot e é compatível com Java 17.

Além da dependência do [H2](https://www.h2database.com/html/main.html), acrescentei o [Lombok](https://projectlombok.org/) para facilitar a criação das entities e models. 

### Guides
Para executar o projeto:

```js
 ./gradlew bootRun
```


Para executar os testes de integração:

```js
 ./gradlew clean test
```

## API

### GoldenAwardsApi

````dtd
GET /api/v1/golden-awards
````
### Movies

````dtd
POST "/api/v1/movies"

PUT "/api/v1/movies/{id}"

GET "/api/v1/movies/{id}"

DELETE "/api/v1/movies/{id}"
````
### Producers

````dtd
POST "/api/v1/producers"

PUT "/api/v1/producers/{id}"

GET "/api/v1/producers/{id}"

DELETE "/api/v1/producers/{id}"
````
### Studios

````dtd
POST "/api/v1/studios"

PUT "/api/v1/studios/{id}"

GET "/api/v1/studios/{id}"

DELETE "/api/v1/studios/{id}"
````

## Importação de outro arquivo
* Colocar o arquivo dentro da pasta '/resources/data'
* Alterar o valor da propriedade 'app.filename' no arquivo 'application.yml'
