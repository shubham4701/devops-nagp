pipeline {
    agent any
    tools {
        maven 'Maven3'
    }
    options {
    //     // Append time stamp to the console output.
    //     timestamps()
    //     timeout(time: 1, unit: 'HOURS')
    //     // Do not automatically checkout the SCM on every stage. We stash what
    //     // we need to save time.
        skipDefaultCheckout()
    //     // Discard old builds after 10 days or 30 builds count.
    //     buildDiscarder(logRotator(daysToKeepStr: '10', numToKeepStr: '10'))
    //     //To avoid concurrent builds to avoid multiple checkouts
    //     disableConcurrentBuilds()
    }
    stages {

        stage('Start') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh "mvn install -DskipTests"
            }
        }

        stage('Unit Testing') {
            steps {
                sh "mvn test"
            }
        }

        stage('Sonar Analysis') {
            steps {
                withSonarQubeEnv("Test_Sonar") {
                    sh "mvn sonar:sonar"
                }
            }
        }

        stage('Upload to Artifactory') {
            steps {
                rtMavenDeployer(
                    id: 'deployer',
                    serverId: '123456789@artifactory',
                    releaseRepo: 'CI-Automation-JAVA',
                    snapshotRepo: 'CI-Automation-JAVA'
                )
                rtMavenRun(
                    pom: 'pom.xml',
                    goals: 'clean install',
                    deployerId: 'deployer',
                )
                rtPublishBuildInfo(
                    serverId: '123456789@artifactory',
                )
            }
        }

        stage('Docker Image') {
            steps {
                sh returnStdout: true, script: 'docker build -t dtr.nagarro.com:443/i-mohitsharma04-develop:%BUILD_NUMBER% -f Dockerfile .'
            }
        }

        stage('Containers') {
            steps {
                parallel(
                    PrecontainerCheck: {
                        script {
                            try {
                                sh "docker stop c-mohitsharma04-develop"
                                sh "docker rm c-mohitsharma04-develop"
                            } catch (err) {
                                echo "No running container"
                            }
                        }
                    },
                    PushtoDTR: {
                        sh returnStdout: true, script: 'docker push dtr.nagarro.com:443/i-mohitsharma04-develop:%BUILD_NUMBER%'
                    }
                )
            }
        }

        stage('Docker deployment') {
            steps {
                sh 'docker run --name c-mohitsharma04-develop -d -p 6100:8080 dtr.nagarro.com:443/i-mohitsharma04-develop:%BUILD_NUMBER%'
            }
        }

        post {
            always {
                echo "Build pipeline completed"
            }
        }
    }
}