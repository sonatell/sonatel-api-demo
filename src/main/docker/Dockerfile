FROM openjdk:11


ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JAVA_OPTS=""

# Add a jhipster user to run our application so that it doesn't need to run as root
RUN adduser --disabled-password --shell /bin/sh sonatell
WORKDIR /home/demo

ADD entrypoint.sh entrypoint.sh
RUN chmod 755 entrypoint.sh && chown sonatell:sonatell entrypoint.sh
USER sonatell

ENTRYPOINT ["./entrypoint.sh"]

EXPOSE 8080

ADD sonatel-api-demo-0.0.1-SNAPSHOT.jar sonatel-api-demo.jar
