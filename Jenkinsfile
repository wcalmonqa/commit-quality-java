pipeline {
  agent { 
    docker { 
      image 'mcr.microsoft.com/playwright/java:v1.49.0-noble' 
    } 
  }
  stages {
    stage('e2e-tests') {
      steps {
      sh 'mvn -B install -D skipTests --no-transfer-progress'
      sh 'mvn test'
      }
    }
   }
}