node{
    
    def mvn = tool("M3") + "/bin/mvn"
    def DOCKER_IMAGE_NAME = "applicationTourisque"

    stage('Build'){
	    sh '${mvn} clean package'
    }

    stage('Push image'){
	    docker.withRegistry(env.DOCKER_REGISTRY_URL, 'DOCKER_REGISTRY_USER') {
		    def image = docker.image(DOCKER_IMAGE_NAME)
		    image.push("latest")

		    dockerFingerprintFrom dockerfile: 'http-api/docker/Dockerfile', image: DOCKER_IMAGE_NAME
	    }
    }

}
