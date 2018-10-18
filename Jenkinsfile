node{
  stage ('Build') {

    withMaven(
        // Maven installation declared in the Jenkins "Global Tool Configuration"
        maven: 'maven',

        mavenLocalRepo: '.repository') {
		
      // Run the maven build
      sh "mvn clean install"
    } 
  }
  stage('SonarQube analysis') {
    withSonarQubeEnv('sonar') {
	  withMaven(   maven: 'maven'){
      // requires SonarQube Scanner for Maven 3.2+
      sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.2:sonar'
    }
	}
  }
  
}