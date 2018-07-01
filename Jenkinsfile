node{

	def mvn = tool("maven-tool") + "/bin/mvn"
		def DOCKER_IMAGE_NAME = "applicationTourisque"

		stage("checkout source"){
			checkout scm
		}
	stage('Build and push image'){
		sh "${mvn} clean package"
	}
}
