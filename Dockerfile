#Primeiro passo: build da aplicação
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install

#Segundo passo: Run da aplicação
FROM openjdk:17-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar ./api_restaurante.jar
EXPOSE 80
CMD ["java", "-jar", "api_restaurante.jar"]