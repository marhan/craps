node {
    stage 'Build'
    git url: 'https://github.com/marhan/craps.git', branch: 'master'

    sh 'chmod +x gradlew'
    sh './gradlew clean test'
}
