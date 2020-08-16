# Application version info from Git commit
This java program is a web-style API, with single endpoint "/version". At HTTP GET request to "/version" endpoint, it will return application version information in JSON format as below.

```
"myapplication": [
 {
  "version": "1.0",
  "lastcommitsha": "38dc043",
  "description": "Demo to show Application version"
 }
]
```
Besides, this program demonstrates:
- How to create a CI pipeline
- How to integrate with other code quality check tools as part of the CI pipeline
- How to containerise application as a single deployable artefact, encapsulating all dependencies

## Getting Started

This program is written in Java programming language and uses below technologies:

- SprintBoot for web-stype API microservice
- Maven for Build tool
- Maven git-commit-id-plugin for capturing git information
- Travis for CI build and deploy
- Codecov for Code coverage
- SonarCloud for Code Quality
- Docker for containerisation

The response of the application endpoint "/version" is constructed as below:
- "version" is picked up from git.closest.tag.name property of git-commit-id-plugin, which returns the tag name that is closest to the current commit. This is something along the line of 'git describe HEAD --abbrev=0'.
- "lastcommitsha" is the commit SHA ID abbreviated to first 7 digits. Actual SHA ID is 40 digits long, so made it simple.
- "description" is hard-coded.

### Prerequisites

To understand how the program works, one needs to be familiar with Travis CI and Maven build, so that one can build the application and test it. It's helpful to be familiar with SprintBoot, Codecov and SonarCloud tools, but not mandatory.

Below are the prerequisites:
- A Github account
- A Travis CI account integrated with GitHub repository (created at https://travis-ci.com/)
- A Codecov account integated with GitHub repository (created at https://codecov.io/). You can use other tool and accordingly modify .travis.yml file.
- A SonarCloud account integated with GitHub repository (created at https://sonarcloud.io/. You also need to able to run 'travis encrypt' in a machine to encrypt the Sonar token to use in .travis.yml file. You can utilise other Code quality tool and accordingly modify .travis.yml.
- Familiarity with Docker and containerisation

### Installation and Deployment

The application has below key files
-  src\main\java\com\se\tech\versiondemo\VersiondemoApplication.java, which contains all the necessary codes.
- src\test\java\com\se\tech\versiondemo\VersiondemoApplicationTests.java, which contains the Test suite that Maven uses during build
- .travis.yml for Travis CI pipeline configuration.
- pom.xml for Mavel build instructions.
- Dockerfile for Docker build instructions.

There are three options to build and test the application.

Option 1. Please download the codes from this github and import this as a Maven project in Eclipse or your favorite IDE. After importing, please perform 'mv clean install' either from IDE or commandline under the root project folder. This will build, test the Test suite and create a distributable jar 'versiondemo-0.0.1-SNAPSHOT.jar' with all dependencies in the target folder (under the project main folder), which is the only file you need to run the application.

Option 2. Fork the repository to another one in GitHub or other Git repository, and integrat with Travis CI (and CodeCov & SonarCloud) If you have set up .travis.yml and Dockerfile correctly, you can run your own CI, and push the images to Dockerhub to download later to your local PC to run and test. 

Option 3. This is most likely the easiest option to build and test the code, if you have a Docker environment setup in a machine. There is a Dockerfile.withoutCI is provided for this purpose to run without any CI pipeline. You can rename to Dockerfile and use for Docker run as below.

- git clone https://github.com/genesis3k/appversion.git
- cd appversion
- mv Dockerfile.withoutCI Dockerfile
- sudo docker build --tag versiondemo:latest .

The automated test will be run as part of the Maven build. Build will be successful in any of the three above cases, if TEST is passed successfully.

## Running the application

Option 1. After confirming the <project folder>/target/versiondemo-0.0.1-SNAPSHOT.jar is created with Maven build successfully, please run in a command line like below:
- java -jar target/versiondemo-0.0.1-SNAPSHOT.jar
  
By default the application will run Tomcat listening at port = 8080, but you can change the port as below:
- java -Dserver.port=8000 -jar target/versiondemo-0.0.1-SNAPSHOT.jar

Option 2 & 3. After downloading or creating the Docker image, you can run the Docker container like below:
- sudo docker run -p 8080:8080 --detach --name versiondemo versiondemo:latest

After confirming the application is running fine by one of the above methods, 
- either, go to a local browser and type **localhost:8080/version**
- or, run **curl localhost:8080/version** in the command line

(change port if you have specified your own)

You should see an output like below on localhost:8080/version endpoint,
```
"myapplication": [
 {
  "version": "1.0",
  "lastcommitsha": "38dc043",
  "description": "Demo to show Application version"
 }
]
```
If you try **localhost:8080/** only, you will see pure JSON format, without any additional text "myapplication" outside of JSON {} block.
```
{"lastcommitsha":"3580032","description":"Demo to show Application version","version":"1.0"}
```

## Author

* **Sanu Ehsan** - 

## License

This project is free to use, without any support.