
FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY target/paypal-1.0.0.jar paypal-1.0.0.jar
ENTRYPOINT ["java","-jar","/paypal-1.0.0.jar"]
