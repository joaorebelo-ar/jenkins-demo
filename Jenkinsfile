pipeline {
    agent {
        docker {
            image 'gradle:jre11'
        }
    } 
    stages {
        stage('Stage 1') {
            steps {
                echo 'Hello world!' 
            }
        }
    }
}