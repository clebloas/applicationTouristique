FROM openjdk:8-jre-alpine

ARG PACKAGED_ARTIFACT_FILENAME

ADD target/${PACKAGED_ARTIFACT_FILENAME} /app.jar

ENV JAVA_OPTS=""

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]

EXPOSE 8080
