pipeline {
  agent { 
    dockerfile true
  }
   stages {
      stage('e2e-tests') {
         steps {
            sh 'docker-compose --version || sudo apt-get install -y docker-compose'
            sh 'docker-compose pull'
            sh 'docker-compose run --rm e2etests'
         }
      }
   }
}