FROM openjdk:11
ENV LOGGING_SPRING=error
ENV LOGGING_PACKAGE=error
ENV POSTGRES_HOST=localhost
ENV POSTGRES_PORT=5432
ENV POSTGRES_DB=enterprise-db
ENV POSTGRES_USERNAME=postgres
ENV POSTGRES_PASSWORD=postgres
ARG JAR_FILE=target/enterprise.jar
COPY ${JAR_FILE} .
ADD target/enterprise.jar .
EXPOSE 8000
CMD [ "java", "-jar",  "/enterprise.jar"]