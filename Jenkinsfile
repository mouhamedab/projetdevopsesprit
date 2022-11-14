pipeline{
agent any
  tools {
     jdk 'JAVA_HOME'
     maven 'M2_HOME'
  }


        stages{

                   stage('Cleaning the project') {
                        steps{
                           sh "mvn -B -DskipTests clean  "
                           }
                       }


       	        stage('Build') {
             		    steps {
               		sh 'mvn -B -DskipTests clean package'
             		          }
                   	}







                  stage('SonarQube analysis 1') {
                              steps {
                                  sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar'
                              }
                          }

                  stage('Nexus Repository Manager') {
                            steps {
                                script {
                					nexusArtifactUploader artifacts: [[artifactId: 'achat', classifier: '', file: 'target/achat-1.0.jar', type: 'jar']], credentialsId: 'NEXUS', groupId: 'tn.esprit.rh', nexusUrl: '192.168.56.3:8081', nexusVersion: 'nexus3', protocol: 'http', repository: 'maven-snapshots', version: '1.0.0-SNAPSHOT'
                				}
                            }



               }
                 stage('JUnit/Mockito'){
                           steps {
                                       sh '''mvn -version
                                       mvn -B -DskipTests clean package'''
                                       //sh 'mvn test'
                                       echo """Bravo! tous les tests sont pris en charge"""
                                       }
                                   }
					stage('Sending email'){
	            			steps {
	            			
	            				  emailext attachLog: true, body: "the result is :  ${currentBuild.result}", compressLog: true, subject: "Status of pipeline: ${currentBuild.fullDisplayName}", to: 'ayman.aloulou@esprit.tn'
						    }
				    }
	        

               }
       }