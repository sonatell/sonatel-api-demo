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
    client-id: a2b89020-b832-42ed-9dff-a36df4194ed7
    client-secret: 22c4f5d0-aa7b-4d76-aab4-f8188edda220
  retailer:
    msisdn: 7xxxxxxx
    pin-code: XXXX
  merchant:
    msisdn: 7xxxxxxx
    merchant-code: XXXXXX
    pin-code: XXXX

```

2 - Run the application

```shell
    ./mvnw
```

3 - Do my first API Call (cf. file under resources/tests.http)

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

    curl -X GET --location "http://localhost:8080/api/account/v1/cashins?customerMsisdn=77xxxxxxx"

    # perform Web Payment (with otp code)

        curl -X GET --location "http://localhost:8080/api/account/v1/payments/onestep?customerMsisdn=77xxxxxxx"

```

### Required

Java 11+
