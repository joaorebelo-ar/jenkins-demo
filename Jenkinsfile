pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
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