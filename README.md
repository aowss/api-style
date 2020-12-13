# Comparing Interaction Styles

The objective is to compare the way the code is written depending on the interaction style chosen.

We use [Spring Boot](https://spring.io/projects/spring-boot) and implement a very simple API to retrieve a list of courses.
The courses are stored in a CSV file.

Even though the API is simplistic, the code is written as if it is production coode.  

## Structure

The project is structured as follows :

* [Synchronous API](./api-sync)
* [Asynchronous API](./api-async)
* [Reactive API](./api-reactive)

## Pre-requisites

### Software

The following should be installed locally :

* [Java 11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)
* [Docker](https://www.docker.com)
* [Maven 3.6](http://maven.apache.org/)

### Build

The project is built using [Maven](https://maven.apache.org).

