#!groovy

node {
    checkout scm
    gradle 'clean test'
}

def gradle(args) {
    sh "${tool 'gradle-2-14-1'}/bin/gradle ${args}"
}
