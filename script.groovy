def buildJar() {
    echo 'building the application ....'
    sh 'mvn package'                                              
}

def buildImage() {
    echo 'building the docker image ....'
    withCredentials([usernamePassword(credentialsId: 'my-docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER' )]) {
        sh 'docker build -t jkiks/demo-app:jma-2.0 .'
        sh 'echo $PASS | docker login -u $USER --password-stdin'
        sh 'docker push jkiks/demo-app:jma-2.0'
    }
}

def testApp() {
    echo 'Testing the application ....'
    echo "Executing pipeline for branch $BRANCH_NAME"
}

def deployApp() {
    echo 'deploying the application ....'
}

return this
