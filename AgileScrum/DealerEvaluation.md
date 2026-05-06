# Dealer Evaluation Final Project

## Learning Objectives

After completing the project:

- Clone a repository from GitHub
- Run Maven build
- Host a Spring Boot Java web application
- Write User Stories
- Containerize your web application
- Deploy your container on IBM Cloud
- Enhance your code
- Deploy changes on IBM Cloud

### About the Course Project

This is the final project for **Cloud Native, Microsevices, Containers, DevOps, and Agile** course. Will apply the knowledge and skills learned in the course to a simulated scenario.

In this project, you are given a **Dealer Evaluation** application and implement those Stories. You are not being tested on Java programming skills in this project but will be required to complete each task, with hints and guidance provided.

The 6 tasks in this hands-on project correspond to the activities performed by someone working in a Cloud Native environment, working with Microservices and CI/CD, and practicing Scrum methodology.

### Working with files in Cloud IDE

#### Create a new file

Right click in the file explorer and select the `New File` option to create a file.
In the prompt, enter the file name.
Once the file has been created, it will appear in the explorer panel.

### Verify the environment and command-line tools

1. Open a terminal window by using the menu in the editor: `Terminal > New Terminal`.

2. Verify that `docker` CLI is installed.

`docker --version`

3. Verify that `ibmcloud` CLI is installed.

`ibmcloud version`

4. Verify that `java` is installed.

`java --version`

5. Verify that Maven is installed.

`mvn --version`

### Start the Code Engine

1. In the menu in the lab environment, click on the `Cloud` dropdown menu and select on the `Code Engine`. The code engine setup panel appears. Click on the `Create Project` to begin.

2. The code engine environment takes a while to prepare.
   There will be a progress status indicated in the setup panel.

3. Once the code engine setup is complete, can see that it is active. Click on the `Code Engine CLI` to begin the pre-configured CLI in the terminal.

4. Will observe the pre-configured CLI startup and the home directory are set to the current directory. As a part of the pre-configuration, the project and Kubeconfig have been set up.

### Task 1: Create the application

1. Open a terminal window by using the menu in the editor: `Terminal > New Terminal`.

2. If not in the current project folder, copy and paste the following code to change to the project folder.

`cd /home/project`

3. Run the following command to clone the Git repository that contains the starter code need for this project if the Git repository doesn't already exist.

`git clone https://github.com/ibm-developer-skills-network/lmmoq-dealerEvaluation.git dealerEvaluation`

4. Change to the directory `dealerEvaluation` to start working on the lab.

`cd dealerEvaluation`

5. List the contents of this directory to see the artifacts for this lab.

`ls`

6. Run the following command on the terminal to host the web page.

`mvn spring-boot:run`

7. Test the application in the browser.

8. In the terminal, press `CMD + C` to stop the web server.

### Task 2: Plan application modernization

One of the key improvements needed for the Dealer Evaluation application is to modernize it and use a cloud environment to host it.

This is a major piece of work, so it should be planned as an Epic and several stories.

#### Epic

An Epic is a large and high-level item of work that provides a way to capture and communicate the broader goal of a project. They are too complex to be estimated accurately at the outset, so they are often broken down into smaller, more manageable user stories or features.

#### Story

A story (or user story) is a short, simple description of a feature or requirement from the perspective of the user. It helps the team understand what needs to be built and why.

#### Create Stories

Let's first look at an example of a complete user story for "Containerizing the application."

#### Example: Containerizing the application

**Task Description**: Have the ability to package the application and be deployable in Docker

**User Story**: As a Developer, I want to containerize the application using Docker so that it can be easily deployed, scaled, and managed in different environments.
So that the application runs consistently across different environments (development, testing, production). Dependencies are managed efficiently without conflicts. And deployment and scaling becomes easier.

#### User stories: Assessment

As part of the assessment, create two user stories for the following tasks, based on the format provided in the example above.

#### User story 1: Deploying on the IBM Cloud

**Description**: Instead of hosting on self-managed or in-house virtual machines, deploy the application on the IBM Cloud using the IBM Cloud Code Engine.

#### User story 2: Read products and dealers from JSON

**Description**: The current code has hard-coded data, which is not an ideal practice. To move away from this, you need to write a story.

### Task 3: Containerize the application

The first step toward modernizing your application is to containerize it using Docker.

#### Docker

Docker is an open-source platform and toolset that allows you to automate the deployment, scaling, and management of applications using containerization. Containers are lightweight, isolated, and protable environments that package applications and their dependencies, enabling them to run consistently across different computing environments.

