node{
  stage ('Build') {
    withMaven(maven: 'maven') {
      sh "mvn clean install"
    } 
  }
  stage('SonarQube analysis') {
    withSonarQubeEnv('sonar') {
	  withMaven(maven: 'maven'){
      sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.2:sonar'
    }
	}
  }
     stage('Deploy') {
        withCredentials([azureServicePrincipal('mySP2')]) {
            sh 'az login --service-principal -u $AZURE_CLIENT_ID -p $AZURE_CLIENT_SECRET -t $AZURE_TENANT_ID'
            sh 'az account set -s $AZURE_SUBSCRIPTION_ID'
            sh 'az resource list'
			azureWebAppPublish appName: 'lindacare-java', azureCredentialsId: 'mySP2', dockerImageName: '', dockerImageTag: '', dockerRegistryEndpoint: [],  filePath: '*.war', publishType: 'file', resourceGroup: 'lindacare-jenkins-test', slotName: '', sourceDirectory: '', targetDirectory: ''
			sh 'az logout'
        }
    }
}