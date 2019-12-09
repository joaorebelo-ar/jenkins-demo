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
                    sh 'docker stop jenkins-demo' 
                    sh 'apt-get update && apt-get install -y libltdl7 && rm -rf /var/lib/apt/lists/*'
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