# Synchronous API

## Implementation

* The controller methood returns a `ResponseEntity`
* The service method returns `List<Course>`

## Run

### Command Line

__From the root of the project__ :

* Build and package

> `mvn package`

* Start the service

> `java --enable-preview -jar target/api-sync.jar`

The server port is `9000` as specified in the [application configuration](./src/main/resources/application.yaml).

* Access the API

> `curl -X GET 'http://localhost:9000/sync/courses?departmentCode=POL'`

* Debug

If you need to debug the service, use the following command to start the service

> `java -Xdebug -Xnoagent -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n -jar target/api-sync.jar`

then connect a remote debugging session to port `8000`

* Profile using Java Mission Control

Download Java Mission Control from [here for OpenJDK](https://jdk.java.net/jmc/) or [here for Oracle](https://www.oracle.com/technetwork/java/javaseproducts/downloads/jmc7-downloads-5868868.html) since it's not part of the JDK 11 anymore.

If you need to profile the service, use the following command to start the service

> `java -XX:+FlightRecorder -jar target/api-sync.jar` 

For offline profiling use

> `java -XX:+FlightRecorder -XX:StartFlightRecording=duration=200s,filename=flight.jfr -jar target/api-sync.jar`


