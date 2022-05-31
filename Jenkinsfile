node {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
            }
        }
          stage('SonarQube analysis') {
            def scannerHome = tool 'sonarqube';
            withSonarQubeEnv('sonarqube') {
            sh "${scannerHome}/bin/sonar-scanner \
            -D sonar.login=admin \
            -D sonar.password=123456 \
            -D sonar.projectKey=bachesBackend \
            -D sonar.exclusions=vendor/**,resources/**,**/*.java \
            -D sonar.host.url=http://localhost:9000/" \
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
