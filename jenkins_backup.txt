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
}