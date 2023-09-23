#!bash
docker-compose up -d postgres
docker build . -t s4a:latest
docker-compose up -d s4a