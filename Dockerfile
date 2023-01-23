#FROM maven:3.8.3-openjdk-11-slim AS builder
#COPY pom.xml /app/
#COPY src /app/src
#RUN mvn -f /app/pom.xml clean package -DskipTests


#FROM openjdk:11-jre-slim
#COPY src /usr/src
#ADD target/SpringBootSecureApp-1.0.jar app.jar
#EXPOSE 8443
#ENTRYPOINT ["java", "-jar", "app.jar"]

#FROM eclipse-temurin:11-jdk-jammy
#
#WORKDIR /app
#
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#RUN ./mvnw dependency:resolve
#
#COPY src ./src
#
#CMD ["./mvnw", "spring-boot:run"]

FROM maven

WORKDIR /
COPY . .
RUN mvn clean install -DskipTests

CMD mvn spring-boot:run