#!/usr/bin/env groovy

def call(def args) {
    def sonarScanner = tool 'SonarScanner'

    withSonarQubeEnv('Geosatis_Sonarqube') {
        def pom = readMavenPom()
        def sonarKey = pom.parent.groupId + ':' + pom.artifactId
        def projectName = "GEOPOLIS ${pom.artifactId} ${env.BRANCH_NAME}"

        sh "${sonarScanner}/bin/sonar-scanner -Dsonar.projectKey=\"${sonarKey}\" -Dsonar.projectName=\"${projectName}\" -Dsonar.branch=${env.BRANCH_NAME}"
    }
    timeout(time: 10, unit: 'MINUTES') {
        waitForQualityGate abortPipeline: true
    }
}