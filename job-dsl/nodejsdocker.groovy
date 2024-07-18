job('NodeJS Docker example') {
    scm {
        git('https://github.com/ArunkumarRScenai/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('ArunkumarRScenai')
            node / gitConfigEmail('arunkumar.rajan@scenai.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('Nodejs_V20') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('arunkumarrajan/docker-nodejs-demo')
            tag('${GIT_REVISION,length=9}')
            // registryCredentials('arunkumarrajan')
            registryCredentialsId('8823705b-5ceb-4ff9-b345-606fe1f4fd4b')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
