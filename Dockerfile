FROM maven:3.8.7 AS build
COPY . .
RUN mvn -B clean package -DskipTests

FROM openjdk:17-jdk-slim
COPY --from=build ./target/*.jar password-gen.jar
EXPOSE 8776
ENTRYPOINT ["java","-jar","password-gen.jar"]