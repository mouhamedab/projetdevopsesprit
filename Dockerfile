FROM openjdk:11-jre-alpine
ADD target/achat.jar achat.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "achat.jar"]
