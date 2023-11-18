FROM openjdk:11

WORKDIR /app

COPY ./build/libs/*.jar app.jar

ENV TZ="America/Lima"

EXPOSE 8080

ENV SERVER_SERVLET_CONTEXT-PATH=""

ENTRYPOINT ["java", "-jar", "app.jar"]