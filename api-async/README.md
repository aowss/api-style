# Asynchronous API

## Implementation

* The controller method returns `CompletableFuture<ResponseEntity<List<CourseRepresentation>>>`
* The service method returns `CompletableFuture<List<Course>>`
