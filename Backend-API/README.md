# Docker compose

## Run the project

```bash
docker-compose up
```

## Stop the project

```bash
docker-compose down
```

### Run the database only

```bash
docker-compose up db # or docker-compose up -d db
```


### Run the backend only intelliJ

Use the following configuration in IntelliJ; setup configuration with following environment variables:

```bash
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/mydatabase
SPRING_DATASOURCE_USERNAME=myuser
SPRING_DATASOURCE_PASSWORD=mypassword
SPRING_JPA_HIBERNATE_DDL_AUTO=none
SPRING_JPA_SHOW_SQL=true
```

after that run the configuration. :rocket: