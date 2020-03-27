pipeline {
    agent none
    stages {
        stage('Build') {
            agent {
                 docker { image 'openjdk:11-jdk' }
            }
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew assemble' 
                archiveArtifacts artifacts: '**/build/**', fingerprint: true  
            }
        }

        stage('Test'){
            agent {
                 docker { image 'openjdk:11-jdk' }
            }
             steps {
                sh './gradlew test' 
                archiveArtifacts artifacts: '**/build/libs/*.jar', fingerprint: true  
            }
        }

        stage('Deploy'){
            agent{
                label 'master'
            }
             steps {
                 script{
                   // sh 'apt-get update && apt-get install -y libltdl7 && rm -rf /var/lib/apt/lists/*'
                    sh 'docker stop jenkins-demo || true && docker rm jenkins-demo || true' 
                    docker.build('jenkins-demo').run('--name jenkins-demo -p 9999:8080')
                 }
            }
        }
    }
    post {
        cleanup {
            cleanWs()
        }
    }
}