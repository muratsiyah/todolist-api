FROM maven:3-alpine AS build-project
ADD . ./todolist-api
WORKDIR /todolist-api
RUN mvn clean install -DskipTests=true

FROM openjdk:8-jre-alpine
RUN apk add curl
EXPOSE 8080
WORKDIR /app
COPY --from=build-project ./todolist-api/target/todolist-api-*.jar ./todolist-api.jar
COPY ./init.sh /
ENTRYPOINT [ "/bin/sh", "/init.sh" ]