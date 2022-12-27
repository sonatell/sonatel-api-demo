<div align="center">
  <img src="assets/logo-api.png" alt="Sonatel API" width="320">
  <h1>Welcome to the Sonatel APIs demo repository</h1>

  <h6>Made with ❤️ &nbsp;by developers for developers</h6>
</div>
<br>

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


4 - Additional operations



```shell
    # encrypt given pin code

    curl -X POST --location "http://localhost:8080/api/account/v1/encrypt" \
        -H "Content-Type: application/json" \
        -d "{\"pin\" : \"1234\"}"

    # perform Cashin (faire un dépôt sur le compte d'un client)

    curl -X GET --location "http://localhost:8080/api/account/v1/cashins"
```


### Required

Java 11+
