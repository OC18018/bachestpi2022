pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
            }
        }
        stage('sonnar'){
            steps('gates'){
            sh 'mvn clean verify sonar:sonar \
                -Dsonar.projectKey=bachesBackend \
                -Dsonar.host.url=http://localhost:9000 \
                -Dsonar.login=27263594518e5e9d31055586c8702d2bb678dcdf'
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
