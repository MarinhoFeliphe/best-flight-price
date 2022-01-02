FROM openjdk:11
EXPOSE 8080
ADD ./build/libs/best-flight-price-0.0.1-SNAPSHOT.jar best-flight-price.jar
ENTRYPOINT ["java", "-jar", "/best-flight-price.jar"]