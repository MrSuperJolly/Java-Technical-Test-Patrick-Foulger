# Answer Dev Academy Java Technical Test

### ⚠️ **Do not fork** this repository. Download a copy and push to your own repository. ⚠️

----
## Introduction

This section of the test has 3 steps which you will need to complete, and a set of optional steps. The instructions for each step are listed below. 

The Java 11 application is a Spring Boot Application built with Maven. It uses an H2 in memory database which is refreshed on starting the application. 

When the application is running, you can browse to http://localhost:5000 to test out the API and access the database. 

> In order to run the project, you must first have a version of Java's JDK11 installed on your machine.

----
## Step 1
Complete the implementation of the JSON endpoint that returns the full list of people from the PeopleRepository. If there are zero people returned from the PeopleRepository then an empty JSON array should be returned. 

----
## Step 2
Implement a JSON endpoint that returns a single person from the PeopleRespository based on the id parameter. If null is returned from the PeopleRepository with the supplied id then a not found error should be returned.

----
## Step 3
Complete the implementation of the endpoint that receives a JSON object to update a person using the PeopleRepository based on the id parameter. Once the person has been successfully updated, the person should be returned from the endpoint. If null is returned from the PeopleRepository then a not found error should be returned. 

----
## Optional
Add the following endpoints to the API:

* A JSON endpoint that returns a single colour from the ColoursRepository based on the id parameter. If null is returned from the ColoursRepository with the supplied id then a not found error should be returned.

* A JSON endpoint that creates a new colour in the ColoursRepository. 

* A JSON endpoint that creates a new person in the PersonRepository. 

### **Requirements**

- Java 11 - [https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html)
- An IDE such as Eclipse - [https://www.eclipse.org/downloads/](https://www.eclipse.org/downloads/)

### **Instructions**

- Open the project `dev-academy-java in your preferred IDE.
- Search for the `TODO` items and work your way through steps 1 to 3.
- Run the unit tests and make sure everything passes.

### **Submission**

Before submission, make sure to remove the following folders:

- `/target` folder