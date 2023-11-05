# ExchangeRates

A basic application that utilizes https://exchangerate.host/ for currency conversion rates as well as converting
specific amounts of currency

## Swagger UI

The swagger for the implemented endpoints can be found under /exchange-rates/swagger-ui/ once you start an instance of
the service.

## Technologies

* Java SE 17
* Spring 6 / Spring Boot 3.1.5 (Tomcat Server)
* Docker
* Built with Maven

## How to Run

Assuming you meet the above technological requirements:

You will pull the redis image using docker,
then run an instance of redis on the default 6379 port
and finally start two instances of the service, on different ports to utilize the shared redis cache!

```
docker pull redis
docker run --name g.giazitz -p 6379:6379 -d redis

mvn spring-boot:run -DSpring-boot.run.arguments=--server.port=8080
mvn spring-boot:run -DSpring-boot.run.arguments=--server.port=8090
```
