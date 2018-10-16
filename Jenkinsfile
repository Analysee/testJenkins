pipeline {
    agent any
    tools {
        maven 'maven' 
        jdk 'jdk'
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

            //stage("Building SONAR ...") {
              //  steps{
            //sh './maven clean sonarqube'

             //   }
            
        //} 
        
        stage('Statical Code Analysis') {
            steps {
                withSonarQubeEnv('werk') {
                    mvn "$SONAR_MAVEN_GOAL -Dsonar.host.url=$SONAR_HOST_URL -Dsonar.login=$SONAR_AUTH_TOKEN " +
                            // Here, we could define e.g. sonar.organization, needed for sonarcloud.io
                           
                            // Additionally needed when using the branch plugin (e.g. on sonarcloud.io)
                            "-Dsonar.branch.name=$BRANCH_NAME -Dsonar.branch.target=master"
                }
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
    }
}