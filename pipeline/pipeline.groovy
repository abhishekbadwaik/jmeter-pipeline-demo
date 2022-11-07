#!/usr/bin/env groovy
node {

    stage('Initialise') {
        /* Checkout the scripts */
        checkout scm: [
                $class: 'GitSCM',
                userRemoteConfigs: [
                        [
                                url: "https://github.com/abhishekbadwaik/jmeter-pipeline-demo.git",
                                credentialsId: "abhishekGitCredentials"
                        ]
                ],
                branches: [[name: "main"]]
        ], poll: false
    }

    stage('Build') {
        echo "Complete set-up for Build"
        echo "${test_value}"
    }

    stage('Execute Performance Tests') {
        dir("${WORKSPACE}/scripts") {
            sh '/opt/apache-jmeter-5.5/bin/jmeter.sh -n -t Shift-Left.jmx -l Shift-Left.jtl -Joptestvalue=${test_value}"
        }
    }

    stage('Analyse Results') {
        echo "Analyse results"
    }
}
