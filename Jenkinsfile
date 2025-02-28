pipeline {
  agent { 
    dockerfile true
  }
   stages {
      stage('e2e-tests') {
         steps {
            sh 'mvn test'
         }
         post {
            always {
               allure includeProperties: 
               false, 
               jdk: '', 
               results: [[path: 'allure-results']]
            }
         }
      }
   }
}