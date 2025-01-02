pipeline {
    agent any

    stages {
        stage('Code_Checkout') {
            steps {
                echo 'Cloning the project from repo..'
                git 'https://github.com/sanjangithub/My_Quizapp_Jenkins.git'
                echo 'Cloning done.'
            }
        }
        stage('Code_Build') {
            when {
                    expression {
                        BRANCH_NAME == 'dev'
                    }
                }
            steps {
                echo 'Build started...'
                bat 'mvn clean compile'
                echo 'Build successfull.'
            }
        }
        stage('Sonar_Scanning') {
            steps {
                echo 'Started sonar scanning on the project.'
                bat 'mvn clean verify sonar:sonar -Dsonar.projectKey=Quiz_sonar_Nexus -Dsonar.host.url=http://localhost:9000 -Dsonar.login=sqp_b5df1de9578c348689f838e3490914225b60c162'
                echo 'Sonar report generation is completed.'
            }
        }
        stage('Artifact_Generation') {
            steps {
                echo 'Started generating artifact...'
                bat 'mvn package'
                echo 'Artifact is generated.'
            }
        }
        stage('Artifact_NexusUploadation') {
            steps {
                echo 'Started uploading the artifact...'
                nexusArtifactUploader(
        nexusVersion: 'nexus3',
        protocol: 'http',
        nexusUrl: 'localhost:8081',
        groupId: 'com.sanjan',
        version: '1.5',
        repository: 'Quizapp_Hitman',
        credentialsId: 'Sj_Nexus',
        artifacts: [
            [artifactId: 'quizapp',
             classifier: '',
             file: 'target/quizapp-1.4.jar', 
             type: 'jar']
        ]
     )
                echo 'uploaded the artifact in nexus artifact repo.'
            }
        }
        stage('Email Notification') {
            steps {
                emailext body: '''Hi Sanjan,

The build for quizapp has completed successfully. Please check the dashboard for detailed results.

Best Regards,
Jenkins.''', 
                subject: 'Quizapp Jenkins Build - SUCCESS', 
                to: 'sanjan.chikkala@gmail.com'
            }
        }
    }
}