#### Package the application

You first need to create a package to be used in Docker.

`mvn package`

#### Create Dockerfile

Use the following code to put in the Dockerfile:

```
# Use OpenJDK 21 as the base image
FROM openjdk:21-jdk-slim

COPY target/comparison-0.0.1-SNAPSHOT.jar /usr/app/comparison.jar

CMD ["java", "-jar", "/usr/app/comparison.jar"]
```

#### Build the Docker image

Use the following commands to create the Docker image:

```
cd /home/project/dealerEvaluation
docker build -t comparison .
```

#### Run the Docker Image in the Cloud IDE

`docker run -it -d -p 8080:8080 comparison`

#### Verify the Docker image

List Docker images and verify that you see `comparison`.

`docker images`

**Assessment:** Note down the output of `docker images` command.

#### Verify that the Docker image is running

`docker ps -a`

**Assessment:** Note down the `NAMES` value from the output.

#### Verify in the browser

#### Stop and clean the Docker container

Finally, clean up any running containers.

`docker ps -aq | xargs docker stop | xargs docker rm`

**Assessment**: Note down the output from the command to stop and cleanup containers.

### Task 4: Deploy on IBM Cloud

Have successfully run the Dealer Evaluation App from the Cloud IDE.

As part of this learning platform, are provided with basic infrastructure on IBM Cloud. The set up has already been done for you.

#### Build and tag a Docker image

The Docker build command builds Docker images from a Dockerfile.

`cd /home/project/dealerEvaluation
docker build . -t us.icr.io/${SN_ICR_NAMESPACE}/comparison`

#### Push the image to IBM Cloud

To upload an image to a registry, use the `docker push`.

Let's push the image onto IBM Cloud.

`docker push us.icr.io/${SN_ICR_NAMESPACE}/comparison`

#### Deploy the image to IBM Cloud

Deploy the image on IBM Cloud Code Engine.

`ibmcloud ce application create --name comparison --image us.icr.io/${SN_ICR_NAMESPACE}/comparison --registry-secret icr-secret --port 8080`

Check the status

`ibmcloud ce application get --name comparison`

To get just the URL of the application hosted on IBM Cloud

`ibmcloud ce application get --name comparison --output url`

Use the above URL and add `/price/index.html`

**Assessment:** Note down the url pointing to your Dealer Evaluation application hosted on IBM Cloud.

### Task 5: Read products and dealers from JSON

In this task, will work on the story that you specified earlier. The hard-coded data about products and dealers will be replaced by reading dynamically from JSON. This move will help avoid mixing data with code and give you greater flexibility in the future to decouple the two.

#### Make changes in the Dealer Service

The dealer products and prices are hard coded in the following file:

`/home/project/dealerEvaluation/src/main/java/com/comparison/price/service/DealerService.java`

In the function `getData`, replace the code with the following code:

```
        try {
            String json = JsonReader.readJsonFromClasspath("json/dealers.json");
            return objectMapper.readValue(json, DealersDTO.class);
        } catch (Exception e) {
            return null;
        }
```

#### Make changes in the product service

The products are hard coded in the following file:

`/home/project/dealerEvaluation/src/main/java/com/comparison/price/service/ProductService.java`

In the function `getData`, replace the code with the following code:

```
        try {
            String productsJson = JsonReader.readJsonFromClasspath("json/products.json");
            return objectMapper.readValue(productsJson, ProductsDTO.class);
        } catch (Exception e) {
            return null;
        }
```

### Task 6: Deploy changes on IBM Cloud

`cd /home/project/dealerEvaluation
mvn package
docker build . -t us.icr.io/${SN_ICR_NAMESPACE}/comparison`

`docker push us.icr.io/${SN_ICR_NAMESPACE}/comparison`

This time, we are not creating but instead are updating the image. So, we will call the command with `update` instead of `create`.

`ibmcloud ce application update --name comparison --image us.icr.io/${SN_ICR_NAMESPACE}/comparison --registry-secret icr-secret --port 8080`

Should see the output for Headphone from All Dealers.

**Assessment:** Note down the price of Headphones from Binglee, after data is read from JSON.

### Summary

In this lab, you have gained an understanding of how a change is done in an application. First, start off by breaking down the tasks into manageable stories, and then work on them and iterate to a better version of your software. Have also seen how a locally hosted Java web application can be converted to a Docker image, tested, and run locally. Finally, you hosted it in the cloud.

Many developers follow the same process to make their applications cloud native.
