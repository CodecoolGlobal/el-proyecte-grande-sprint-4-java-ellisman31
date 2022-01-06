FROM adoptopenjdk/openjdk16:alpine-jre
LABEL maintainer="special department"
ADD ./backend/forcedepartment/forcedepartment-0.0.1-SNAPSHOT.jar forcedepartment.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "forcedepartment.jar"]
