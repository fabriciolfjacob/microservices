FROM openjdk:8u181-jdk

EXPOSE 8080

ADD ./target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]