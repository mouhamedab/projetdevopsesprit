pipeline {
    agent any
     
    environment {
        PATH = "$PATH:/opt/apache-maven-3.0.5/bin"
    }
    stages{
        stage('Build mvn'){
            steps{
               
                checkout([$class: 'GitSCM', branches: [[name: '*/ahmedhaddad']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/mouhamedab/projetdevopsesprit.git']]])
                sh 'mvn clean install'
            }
         }  
        
         stage('Build'){
            steps{
                sh 'mvn clean package'
            }
         }
        
        stage(MVN SONARQUBE) {
            steps{
                sh 'mvn sonar: sonar -Dsonar.login=admin -Dsonar.password=sonar'
            }
         }
          
      
        stage('Build docker image'){
            steps{
                script{
                    sh 'docker build -t ahmedhaddad29/achat-1.0-sk .'
                }
            }
        }
        stage('Push image to Hub'){
            steps{
                script{
                withCredentials([string(credentialsId: 'dockerpass', variable: 'dockerpass')]) {
                   sh 'docker login -u ahmedhaddad29 -p ${dockerpass}'
                    }
                   sh 'docker push ahmedhaddad29/achat-1.0-sk'
                }
            }
        }
        stage('docker compose'){
             steps{
                 script{
                     
                     sh 'docker-compose up -d'
                        }
                  }
        }
        
        stage('Sending email'){
            steps {
             mail bcc: '', body: '''Hello from ahmed,
             Devops Pipeline with success.
             Cordialement''', cc: '', from: '', replyTo: '', subject: 'Devops Timesheet', to: 'ahmed.haddad@esprit.tn'
             }
        }
}
}
