#!/bin/bash
docker-compose up -d --force-recreate --no-deps --build $1
docker ps
