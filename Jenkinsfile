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
  stage('build && SonarQube analysis') {
            steps {
                withSonarQubeEnv('werk') {
                    // Optionally use a Maven environment you've configured already
                    withMaven(maven:'maven') {
                        sh 'mvn sonar:sonar \
  -Dsonar.projectKey=please \
  -Dsonar.organization=analysee-github \
  -Dsonar.host.url=https://sonarcloud.io \
  -Dsonar.login=480fc6930d0cca103bb7c7b33407506c0c168868'
                    }
                }
            }
        }
       
    }
}