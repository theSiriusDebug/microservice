FROM openjdk:20
EXPOSE 8088
ADD /target/api-gateway-0.0.1-SNAPSHOT.jar api-gateway-1.0.jar
ENTRYPOINT ["java","-jar","api-gateway-1.0.jar"]