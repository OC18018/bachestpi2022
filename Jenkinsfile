pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'pwd'
            }
        }
        stage('sonnar'){
            steps('gates'){
            sh 'mvn clean verify sonar:sonar \
                -Dsonar.projectKey=bachesBackend \
                -Dsonar.host.url=http://localhost:9000 \
                -Dsonar.login=admin \
                -Dsonar.password=123456'
                
            }
        }
        stage('Test') {
            steps {
                sh 'mvn -f pom.xml clean test'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
