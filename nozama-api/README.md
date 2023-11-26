# NOZAMA-API

It is Parent project, we can import into the IDE.

prerequisites:
JDK 17
Springboot Version 2.7.1
Environment Variables
NOZAMA_CERT => Pointing to the cert\CA folder within the nozama-cert project folder. 

Import the certificates (localhost.crt as localhost, nozama.in.crt as nozama-in, nozama.crt as nozama) into the cacerts file under your JDK (%JAVA_HOME%/lib/security)

[![Maven Build - Skip Tests ](https://github.com/balamt/nozama/actions/workflows/api-maven-build-skiptest.yml/badge.svg)](https://github.com/balamt/nozama/actions/workflows/api-maven-build-skiptest.yml)

# Ports Used in Nozama

Below are the list of Applications and PORTS occupied by the service and batch/schedulers and others services

| Service/Applications         | Port |
| ---------------------------- | ---- |
| Nozama_EurekaServer          | 8761 |
| Nozama_ApiGateway            | 8090 |
| Nozama_UserService           | 8081 |
| Nozama_ProductService        | 8082 |
| Nozama_ShipmentService       | 8083 |
| Nozama_OrderService          | 8084 |
| Nozama_DeliveryScheduler\*\* | 8085 |
| Nozama_WallerService         | 8086 |
| Nozama_OrderInfoUpdater\*\*  | 8087 |
| Nozama_CartService           | 8088 |
| Nozama_UserAuthService       | 8089 |
| Nozama_ConfigService         | 8888 |
| Nozama_AddressService        | 8092 |
| Nozama_CheckoutService       |      |
| Nozama_PaymentService        |      |
| mysql                        | 3306 |
| prometheus                   | 8763 |
| grafana                      | 8764 |

<sup>Note: Make sure you are having these ports available in your machine to run the services without docker</sup>

# Use of Sleuth Logging in Spring Boot

[Source Reference](https://www.baeldung.com/spring-cloud-sleuth-single-application)

When you run the service with Seluth dependency added to POM.xml and also added Loggers in the code

2019-05-10 22:36:38.254 INFO
[nozama-userservice,4e30f7340b3fb631,4e30f7340b3fb631,false] 12516
--- [nio-8080-exec-1] c.b.spring.session.UserController : Sample Test Message from User Service

[application name, traceId, spanId, export]

- **Application name**
  - This is the name we set in the properties file and can be used to aggregate logs from multiple instances of the same application.
- **TraceId**
  - This is an id that is assigned to a single request, job, or action. Something like each unique user initiated web request will have its own traceId.
- **SpanId**
  - Tracks a unit of work. Think of a request that consists of multiple steps. Each step could have its own spanId and be tracked individually. By default, any application flow will start with same TraceId and SpanId.
- **Export**
  - This property is a boolean that indicates whether or not this log was exported to an aggregator like Zipkin. Zipkin is beyond the scope of this article but plays an important role in analyzing logs created by Sleuth.

## Docker

[Docker AWS to build and deploy](https://keyholesoftware.com/2017/09/26/using-docker-aws-to-build-deploy-and-scale-your-application/)

## How to change master branch with different branch

Source URL [How to change master branch with different branch](https://stackoverflow.com/questions/2862590/how-to-replace-master-branch-in-git-entirely-from-another-branch)

```
This will be useful when your actual master branch is broken and you need to replace it with different branch

git branch -m master master-old # rename master on local
git push origin :master # delete master on remote
git push origin master-old # create master-old on remote
git checkout -b master seotweaks # create a new local master on top of seotweaks
git push origin master # create master on remote
```

---

## Spring CLI

- [Spring CLI Download Repo Link](https://repo.spring.io/ui/native/release/org/springframework/boot/spring-boot-cli/)

## Some References

- [Docker Compose - Getting Started](https://docs.docker.com/compose/gettingstarted/)
- [Docker Compose Sample](https://github.com/docker/awesome-compose)
