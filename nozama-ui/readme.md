# Nozama UI

UI For Nozama - Build on React JS

### Pages

| Page      | Description                                  | Comments |
| --------- | -------------------------------------------- | -------- |
| /         | Root Home Page                               | Comments |
| /products | Products page with header, search and footer | Comments |
| /login    | Login Page                                   | Comments |
| /signup   | Signup Page                                  | Comments |
| /account  | User Account Page                            | Comments |

## Prerequisites

- npm
- nodejs
- react
- create-react-app
- vscode or any code editor
- browser (any browser)

## install npm, npx/node, react, create-react-app ... & other dependency used in this project

```bash
npm install -g npm
  
npm install -g npx

npm install -g node

npm install -g react

npm install -g create-react-app

npm install -g react-bootstrap

npm install -g bootstrap@5.1.3

npm install -g react-dom

npm install -g react-router-dom

npm install -g jquery

npm install -g popper.js

npm install -g react-script

npm install -g @fortawesome/react-fontawesome

npm install -g @fortawesome/free-solid-svg-icons

npm install -g @fortawesome/fontawesome-svg-core

```
npm i --save react create-react-app react-bootstrap bootstrap@5.1.3 react-dom react-router-dom jquery popper.js react-script @fortawesome/react-fontawesome @fortawesome/free-solid-svg-icons @fortawesome/fontawesome-svg-core

npm i --save-dev webpack

```

## Build the project

```bash
npm run build
```

## Run the Project

```bash
npm start
```

## Run the Project in Development Mode in 0.0.0.0

```bash 
npm run start --host 0.0.0.0
```

## References

- [React Bootstrap Official Document Reference](https://react-bootstrap.github.io/getting-started/introduction/)
- [Bootstrap 4 Reference](https://getbootstrap.com/docs/4.0/getting-started/introduction/)
- [Bootstrap 5.1 Reference](https://getbootstrap.com/docs/5.1/getting-started/introduction/)
- [Carousel Reference](https://www.tutsmake.com/react-bootstrap-carousel-slider-tutorial/)
- [Bootstrap Theme Base Builder](https://themestr.app/builder)
- [Fontawesome for React](https://fontawesome.com/v5/docs/web/use-with/react)
- [Some Best Practices - Part 1](https://ordinarycoders.com/blog/article/reactjs-best-practices)
- [Submit Form Data in React JS](https://www.techomoro.com/submit-a-form-data-to-rest-api-in-a-react-app/)
- [Adding Custome Environment variables](https://create-react-app.dev/docs/adding-custom-environment-variables)
- [Fetch get json data](https://stackoverflow.com/questions/54656223/fetch-function-return-promise-pending)
- [Axios Vs fetch()](https://blog.logrocket.com/axios-vs-fetch-best-http-requests/)
- [Configure NGINX as reverse proxy to hit ui and api](https://www.bogotobogo.com/DevOps/Docker/Docker-Compose-Nginx-Reverse-Proxy-Multiple-Containers.php)
- [Country Flag Component for React](https://www.npmjs.com/package/react-country-flag)
- [Tailwind Cheat Sheet](https://nerdcave.com/tailwind-cheat-sheet)
- [React JS Basic Guide](https://www.pragimtech.com/blog/reactjs/reactjs-introduction/)
- [Add HTTPS for react site for development](https://medium.com/swlh/how-to-make-react-js-use-https-in-development-4ead560eff10)


## Docker

Docker Pull Nginx and Node

```bash
docker pull nginx:stable-alpine
```

```bash
docker pull node:latest
```

## Dockerfile

```bash
docker build -t nozama-ui:base -f Nozama_UI_DockerFile .
```