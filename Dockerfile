FROM openjdk:11-jre-slim

RUN mkdir -p /opt/jenkins-demo/

COPY build/libs/jenkins-demo.jar /opt/jenkins-demo/
WORKDIR /opt/jenkins-demo
ENTRYPOINT [ "sh", "-c", "java -jar jenkins-demo.jar" ]