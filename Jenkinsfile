node {
    stage('Build & Deploy') {
        docker.withRegistry(env.VOXLOUD_DOCKER_REGISTRY, "voxloud-docker-registry") {
            docker.image('voxloud.azurecr.io/voxloud-maven-3.3.9-jdk-8:9').inside {
                // Repo checkout with submodules
                checkout([$class: 'GitSCM', branches: scm.branches, doGenerateSubmoduleConfigurations: true, extensions: scm.extensions + [[$class: 'SubmoduleOption', parentCredentials: true]], userRemoteConfigs: scm.userRemoteConfigs])
                sh 'mvn verify'
            }
        }
    }
}

stage('Sonar analysis') {

    node {
        docker.image('voxloud.azurecr.io/voxloud-maven-3.3.9-jdk-8:9').inside {
            sh "mvn sonar:sonar -Dmaven.repo.local=${pwd()}/.m2repo"
        }
    }
}