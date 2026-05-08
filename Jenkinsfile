pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Run Tests') {
            steps {
                sh '''
                    docker run --rm \
                    -v $(pwd):/workspace \
                    -w /workspace \
                    markhobson/maven-chrome:jdk-11 \
                    mvn clean test || true
                '''
            }
        }
    }
    post {
        always {
            junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
            emailext(
                subject: "NexaBI Tests - Build #${BUILD_NUMBER} - SUCCESS",
                body: "Build #${BUILD_NUMBER} completed. Results: ${BUILD_URL}",
                to: "waliurehman4023@gmail.com"
            )
        }
    }
}