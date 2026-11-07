# Campus Lost and Found

CSC 311 class demo, a Spring Boot web app.

**Author:** Hassan Khan

## Requirements

- Java 21 or newer
- Maven, or an IDE with Maven support such as IntelliJ IDEA

## Run the app

From the project root:

```
mvn spring-boot:run
```

Or run `LostfoundApplication` from your IDE.

Then open <http://localhost:8080> and register an account at `/register` to sign in.

Data is stored in an H2 database file under `data/`, which is created on first run. To browse it, open `/h2-console` with JDBC URL `jdbc:h2:file:./data/lostfound-db`, username `sa`, and an empty password.