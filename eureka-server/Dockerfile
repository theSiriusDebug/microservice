FROM openjdk:20
EXPOSE 8761
ADD /target/eureka-server-0.0.1-SNAPSHOT.jar netflix-eureka-naming-server-1.0.jar
ENTRYPOINT ["java","-jar","netflix-eureka-naming-server-1.0.jar"]