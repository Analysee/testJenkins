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
        try {
            stage("Building SONAR ...") {
            sh './gradlew clean sonarqube'
            }
        } catch (e) {emailext attachLog: true, body: 'See attached log', subject: 'BUSINESS Build Failure', to: 'abc@gmail.com'
            step([$class: 'WsCleanup'])
            return
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