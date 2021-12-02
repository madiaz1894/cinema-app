# README #

### Assumptions ###
- This service is for a local cinema, so it won't need timezones support for show times.
- The frontend service can associate each movie name with an imdbId, so the main id for my implementation is the imdbId.

### Internal Endpoints ###
There is 2 internal endpoints. I used a simple basic auth to handle that. I could use an auth0 implementation but for simplicity I used an "admin/pass" authentication.

### Design ###
* This project was made using a clean architecture using two modules: Domain and Infraestructure. Domain for the business logic and Infrastructure for the Implementations, controllers, beans and routes. 
![Design](../cinema-app/infrastructure/src/main/resources/assets/design.png)

### How to run it ###

* This is a spring boot project that uses an env.yml (in a production environment) file for handling secure variables like the imdb API KEY. 
* For testing purposes I use the env.example. There is a plugin in intellij called envfile to run projects with env files so there is no need to set each environment variable manually.
* A database server must be created in order to make this work. I'm using flyway to handle the DB migrations:
  * For this purpose it could be used a Docker Container:
```docker
sudo docker run --name <Container Name> -e POSTGRES_USER=<Database user> -e POSTGRES_PASSWORD=<Database password> -p 54320:5432 -d postgres:9.6.14
sudo docker start <Container Name>
```
* Then, create the database and link the data in the env.example file. 
* If everything is set you could run this app as a Spring boot project. 


### Swagger Documentation ###
http://localhost:8080/api/cinema-app/swagger-ui
### Who do I talk to? ###
Marcos Diaz :) 