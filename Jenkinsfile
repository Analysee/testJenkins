pipeline {
    agent any
triggers {
      pollSCM('* * * * *')
    }
    tools {
        maven 'maven'
        jdk 'jdk'
	sonar 'sonar'
    }
    stages {
        stage ('Initialize') {

            steps {
			
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }
  stage('build && SonarQube analysis') {
            steps {
                withSonarQubeEnv('sonar'){
                  sh 'mvn clean package sonar:sonar'
}

        stage ('Build') {
            steps {
                sh 'mvn -Dmaven.test.failure.ignore=true install' 
				sh 'mvn clean package sonar:sonar'
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml' 
                }
            }
        }
    }
}