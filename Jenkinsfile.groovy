node {
    catchError {
        stage 'Build'
        git url: 'https://github.com/marhan/craps.git', branch: 'master'

        try {
            sh 'chmod +x gradlew'
            sh './gradlew clean test'
        }

    }

}
