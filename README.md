# Deploy a Java application in AWS EC2

## Create a VPC
Navigate to the VPC Dashboard in the AWS Management Console.
Click on “Your VPCs” and then “Create VPC”.
Enter a name and CIDR block (e.g., 10.0.0.0/16) and create the VPC.

## Create a Subnet
In the VPC Dashboard, click on “Subnets” and then “Create subnet”.
Select your VPC, enter a name, CIDR block (e.g., 10.0.1.0/24), and create the subnet.
Create an Internet Gateway and Attach to VPC
Click on “Internet Gateways”, then “Create internet gateway”.
Enter a name and create the Internet Gateway.
Select the created Internet Gateway, click “Actions”, then “Attach to VPC”, and select your VPC.

## Configure Route Table
Click on “Route Tables”, then “Create route table”.
Enter a name, select your VPC, and create the route table.
Select the created route table, go to the “Routes” tab, click “Edit routes”, then “Add route”.
Enter 0.0.0.0/0 in the Destination and select your Internet Gateway for the Target.
Go to the “Subnet Associations” tab, click “Edit subnet associations”, and associate your subnet.

## Set Up Amazon Cognito
Navigate to the Amazon Cognito service in the AWS Management Console.
Click on “Manage User Pools” and then “Create a user pool”.
Enter a name for the pool and customize the pool creation as per your requirements.
Once the pool is created, click on “App clients” under “General settings”, then “Add an app client”, provide a name, and create the app client.

## Set Up API Gateway
Navigate to the API Gateway service in the AWS Management Console.
Click on “Create API”, select “REST API”, and configure it as per your requirements.

## Create a VPC Link to EC2
In the API Gateway console, go to “VPC Links”, then “Create”.
Enter a name, select your VPC, and create the VPC Link.

## Launch an EC2 Instance
Navigate to the EC2 Dashboard in the AWS Management Console.
Click on “Instances”, then “Launch instances”.
Select an Amazon Machine Image (AMI), instance type, and configure instance details.
In the “Configure Instance Details” step, select your VPC and subnet.
Add storage, add tags, configure security group, and review and launch the instance.

## Set Up Load Balancer
In the EC2 Dashboard, go to “Load Balancers”, then “Create Load Balancer”.
Select the type of load balancer as per your requirements, configure it, and add your EC2 instance to it.

## Setup
Dependencies are listed in the pom.xml file and below.
Generate the private and public keys as needed.
java -jar /home/ec2-user/jetty/jetty-home-12.0.6/start.jar jetty.base=/home/ec2-user/jetty/jetty-base --add-modules=server,http,ee10-deploy,ee10-jsp
java -jar /home/ec2-user/jetty/jetty-home-12.0.6/start.jar jetty.base=/home/ec2-user/jetty/jetty-base

java -jar .\start.jar jetty.base=C:\opt\repo\jetty-base --add-modules=server,http,ee10-deploy,ee10-jsp
java -jar .\start.jar jetty.base=C:\opt\repo\jetty-base


mvn archetype:generate -DgroupId=com.justin.keywebapp \
      -DartifactId=key-webapp \
      -Dpackage=com.justin.keywebapp \
      -DarchetypeArtifactId=maven-archetype-webapp \
      -Dversion=1.0-SNAPSHOT \
      -DinteractiveMode=false
