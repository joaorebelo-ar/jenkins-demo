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

 - Realm: CompanyA - change realm to SSL required = None
 - UserGroup:
    - jenkins-admin
 - Client: jenkins-client: confidential 
  - Configure client as Confidential
  - with root URL pointing to jenkins
  - Valid redirect URIs
  - Add group-membership mapper
 - Create two user groups: jenkins-admin & jenkins-read
 - Create two users: jenkins_admin (group: jenkins-admin), jenkins_reader(group: jenkins-read)

Jenkins Configuration to integrate with Keycloak

 - In Manage Jenkins -> Configure Global Security

   - Login with Openid Connect
     - ClientId
     - Client secret
   - Automatic configuration with well-known Realm URI: 

Save exit the screen switch to to manual configuration and advanced:
  - post logout redirect URL: http://jenkins-keycloak-talk.westeurope.cloudapp.azure.com:8090
  - Advanced set username field name: preferred_username
  - Full name field name: preferred_username
  - groups field name: group-membership

Configure in Authorization: Matrix-based security

 set group /jenkins-admin - Tick Overall Administer permission
 set group /jenkins-read - Tick Overall read permission

 Login with both users to show the different permission is effect.