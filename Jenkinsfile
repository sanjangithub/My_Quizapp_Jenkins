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

        // stage('Generate_Version') {
        //     steps {
        //         script {
        //             // Generate a unique version using the current timestamp
        //             def timestamp = new Date().format("yyyyMMddHHmmss")
        //             env.BUILD_VERSION = "1.0.${timestamp}"
        //             echo "Generated Build Version: ${env.BUILD_VERSION}"
        //         }
        //     }
        // }
        
        stage('Code_Build') {
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
     //    stage('Artifact_NexusUploadation') {
     //        steps {
     //            echo 'Started uploading the artifact...'
     //            nexusArtifactUploader(
     //    nexusVersion: 'nexus3',
     //    protocol: 'http',
     //    nexusUrl: 'localhost:8081',
     //    groupId: 'com.sanjan',
     //    version: "${env.BUILD_VERSION}",
     //    // version: '1.8',
     //    repository: 'Quizapp_Hitman',
     //    credentialsId: 'Sj_Nexus',
     //    artifacts: [
     //        [artifactId: 'quizapp',
     //         classifier: '',
     //         // file: "target/quizapp-${env.BUILD_VERSION}.jar",
     //         file: 'target/quizapp-1.4.jar', 
     //         type: 'jar']
     //    ]
     // )
     //            // echo 'uploaded the artifact in nexus artifact repo.'
     //            echo "Uploaded the artifact version ${env.BUILD_VERSION} to Nexus artifact repo."
     //        }
     //    }

        stage('Artifact_NexusUploadation') {
    steps {
        script {
            // Read the current Nexus version from a file or initialize it to 1.0
            def versionFile = 'nexus_version.txt'
            def currentVersion = '1.0' // Default if the file doesn't exist
            if (fileExists(versionFile)) {
                currentVersion = readFile(versionFile).trim()
            }
            
            // Increment the minor version (e.g., 1.8 -> 1.9)
            def versionParts = currentVersion.split('\\.')
            def major = versionParts[0].toInteger()
            def minor = versionParts[1].toInteger() + 1
            env.BUILD_VERSION = "${major}.${minor}"
            
            // Save the new version to the file
            writeFile(file: versionFile, text: env.BUILD_VERSION)
            echo "Updated Nexus version to: ${env.BUILD_VERSION}"
            
            // Upload the artifact to Nexus
            echo 'Started uploading the artifact...'
            nexusArtifactUploader(
                nexusVersion: 'nexus3',
                protocol: 'http',
                nexusUrl: 'localhost:8081',
                groupId: 'com.sanjan',
                version: "${env.BUILD_VERSION}", // Incremented version
                repository: 'Quizapp_Hitman',
                credentialsId: 'Sj_Nexus',
                artifacts: [
                    [artifactId: 'quizapp',
                     classifier: '',
                     file: 'target/quizapp-1.4.jar', // Static artifact version
                     type: 'jar']
                ]
            )
            echo "Uploaded the artifact version ${env.BUILD_VERSION} to Nexus artifact repo."
        }
    }
}

        stage('Email Notification') {
            steps {
                emailext body: '''Hi Sanjan,

The build for quizapp has completed successfully. Please check the dashboard for detailed results.
Dashboard link - http://localhost:8080/job/Quizapp_Nex/

Best Regards,
Jenkins.''', 
                subject: 'Quizapp Jenkins Build - SUCCESS', 
                to: 'sanjan.chikkala@gmail.com'
            }
        }
    }
}
