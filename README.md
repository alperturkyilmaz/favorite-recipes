## Prerequisites
* jdk 13
* maven (3.3.1 or later)

## How to start

Step #1: switch to git repository directory

> cd favorite-recipes/

Step #2: run maven build/install

> mvn -f ./pom.xml clean install

Step #2: run the spring boot application

> java -jar ./target/favorite-recipes-1.0-SNAPSHOT.jar

Step #3: Access the swagger documentation on this link
`http://localhost:8083/swagger-ui/index.html`
