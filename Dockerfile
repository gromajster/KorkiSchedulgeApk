FROM openjdk:8u201-jdk-alpine3.9
ADD korki-1.0.jar .
EXPOSE 8080
CMD java -jar korki-1.0.jar