ARG VERSION=0.0.1-SNAPSHOT
FROM openjdk:17
MAINTAINER "samwaithaka@gmail.com"

WORKDIR /app

COPY target/trandata-0.0.1-SNAPSHOT.jar ./

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "./trandata-0.0.1-SNAPSHOT.jar"]