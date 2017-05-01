FROM frolvlad/alpine-oraclejdk8:slim
ADD cart-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -jar app.jar" ]
