node {
    def MvnHome=tool name: 'maven-3', type: 'maven'
	def MavenCMD="${MvnHome}/bin/mvn"
	def docker=tool name: 'docker', type: 'dockerTool'
	def DockerCMD="${docker}/bin/docker"
    stage('GitHub Repo checkout & Preparation') { 
       git credentialsId: 'GitHub', url: 'https://github.com/GautamDJain/SpringBoot_BootCamp.git'  
    }
    stage('Maven Build, Unit test & Package') {
                sh "${MavenCMD} clean package"       
    }
    stage('Docker image build & push to Docker Hub') {
        sh "${DockerCMD} --version"
        sh "${DockerCMD} build -t gautamjainsagar/myspringbootimage ."
        withCredentials([string(credentialsId: 'DockerHubPass', variable: 'dockerHubPass')]) {
           sh "${DockerCMD} login -u gautamjainsagar -p ${dockerHubPass}"     
        }
	    sh "${DockerCMD} push gautamjainsagar/myspringbootimage"
    }
    stage('Docker image pull & run') {
	sshagent(['Docker_User_SSH']) {
        sh 'ssh -o StrictHostKeyChecking=no cloud_user@10.128.0.4 docker --version'
        //sh 'docker run -d -p 8088:8080 gautamjainsagar/myspringbootimage'
    }
}
}
