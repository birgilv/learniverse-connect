# Learniverse-Connect Backend

This is the backend for the application. It is made using [Spring Boot](https://spring.io/projects/spring-boot). It contains all the code for the API endpoints and interaction with the database. We use MySQL for the database.

## Before running backend

The application is written in Java 17. That means in order to compile and run, you need to have Java 17 (or later) JDK installed. You can download it from [Oracle's Java download page](https://www.oracle.com/java/technologies/downloads/). 

Our application also requires Maven. You can download and install Maven from the [Apache Maven website](https://maven.apache.org/download.cgi).


Look at the `application.properties.example` (resides within resources) on how to fill out the `application.properties`. 

## Running the backend:
1. Enter the git repository folder
2. Change directory to backend. This can be done using  `cd backend`
3. Compile the backend folder:  `mvn package `
4. Execute the jar file:  `java -jar target/*.jar` or use  `mvn spring-boot:mvn`