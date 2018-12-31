# Spring Boot, MySQL, JPA, Hibernate Rest API Tutorial

Build Restful GET and UPDATE APIs for a simple Number Increment application using Spring Boot, Mysql, JPA and Hibernate.

## Requirements

1. Java - 1.8.x

2. Maven - 3.x.x

3. Mysql - 5.x.x

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/callicoder/spring-boot-mysql-rest-api-tutorial.git
```

**2. Create Mysql database**
```bash
create database glaucus;
use glaucus;
create table Number(
id int primary key not null auto_increment ,
Value int
);
insert into Number  (id,value) values(0,0);

```

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

**4. Build and run the app using maven**

```bash
mvn package
java -jar target/demo-task-1.0.0.jar
```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:<spring.port>/api/id/{id}> ie. http://127.0.0.1:9001/api/id/1 .

## Explore Rest APIs

The app defines following CRUD APIs.

    GET /api/id/{id}
    
    PUT /api/id/{id}
    

You can test them using postman or any other rest client.


