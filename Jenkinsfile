node {
        def MvnHome=tool name: 'maven-3', type: 'maven'
	def MavenCMD="${MvnHome}/bin/mvn"
	def docker=tool name: 'docker', type: 'dockerTool'
	def DockerCMD="${docker}/bin/docker"
	try {
        stage('GitHub Repo checkout & Preparation') { 
            git credentialsId: 'GitHub', url: 'https://github.com/GautamDJain/SpringBoot_BootCamp.git'  
        }
        stage('Maven Build, Unit test & Package') {
		sh 'java -version'
            sh "${MavenCMD} clean package"       
        }
        stage('Docker image build & push to Docker Hub') {
            sh "${DockerCMD} --version"
		//sh "service ${DockerCMD} start"
           sh "${DockerCMD} build -t gautamjainsagar/myspringbootimage ."
            withCredentials([string(credentialsId: 'DockerHubPass', variable: 'dockerHubPass')]) {
               sh "${DockerCMD} login -u gautamjainsagar -p ${dockerHubPass}"     
            }
	        sh "${DockerCMD} push gautamjainsagar/myspringbootimage"
        }
        stage('Docker image pull & run') {
	        sshagent(['Docker_AWSUser_SSH']) {
                      sh 'ssh -o StrictHostKeyChecking=no ec2_user@3.128.155.70 docker --version'
					  sh "ssh -o StrictHostKeyChecking=no ec2_user@172.31.17.204 service docker start"
		      sh 'ssh -o StrictHostKeyChecking=no ec2_user@172.31.17.204 docker stop springbootapp || true'
		      sh 'ssh -o StrictHostKeyChecking=no ec2_user@172.31.17.204 docker rm -f springbootapp || true'
		      sh 'ssh -o StrictHostKeyChecking=no ec2_user@172.31.17.204 docker run -d -p 8088:8080 --name springbootapp gautamjainsagar/myspringbootimage'
                      //sh 'docker run -d -p 8088:8080 gautamjainsagar/myspringbootimage'
           }
        }
    }
    catch(e){
        currentBuild.result="FAILURE"
        stage('Email Notificatin') {
           mail bcc: '', body: "Your Jenkins Job ${env.JOB_NAME} build is ${currentBuild.currentResult} for build number ${env.BUILD_NUMBER}. \nYou can check Jenkin job console output info at: ${env.BUILD_URL}", cc: '', from: '', replyTo: '', subject: "Jenkins Build Job ${env.JOB_NAME} status is ${currentBuild.currentResult}", to: 'gautamjain2011@gmail.com'
        }
    }
}
