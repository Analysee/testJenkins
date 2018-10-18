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
    nexusArtifactUploader artifacts: [[artifactId: 'test', classifier: '', file: 'test.zip', type: 'zip']], credentialsId: '', groupId: '1', nexusUrl: 'jenkins5lindacare.westeurope.cloudapp.azure.com:8081', nexusVersion: 'nexus2', protocol: 'http', repository: 'lindacare', version: '1'

}
	}
  }
}