FROM frolvlad/alpine-oraclejdk8:slim
ADD  ./target/billing-service.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -jar app.jar" ]
