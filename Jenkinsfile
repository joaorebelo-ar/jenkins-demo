pipeline {
    agent {
        docker { image 'openjdk:11-jdk' }
    }
    stages {
        stage('Build') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew assemble' 
                archiveArtifacts artifacts: '**/build/*', fingerprint: true  
            }
        }

        stage('Test'){
             steps {
                sh './gradlew test' 
                archiveArtifacts artifacts: '**/build/libs/*.jar', fingerprint: true  
            }
        }
    }
}