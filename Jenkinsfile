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
		nexusArtifactUploader(
		nexusVersion: 'nexus3',
		protocol: 'http',
		nexusUrl: 'my.nexus.address',
		groupId: 'com.example',
		version: version,
		repository: 'RepositoryName',
		credentialsId: 'CredentialsId',
		artifacts: [
			[artifactId: projectName,
			 classifier: '',
			 file: 'my-service-' + version + '.jar',
			 type: 'jar']
			]		
 )
		}
		
		}
    }
	
}