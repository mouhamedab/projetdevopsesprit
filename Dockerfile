FROM openjdk:8-jre-alpine
ADD target/springprojet.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "achat.jar"]
