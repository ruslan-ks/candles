FROM maven:3.9.4-eclipse-temurin-17-alpine as BUILD
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml -DskipTests=true clean package

FROM openjdk:22-slim
COPY --from=build /home/app/target/candles-0.0.1-SNAPSHOT.jar /usr/local/lib/candles.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/candles.jar"]