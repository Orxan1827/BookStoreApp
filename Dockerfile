FROM openjdk:17
ARG JAR_FILE=build/libs/bookStoreApp-0.0.1.jar
COPY ${JAR_FILE} /bookStoreApp-0.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/bookStoreApp-0.0.1.jar"]