# Sonatel APIs demo

### 🚀 Getting started

1 - Configure my credentials under this config

> resources / application.yaml

```yaml

sonatel:
    security:
      client-id: <put_your_client_id>
      client-secret: <put_your_client_secret>
    my-numbers:
      - 77xxxxxxx

```

2 - Run the application

```shell
    ./mvnw
```

```

3 - Do my first API Call _(cf. file under resources/tests.http)_

```shell
    curl http://localhost:8080/api/account/v1/publicKeys
```

### Required

Java 11+
