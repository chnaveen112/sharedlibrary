def call(){
sshagent(['tomcat']) {
        //to stop the tomcat server
             sh "ssh -o StrictHostKeyChecking=no ec2-user@172.31.9.71 /opt/tomcat8/bin/shutdown.sh"
        //delete old war file from tomcat
             sh "ssh ec2-user@172.31.9.71 rm -rf /opt/tomcat8/webapps/springmvc*"
        // copy latest war from jenkins to tomcat using scp command
             sh "scp target/springmvc.war ec2-user@172.31.9.71:/opt/tomcat8/webapps/"
        //start the server  
             sh "ssh ec2-user@172.31.9.71 /opt/tomcat8/bin/startup.sh"
}
}
