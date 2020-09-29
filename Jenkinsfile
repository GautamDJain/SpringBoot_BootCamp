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
		sh 'ssh -o StrictHostKeyChecking=no cloud_user@10.128.0.4 docker stop springbootapp || true'
		sh 'ssh -o StrictHostKeyChecking=no cloud_user@10.128.0.4 docker rm -f springbootapp || true'
		sh 'ssh -o StrictHostKeyChecking=no cloud_user@10.128.0.4 docker run -d -p 8088:8080 --name springbootapp gautamjainsagar/myspringbootimage'
        //sh 'docker run -d -p 8088:8080 gautamjainsagar/myspringbootimage'
    }
}
 stage('Email Notificatin') {
	 print "${currentBuild.currentResult}, ${env.JOB_NAME}, ${env.BUILD_NUMBER} ${env.BUILD_URL}"
	  print "This is ${currentBuild.currentResult}, ${JOB_NAME}, ${BUILD_NUMBER} ${BUILD_URL}"
 mail bcc: '', body: "${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\\n More info at: ${env.BUILD_URL}", cc: '', from: '', replyTo: '', subject: "Jenkins Build Job ${env.JOB_NAME} Status is ${currentBuild.currentResult}", to: 'gautamjain2011@gmail.com'
}
}
