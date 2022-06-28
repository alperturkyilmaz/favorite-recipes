
## Prerequisites
* jdk 13
* maven (3.3.1 or later)

## Notes
* The Rest Application has been implemented by using Spring Boot framework, other opensource libraries 
used in the project are as follows:
  * Mapstruct
  * Lombok
  * QueryDsl
  * lorem (https://github.com/mdeanda/lorem) -An extremely useful Lorem Ipsum generator
####
* Data is being persisted to H2 in-memory database
  DB can be viewed from the following URL: http://localhost:8083/h2-console/
 > user=sa, password=password
####
* REST API has been documented and the swagger documentation can be accessed on this link: http://localhost:8083/swagger-ui/index.html
####
* Entity Diagram
>### Recipe --(1)-------(n)-- RecipeIngredient --(n)-------(1)-- Ingredient

## How to start

Step #1: switch to git repository directory

> cd favorite-recipes/

Step #2: run maven build/install

> mvn -f ./pom.xml clean install

Step #2: run the spring boot application

> java -jar ./target/favorite-recipes-1.0-SNAPSHOT.jar



