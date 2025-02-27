pipeline {
  agent { dockerfile true  }
   stages {
      stage('e2e-tests') {
         steps {
            sh 'mvn test'
         }
      }
   }
}