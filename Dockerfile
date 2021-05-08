FROM crusaderrocks/graalvm-ce-java11-21.0.0.2-docker
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
