pipeline {
  agent any
  stages {
    stage('Verify Tools') {
      parallel {
        stage('java') {
          steps {
            sh 'java -version'
            sh 'which java'
          }
        }
        stage('maven') {
          steps {
            sh 'mvn -version'
            sh 'which mvn'
          }
        }
        stage('docker') {
          steps {
            sh 'docker --version'
            sh 'which docker'
          }
        }
      }
    }
    stage('Build') {
      steps {
        echo 'Building...'
        sh 'mvn clean install -B -DskipTests'
        sh 'ls'
        sh 'pwd'
        sh 'docker build -t dota2analyticsapp .'
      }
    }
    stage('Test') {
      steps {
        echo 'Testing...'
      }
    }
    stage('Deploy') {
      steps {
        echo 'Deploying...'
        sh 'docker rm -f dota2analyticsapp-con || true'
        sh 'docker run -d -p 12001:12001 --restart always --name dota2analyticsapp-con dota2analyticsapp'
        sh 'docker image prune -f'
      }
    }
  }
  post {
    always {
      cleanWs()

    }

  }
  options {
    disableConcurrentBuilds()
    timeout(time: 10, unit: 'MINUTES')
  }
}