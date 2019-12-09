pipeline {
    agent {
        docker { image 'openjdk:11-jdk' }
    }
    stages {
        stage('Build') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew assemble' 
                archiveArtifacts artifacts: '**/build/**', fingerprint: true  
            }
        }

        stage('Test'){
             steps {
                sh './gradlew test' 
                archiveArtifacts artifacts: '**/build/libs/*.jar', fingerprint: true  
            }
        }

        stage('Deploy'){
             steps {
                 script{
                    sh 'docker build -t jenkins-demo .'
                    //docker.build('jenkins-demo').run('-p 9999:8080')
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