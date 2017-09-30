# Spring Demo

This is a simple project that shows how to use Spring MVC + Spring Security + Hibernate.

Please use the provided SQL schema and sample data.

## Requirements

- Java 1.8 or newer
- MySQL 5 or newer

## Building

The project requires Java 1.8 and Maven 3.3.9.

```sh
$ mvn clean install
```

## Deployment

Don't forget to update database connection credentials in application.properties.

You can use a servlet container to run this app. Or, you can run

```sh
$ java -jar target/spring-demo-0.0.1-SNAPSHOT.jar
```

from the root dir. Then navigate to localhost:8080.

## License

MIT
