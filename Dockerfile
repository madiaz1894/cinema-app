FROM openjdk:8-jdk-alpine AS builder
RUN mkdir /code
COPY .mvn /code/.mvn
COPY mvnw /code
COPY mvnw.cmd /code
COPY pom.xml /code
COPY domain/ /code/domain
COPY infrastructure/ /code/infrastructure
WORKDIR /code
RUN chmod +x ./mvnw
RUN ./mvnw clean compile test package

FROM openjdk:8-jre-alpine
RUN mkdir /code
COPY --from=builder /code/infrastructure/target/cinema-app.jar /code
EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java -jar -Duser.timezone=$TIMEZONE /code/cinema-app.jar" ]
