FROM openjdk:latest
EXPOSE 8086
ADD target/Hitman.jar Hitman.jar
ENTRYPOINT ["java","-jar","/Hitman.jar"]
