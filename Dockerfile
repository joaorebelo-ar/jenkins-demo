FROM openjdk:11-jdk
RUN mkdir -p /opt/jenkins-demo/

COPY build/libs/jenkins-demo.jar /opt/jenkins-demo/
COPY src/main/resources/demo.yml /opt/jenkins-demo/
WORKDIR /opt/jenkins-demo
EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java -jar jenkins-demo.jar server demo.yml" ]