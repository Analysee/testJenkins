pipeline {
    agent any
    tools {
        maven 'maven' 
        jdk 'jdk'
    }
    stages {
        stage ('Initialize') {
	      
            steps {
   
                sh 'mvn -version'
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
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
        stage('Statical Code Analysis') {
            steps {
                withSonarQubeEnv('werk') {
                   sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.2:sonar'
                            // Here, we could define e.g. sonar.organization, needed for sonarcloud.i
                           
                            // Additionally needed when using the branch plugin (e.g. on sonarcloud.io)
                         
                }
            }
        }
       
    }
}