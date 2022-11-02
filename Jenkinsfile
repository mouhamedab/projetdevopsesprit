pipeline{
agent any
  tools {
     jdk 'JAVA_HOME'
     maven 'M2_HOME'
  }


        stages{


                 stage('Build Maven Spring'){
                             steps{
                                sh 'mvn -B -DskipTests clean install '
                             }
                         }













        }

      }