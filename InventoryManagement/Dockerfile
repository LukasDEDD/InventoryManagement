FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn -B dependency:resolve

COPY src ./src

RUN mvn -B -DskipTests package


FROM eclipse-temurin:21-jre AS runtime

WORKDIR /app

ENV SPRING_PROFILES_ACTIVE=production
ENV DATA_PATH=/app/resources

RUN useradd -m appuser

COPY --from=build /app/target/*.jar app.jar
COPY src/main/resources ./resources

RUN chown -R appuser:appuser /app/resources

USER appuser

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]


