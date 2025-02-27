pipeline {
  agent any
   stages {
      stage('e2e-tests') {
         steps {
            sh 'sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin'
            sh 'docker-compose pull'
            sh 'docker-compose run --rm e2etests'
         }
      }
   }
}