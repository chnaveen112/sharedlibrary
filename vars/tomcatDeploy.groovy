def call(config){
        def credId = config['credId']
        def ip = config['ip']
        def userName = config['userName']
        def tomcatHome = config['tomcatHome']
        def warName = config['warName']
sshagent(['credId']) {
        //to stop the tomcat server
        sh "ssh -o StrictHostKeyChecking=no ${userName}@${ip} ${tomcatHome}/bin/shutdown.sh"
        //delete old war file from tomcat
        sh "ssh ${userName}@${ip} rm -rf ${tomcatHome}/webapps/${warName}*"
        // copy latest war from jenkins to tomcat using scp command
             sh "scp target/springmvc.war ${userName}@${ip}:${tomcatHome}/webapps/"
        //start the server  
             sh "ssh ${userName}@${ip} ${tomcatHome}/bin/startup.sh"
}
}
