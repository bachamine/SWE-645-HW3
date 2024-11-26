pipeline {
    agent any
    environment {
        DOCKER_IMAGE = 'bachamine/spring-gmu-survey'
        KUBECONFIG_CREDENTIAL_ID = 'kubeconfig-id'
        DOCKER_CREDENTIAL_ID = 'docker-pass'
        K8S_NAMESPACE = 'default'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/bachamine/SWE-645-HW3/survey-app'
            }
        }
        stage('Build') {
            steps {
                echo 'Building the application with Gradle...'
                sh './gradlew clean build'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    echo 'Building Docker image...'
                    docker.build(env.DOCKER_IMAGE)
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                script {
                    echo 'Pushing Docker image...'
                    docker.withRegistry('https://index.docker.io/v1/', env.DOCKER_CREDENTIAL_ID) {
                        docker.image(env.DOCKER_IMAGE).push('latest')
                    }
                }
            }
        }
        stage('Deploy to Kubernetes') {
            steps {
                script {
                    echo 'Deploying to Kubernetes...'
                    withKubeConfig([credentialsId: env.KUBECONFIG_CREDENTIAL_ID]) {
                        sh '''
                            kubectl set image deployment/spring-gmu-survey container-0=${DOCKER_IMAGE}:latest -n ${K8S_NAMESPACE} 
                            kubectl rollout restart deployment spring-gmu-survey -n ${K8S_NAMESPACE}
                            kubectl rollout status deployment/spring-gmu-survey -n ${K8S_NAMESPACE}
                        '''
                    }
                }
            }
        }
    }
    post {
        always {
            echo 'Cleaning up...'
        }
        success {
            echo 'Deployment successful!'
        }
        failure {
            echo 'Deployment failed!'
        }
    }
}
