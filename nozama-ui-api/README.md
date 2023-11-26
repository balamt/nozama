# Nozama UI API Server
----------------------------------------------------

This UI API is used as replacement of Actual Spring Boot Server.

### Why we need this server

- When Someone who does not know about Java/SpringBoot, They can use this node http server
- When People dont want to setup springboot to run the Nozama UI and Nozama Admin UI
- When we dont want to run docker container (Use less RAM)


### How to start the server

- Open terminal/command
- Navigate to the root folder of nozama-ui-api
- Run the below command (When server started you must see the link to open the api will be printed)

```
node /src/api.js
```

### How to stop the server

- Open the terminal/command where node is running
- Press Control + C key to send [SIGINT](https://www.gnu.org/software/libc/manual/html_node/Termination-Signals.html#index-SIGINT)



#### Some Reference

- [Tutorials Point Node Server](https://www.tutorialspoint.com/nodejs/nodejs_first_application.htm)
- [Express JS](https://www.tutorialspoint.com/expressjs/expressjs_restful_apis.htm)