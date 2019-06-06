FROM openjdk:8u201-jdk-alpine3.9
ADD korki-1.0.jar .
CMD java -jar korki-1.0.jar --server.port=$PORT $JAVA_OPTS