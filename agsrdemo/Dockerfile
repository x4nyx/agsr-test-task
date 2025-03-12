FROM maven:3.8.4-openjdk-17 as builder
WORKDIR /app
COPY . /app/.
RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip=true

FROM openjdk:17-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/*.jar
EXPOSE 8181
ENTRYPOINT ["java", "-jar", "/app/*.jar"]

#FROM openjdk:17-alpine
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#EXPOSE 8181
#ENTRYPOINT ["java", "-jar", "app.jar"]