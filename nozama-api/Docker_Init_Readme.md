# Docker Setup for Local Development

## Prerequisites
- Docker
- Docker Compose
- alpine:latest
- amazon corretto 23
---------------------------
## Follow the Below instructions carefully to set up the Nozama Docker in local
1. Install Docker
2. Pull alpine:latest image
   ```bash
    docker pull alpine:latest
    ```
3. Run the Docker Image Creation command as follows:
   ```bash
   docker build -t nozama-jdk:base -f DockerFile_jdk_alpine .
   ```
4. Run the MySQL Image Creation command as follows:
   ```bash
   docker build -t nozama_mysql:mysql -f DockerFile_mysql .
   ```
5. For **Zipkin**
   - Run the Zipkin Image Creation command as follows:
      - Download the Zipkin jar file from the following link: https://search.maven.org/remote_content?g=io.zipkin.java&a=zipkin-server&v=LATEST&c=exec
      - Place the downloaded jar file in the directory "./nozama-api/zipkin/"
      - Ensure to place only one version of the jar file, as the Dockerfile will copy any jar which has the name **"zipkin-server-*-exec.jar"** file from the directory.
      ```bash
      docker build -t nozama_zipkin:1 -f DockerFile_Zipkin .
      ```
6. For **Grafana**:
   - Create folder under "./nozama-api/grafana"
   - Inside the folder grafana, create a file called "grafana.ini"
   - Copy the below content to the "grafana.ini" file.
   ```ini
   [server]
   # Protocol (http, https, socket)
   protocol = https
   ```
   - Run the Grafana Image Creation command as follows:
   ```bash
   docker build -t nozama-grafana:base -f DockerFile_grafana .
   ```
7. For **Prometheus**:
   - Run the Prometheus Image Creation command as follows:
   ```bash
   docker build -t nozama-prometheus:base -f DockerFile_prometheus .
   ```
8. For **Kafka**:
   - **Yet to Document it.**
9. In case rebuild of the image is required, run the below command:
   ```bash
   docker-compose up -d --build
   ```