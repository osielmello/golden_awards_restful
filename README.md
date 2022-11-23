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

## Importação de outro arquivo
* Colocar o arquivo dentro da pasta '/resources/data'
* Alterar o valor da propriedade 'app.filename' no arquivo 'application.yml'
