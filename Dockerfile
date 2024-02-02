FROM openjdk:17
EXPOSE 9090
ADD build/libs/petshop-1.0.jar petshop.jar
ENTRYPOINT ["java", "-jar", "petshop.jar"]