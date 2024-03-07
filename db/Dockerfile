FROM postgres:latest

ENV POSTGRES_PASSWORD postgres

COPY db/init.sql /docker-entrypoint-initdb.d/

EXPOSE 5432