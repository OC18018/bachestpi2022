
pipeline {
    agent any 
    environment {
       
        registry = "josdevwho/baches"
    
        registryCredential = 'dockerhub_id'
        dockerImage = ''
    }
    
    stages {

            //stage('sonnar'){
            //steps('gates'){
            //    withMaven {
           // sh'mvn --version'
            //sh 'mvn clean verify sonar:sonar \
              //  -Dsonar.projectKey=bachesBackend \
               // -Dsonar.host.url=http://localhost:9000 \
                //-Dsonar.login=31956f9f70fe25016e4c2009fb65a30d2b6a2a29'
                //}
                
            //}
        //}
        stage('Test') {
            steps {
                withMaven(maven: 'maven') {
                sh 'mvn -f pom.xml clean test'
                }
            }
        }
    
    // Building Docker images
    stage('Building image') {
      steps{
        script {
                
                dockerImage = docker.build("--build-arg POSTGRES_USER=postgres",registry) 
        }
      }
    }
    
     // Uploading Docker images into Docker Hub
    stage('Upload Image') {
     steps{    
         script {
            docker.withRegistry( '', registryCredential ) {
            dockerImage.push()
            }
        }
      }
    }
    
     // Stopping Docker containers for cleaner Docker run
     stage('docker stop container') {
         steps {
            sh 'docker ps -f name=bachesContainer -q | xargs --no-run-if-empty docker container stop'
            sh 'docker container ls -a -fname=bachesContainer -q | xargs -r docker container rm'
         }
       }
    
    
    // Running Docker container, make sure port 8096 is opened in 
    stage('Docker Run') {
     steps{
         script {
            dockerImage.run("-p 9090:8080 --add-host db:192.168.1.33 --rm --name bachesContainer")
         }
      }
    }
  }
}
