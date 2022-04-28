FROM java:latest

ENV USERNAME=${USERNAME}
ENV PASSWORD=${PASSWORD}

COPY target/backend-0.0.1-SNAPSHOT.jar backend-0.0.1-SNAPSHOT.jar

CMD java -jar ./backend-0.0.1-SNAPSHOT.jar