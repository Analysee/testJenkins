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
         stage ('Build') {
            steps {
                sh 'mvn clean install' 
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml' 
                }
            }
        }
        stage('SonarQube analysis') {
            steps {
                sh 'mvn sonar:sonar \
                -Dsonar.host.url=$SONAR_HOST_URL \
                -Dsonar.login=61198c2c65df058fd9543db21cede430e055acfc'
            }
        }

    }
	
}