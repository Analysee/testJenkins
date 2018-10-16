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
		steps{
		nexusArtifactUploader artifacts: [
		[artifactId: 'nexus-artifact-uploader', classifier: 'debug', file: 'nexus-artifact-uploader.jar', type: 'jar'], 
		[artifactId: 'nexus-artifact-uploader', classifier: 'debug', file: 'nexus-artifact-uploader.hpi', type: 'hpi']
		], 
		credentialsId: '44620c50-1589-4617-a677-7563985e46e1', 
		groupId: 'sp.sd', 
		nexusUrl: 'localhost:8080/nexus', 
		nexusVersion: 'nexus2', 
		protocol: 'http', 
		repository: 'NexusArtifactUploader', 
		version: '2.4'
		}
		
		}
    }
	
}