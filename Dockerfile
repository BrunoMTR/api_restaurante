FROM openjdk:17-jdk-alpine
COPY target/*.jar restaurante.jar
ENTRYPOINT ["java", "-jar", "/restaurante.jar"]