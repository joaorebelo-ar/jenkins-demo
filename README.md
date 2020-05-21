Create docker group and add current user to it

    sudo groupadd docker
    sudo usermod -aG docker $USER
    newgrp docker

Create jenkins user and add to docker group

    sudo adduser jenkins
    sudo usermod -aG docker jenkins

Build custom jenkins image
    
    docker build --rm -f "jenkins-Dockerfile" -t server-jenkinsdemo:latest "."

Create volume

    docker volume create jenkins-demo_jenkins_home

Start jenkins

    sudo docker run --rm -d --group-add $(stat -c '%g' /var/run/docker.sock) -v /var/run/docker.sock:/var/run/docker.sock -v jenkins-demo_jenkins_home:/var/jenkins_home --privileged -p 8090:8080 -p 50000:50000 server-jenkinsdemo:latest


Start keycloak instance


docker run -p 8080:8080 -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin quay.io/keycloak/keycloak:10.0.1 -n keycloak-server

Install jenkins plugins

- OpenId Connect Authentication

Create Keycloak realms, users and groups

 - Realm: CompanyA
 - UserGroup:
    - jenkins-admin
 - Client: jenkins-client: confidential 
