#!/usr/bin/env groovy

def call(def args) {
    def mvnHome = tool 'Maven 3.6.1'
    def javaHome = tool 'OpenJDK 10.0.2'
    
    withEnv(["JAVA_HOME=${javaHome}", "PATH+MAVEN=${mvnHome}/bin:${env.JAVA_HOME}/bin"]) {
        sh "${mvnHome}/bin/mvn ${args} --batch-mode -V -U -e -Dsurefire.useFile=false"
    }
}