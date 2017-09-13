node{
    
    def mvn = tool("maven-tool") + "/bin/mvn"
    def DOCKER_IMAGE_NAME = "applicationTourisque"
echo "mvn= ${mvn}"
    stage("checkout source"){
        checkout scm
    }
    stage('Build'){
	    sh "${mvn} clean package"
    }

    stage('Push image'){
	    docker.withRegistry(env.DOCKER_REGISTRY_URL, 'DOCKER_REGISTRY_USER') {
		    def image = docker.image(DOCKER_IMAGE_NAME)
		    image.push("latest")

		    dockerFingerprintFrom dockerfile: 'http-api/docker/Dockerfile', image: DOCKER_IMAGE_NAME
	    }
    }

}
