#User Auth Service

https://www.marcobehler.com/guides/spring-security

https://www.javainuse.com/spring/boot-jwt

User auth will call User service using Feign Client.
Feign Client config requires the api gate way url with port details, which we can pass it through the properties file.

Also this service can generate token, also it can renew/refresh the token.
If token is already expired, then it will not renew the token.