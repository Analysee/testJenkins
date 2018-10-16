pipeline {
    agent any
    tools {
        maven 'maven' 
        jdk 'jdk'
    }
    stages {
        stage ('Initialize') {
            steps {
   
                sh ' mvn -version'
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }
        stage('SonarQube analysis') {
            steps {
                sh 'mvn sonar:sonar \
                -Dsonar.projectKey=please \
                -Dsonar.organization=analysee-github \
                -Dsonar.host.url=https://sonarcloud.io \
                -Dsonar.login=480fc6930d0cca103bb7c7b33407506c0c168868'
            }
			
        }
         stage ('Build') {
            steps {
                sh 'mvn -Dmaven.test.failure.ignore=true install' 
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml' 
                }
            }
        }
		stage('Artifact'){
		  steps {
      nexusArtifactUploader {
        nexusVersion('nexus2')
        protocol('http')
        nexusUrl('localhost:8080/nexus')
        groupId('sp.sd')
        version('2.4')
        repository('NexusArtifactUploader')
        credentialsId('44620c50-1589-4617-a677-7563985e46e1')
        artifact {
            artifactId('nexus-artifact-uploader')
            type('jar')
            classifier('debug')
            file('nexus-artifact-uploader.jar')
        }
        artifact {
            artifactId('nexus-artifact-uploader')
            type('hpi')
            classifier('debug')
            file('nexus-artifact-uploader.hpi')
        }
      }
    }
		}
    }
	
}