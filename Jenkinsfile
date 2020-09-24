node {
    def mvnHome
    stage('Preparation') { // for display purposes
        // Get some code from a GitHub repository
       git credentialsId: 'github', url: 'https://github.com/GautamDJain/SpringBoot_BootCamp.git'
        // Get the Maven tool.
        // ** NOTE: This 'M3' Maven tool must be configured
        // **       in the global configuration.
     
    }
    stage('Build') {
        // Run the maven build
                mvnHome = tool 'maven-3'
                sh 'mvn clean package'
           
        
    }
    stage('docker image build') {
           sh 'docker --version'
        sh 'docker build -t gautamjainsagar/myimage .'
        
    }
    stage('docker image push & run') {
    withCredentials([string(credentialsId: 'DockerHubPass', variable: 'dockerHubPass')]) {
        sh 'docker login -u gautamjainsagar -p ${dockerHubPass}'
        //sh 'docker push gautamjainsagar/myimage'
        sh 'docker run -d -p 8088:8080 gautamjainsagar/myimage'
    // some block
    }
}
}
