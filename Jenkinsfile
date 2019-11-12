
properties([pipelineTriggers([githubPush()])])

def imageName = "leorenan/desafio-twitter"
def imageVersion = "${env.BUILD_ID}"
def taskFamily = "ECS-task-poc-desafio-twitter"
def clusterName = "ECS-Cluster"
def serviceNameBase = "ECS-Service-poc-desafio-twitter"
def serviceName8080 = ""
def elb_listeners_8080 = ""
def serviceName80 = ""
def elb_listeners_80 = ""
def taskRevisaoAtual = ""
 
pipeline {
	agent none
	stages {
	
	    stage ('Checkout'){
	    	when {
                expression {return true}
            }
            agent any
            steps{
            	script{
	    			checkout scm
	    		}
	    	}
	    }
	    
	    stage ('Build Maven'){
	    	when {
                expression {return true}
            }
	    	agent any
            steps{
            	script{
			        withMaven(jdk: 'Java 1.8-221', maven: 'Maven 3.6.2') {
			            sh  "mvn clean package"
			        }
		        }
	        }
	    }
	    
	    stage ('SonarQube Analise'){
	    	when {
                expression {return true}
            }
	    	agent any
            steps{
            	script{
			        withSonarQubeEnv(credentialsId: 'SonarQube', installationName: 'SonarQube') { // You can override the credential to be used
			            withMaven(jdk: 'Java 1.8-221', maven: 'Maven 3.6.2') {
			                sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.6.0.1398:sonar'
			            }
			        }
		        }
	        }
	    }
	    
	    
	    
	    stage ('Build e Push Docker'){  
	    	when {
                expression {return true}
            }  
	    	agent any
            steps{
            	script{
			        def img = docker.build("${imageName}:${env.BUILD_ID}", "--build-arg JAR_FILE=/target/app.jar .")
			        docker.withRegistry("https://registry.hub.docker.com/", '9f8994aa-031b-4c0c-b38a-140179b64435'){
			            img.push()
			        }
			        
			        sh  "docker rmi \$(docker images -q) --force"
		        }
	        }
	    }
	    
	    stage ('ECS Atualiza Task Definition'){  
	    	when {
                expression {return true}
            }  
	    	agent any
            steps{
            	script{
			        def taskDefile = "aws/task-definition-${imageName.replaceAll("/", "-")}.json"
			        sh  "sed -e  's;%BUILD_TAG%;${imageName}:${env.BUILD_ID};g' aws/task-definition.json >  ${taskDefile} "
			        taskRevisaoAtual = sh (
			          returnStdout: true,
			          script:  "                                                              \
			            aws ecs describe-task-definition  --region us-east-1 --task-definition ${taskFamily} | egrep 'revision' | tr ',' ' ' | awk '{print \$2}'"
			        ).trim()
			        
			        sh  "aws ecs register-task-definition  --region us-east-1 --family ${taskFamily} --cli-input-json file://${taskDefile}"
	    		}
	    	}
	        
	    }
	    
	    stage ('Deploy ECS - Variaveis Blue/Geen'){  
	    	when {
                expression {return true}
            }  	
	    	agent any
            steps{
            	script{
			    	def elb_arn = sh (
			    		returnStdout: true,
			    		script: "aws elbv2 describe-load-balancers --region us-east-1 | jq '.LoadBalancers[] | .LoadBalancerArn' | grep ecs-load-balancer"
			    	).replaceAll("\"","").trim()
			    	
			    	sh "aws elbv2 describe-listeners --region us-east-1 --load-balancer-arn  ${elb_arn} > elb_listeners.json"
			   	
			    	elb_listeners_8080 = sh (
			    		returnStdout: true,
			    		script: "cat elb_listeners.json | jq '.Listeners[] | select( .Port == 8080 ) | \"\\(.ListenerArn) \\(.DefaultActions[0].TargetGroupArn)\"'"
			    	).replaceAll("\"","").trim()
			    	
			    	elb_listeners_80 = sh (
			    		returnStdout: true,
			    		script: "cat elb_listeners.json | jq '.Listeners[] | select( .Port == 80 ) |  \"\\(.ListenerArn) \\(.DefaultActions[0].TargetGroupArn)\"'"
			    	).replaceAll("\"","").trim()
			    
			    	if (elb_listeners_8080.contains("blue")){
			    		serviceName8080 = serviceNameBase + "-blue"
			    		serviceName80 = serviceNameBase + "-green"
			    	}else{
			    		serviceName8080 = serviceNameBase + "-green"
			    		serviceName80 = serviceNameBase + "-blue"
			    	}
		        }
	       } 
	    }
	    
	    stage ('Deploy ECS - Porta 8080'){  
	    	when {
                expression {return true}
            }  	
	    	agent any
            steps{
            	script{
		          sh  "aws ecs update-service --region us-east-1 --cluster ${clusterName} --service ${serviceName8080} --task-definition ${taskFamily}:${taskRevisaoAtual}  --desired-count 0"
			        
			      def currentTasks = sh (
			          returnStdout: true,
			          script:  " aws ecs list-tasks --region us-east-1 --cluster ${clusterName} --service ${serviceName8080} | jq '.taskArns[]'"
			      )
			      
			      if(currentTasks){
			      	echo "currentTasks ${currentTasks}"
				      currentTasks.split(' ').each{ currentTask ->
				      	echo "currentTask ${currentTask}"
				      
				      	sh "aws ecs stop-task --region us-east-1 --cluster ${clusterName} --task ${currentTask}"
				      }
			      }
			      
			      sh  "aws ecs update-service --region us-east-1 --cluster ${clusterName} --service ${serviceName8080} --task-definition ${taskFamily}:${taskRevisaoAtual}  --desired-count 1"
			      
			      echo "Aguardando atualização da task"
			      sleep 60
            	}
        	} 
    	}
    	    	
    	stage ('Deploy ECS - Move Porta 8080 para 80 '){  
	    	when {
                expression {return true}
            }  	
	    	agent any
            steps{
            	script{
		          sh  "aws elbv2 modify-listener --region us-east-1  --listener-arn ${elb_listeners_8080.split(" ")[0]} --port 8081"
		          sh  "aws elbv2 modify-listener --region us-east-1  --listener-arn ${elb_listeners_80.split(" ")[0]} --port 8080"
		          sh  "aws elbv2 modify-listener --region us-east-1  --listener-arn ${elb_listeners_8080.split(" ")[0]} --port 80"
		        }
        	}
    	}  
    	
    	stage ('Deploy ECS - Porta 80'){  
	    	when {
                expression {return true}
            }  	
	    	agent any
            steps{
            	script{
		          sh  "aws ecs update-service --region us-east-1 --cluster ${clusterName} --service ${serviceName80} --task-definition ${taskFamily}:${taskRevisaoAtual}  --desired-count 0"
			        
			      def currentTasks = sh (
			          returnStdout: true,
			          script:  " aws ecs list-tasks --region us-east-1 --cluster ${clusterName} --service ${serviceName80} | jq '.taskArns[]'"
			      )
			      
			      if(currentTasks){
			      	echo "currentTasks ${currentTasks}"
				      currentTasks.split(' ').each{ currentTask ->
				      	echo "currentTask ${currentTask}"
				      
				      	sh "aws ecs stop-task --region us-east-1 --cluster ${clusterName} --task ${currentTask}"
				      }
			      }
			      
			      sh  "aws ecs update-service --region us-east-1 --cluster ${clusterName} --service ${serviceName80} --task-definition ${taskFamily}:${taskRevisaoAtual}  --desired-count 1"
		
            	}
        	}
    	}
    	
    	 
    	 
    	
	} 
}
