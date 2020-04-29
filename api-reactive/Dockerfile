FROM openjdk:13-alpine

EXPOSE 9000 8000

ENV APP_HOME /usr/src/app

COPY target/api-sync.jar $APP_HOME/app.jar

WORKDIR $APP_HOME

ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -jar app.jar