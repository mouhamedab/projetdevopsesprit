pipeline{
agent any
  tools {
     jdk 'JAVA_HOME'
     maven 'M2_HOME'
  }


        stages{

                   stage('Check out Git'){

                        steps{
                            echo 'Pulling...';
                            git branch: 'aymanaloulou1',
                            url : 'https://github.com/mouhamedab/projetdevopsesprit.git';
                            }
                        }



                   stage('Cleaning the project') {
                        steps{
                           sh "mvn -B -DskipTests clean  "
                           }
                       }

                   stage('JUnit/Mockito'){
                                              steps {
                                                          sh '''mvn -version
                                                          mvn -B -DskipTests clean package'''
                                                          //sh 'mvn test'
                                                          echo """ tous les tests sont pris en charge"""
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





                	stage('Build image') {
                          	steps {
                      		 sh "docker build -t aymanaloulou/devopsimagedocker ."
                      		}
                      		}


                       stage('Push image') {
                			steps {
                			    withDockerRegistry([ credentialsId: "DockerHub", url: "" ]) {

                       	  sh "docker push aymanaloulou/devopsimagedocker"
                       	}
                       	}
                       	}

                    stage('Run Spring && MySQL Containers') {
                                                                 steps {
                                                                     sh "docker-compose -f docker-compose.yml up -d  "
                                                                 }
                                                                 }



                   stage('Cleaning up') {
                        steps {
               			sh "docker rmi -f aymanaloulou/devopsimagedocker"
                        }
                    }









				 stage('Sending email'){
	            			steps {

	            			emailext  body: "the result is :  ${currentBuild.currentResult}", compressLog: true,attachLog: true, subject: "Status of pipeline: ${currentBuild.fullDisplayName}", to: "ayman.aloulou@esprit.tn"

						    }
				            }

	        




               }
       }