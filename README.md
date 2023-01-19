# structured-repro Project

This project reproduce this [issue](https://github.com/cloudevents/sdk-java/issues/465).

## Repro

Start quarkus with:

```
mvn quarkus:dev
```

Navigate or GET http://localhost:8080/hello.

### Expected behavior

The rest client `src/main/java/org/repro/ReproRestClient.java` should respect the `@Consumes` annotation and produce a structured cloud event (eg. with `Content-Type: application/cloudevents+json`).

### Actual behavior

Navigate or GET http://localhost:8080/hello.

The rest client `src/main/java/org/repro/ReproRestClient.java` produce a binary encoded cloud event.

### Response inspection

To quickly inspect response create an endpoint on https://beeceptor.com/.

Defaults to https://beeceptor.com/console/quarkus-repro (can be overrided in `application.properties`).
