# MoneyTransfer  [![Build Status](https://travis-ci.org/lorenzosax/moneytransfer.svg?branch=master)](https://travis-ci.org/lorenzosax/moneytransfer)
## Bank WebApplication

Candidate: _Lorenzo Gagliani_

A small WebApplication developed for a _Bank technical interview_ (in June 2018) of which the test and Continuous integration part developed during the Software Engineer exam at Unisannio University (in January 2020).

Technologies used:

##### Backend: _**SpringBoot applications**, version 2.1.9.RELEASE_
##### Frontend: _**Angular applications**, version 8_

_For all the details regarding the specifications it is possible to consult the pdf: **tech_interview_test.pdf**_

_You can consult the document that describes the approach and the analysis of the project in the root folder: **Gagliani_MoneyTransfer_Docs_ENG.pdf**_

_You can find a postman collection with all the endpoints developed in root folder: **money-transfer.postman_collection.json**_

## Getting Started


### Installing and Deployment

The Project is composed by one parent pom that import two modules:
- moneytransfer-api
- moneytransfer-ui

the first contain the backend code (REST API) and the second contain the client side application (that use API exposed from server)

Project requires [Gradle](https://services.gradle.org/distributions/gradle-5.6.4-bin.zip) v. 5.6.4  to run.

On root folder run:
```sh
$ ./gradlew bootJar
```

Backend: the following command will generate the _moneytransfer.jar file_ that can deploy using following command:
```sh
$ java -jar moneytransfer.jar
```

Frontend: the following command will generate frontend resources for production:
```sh
$ cd moneytransfer-ui
$ npm run build
```

### Development mode

For _Frontend_ project you can follow README.md in __moneytransfer-ui__ module. 
For _Backend_ you can run the springboot application running this command from __moneytransfer-api__ module:
```sh
$ ./gradlew bootRun
```
In the end the two applications will be served in two different ports:

Frontend:   `http://localhost:4200`

Backend:    `http://localhost:8080`

### _Database access:_
The datasource driver, url, db name and password can be configured in its configuration file (production and development config) that are located:
- Production: moneytransfer/moneytransfer-api/src/main/resources/application-prod.yml
- Develop:    moneytransfer/moneytransfer-api/src/main/resources/application.yml

## Authors

* **Lorenzo Gagliani** - *Initial work* - [lorenzosax](https://github.com/lorenzosax)
