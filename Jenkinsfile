pipeline {
    agent any{ 
        dockerfile{
            dir 'Build'
            additionalBuildArgs '--add-host db:192.168.1.47'
        } 
    }
    stages {
        stage('Build') {
            steps {
                sh 'pwd'
                sh 'ls'
            }
        }
        stage('sonnar'){
            steps('gates'){
                withMaven {
            sh'mvn --version'
            sh 'mvn clean verify sonar:sonar \
                -Dsonar.projectKey=bachesBackend \
                -Dsonar.host.url=http://localhost:9000 \
                -Dsonar.login=eaac028e2710cc8e26c5b490f1d04543146a3568'
                }
                
            }
        }
        stage('Test') {
            steps {
                withMaven(maven: 'maven') {
                sh 'mvn -f pom.xml clean test'
                }
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
