#User Auth Service

* [Springboot security reference](https://www.marcobehler.com/guides/spring-security)

* [Springboot JWT Reference](https://www.javainuse.com/spring/boot-jwt)
* [JWT Authorization and authentication in springboot](https://www.freecodecamp.org/news/how-to-setup-jwt-authorization-and-authentication-in-spring/)
* [Springboot Refresh/renew JWT Tokens](https://www.bezkoder.com/spring-boot-refresh-token-jwt/)
* [Springboot actuator - management and health expose](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html)

User auth will call User service using Feign Client.
Feign Client config requires the api gate way url with port details, which we can pass it through the properties file.

Also this service can generate token, also it can renew/refresh the token.
If token is already expired, then it will not renew the token.

