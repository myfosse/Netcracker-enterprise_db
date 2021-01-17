FROM openjdk:11
ENV LOGGING_SPRING=error
ENV LOGGING_PACKAGE=error
ENV POSTGRES_URL=jdbc:postgresql://localhost:8000/enterprise-db
ENV POSTGRES_USERNAME=postgres
ENV POSTGRES_PASSWORD=postgres
EXPOSE 8080
ADD target/enterprise-db-0.0.1-SNAPSHOT.jar enterprise.jar
ENTRYPOINT ["java","-jar","/enterprise.jar"]