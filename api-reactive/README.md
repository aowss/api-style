# Synchronous API

## Implementation

* The controller method returns `ResponseEntity<Flux<CourseRepresentation>>`
* The service method returns `Flux<Course>`

Question: should the Reactive API's controller return type be `ResponseEntity<Flux<CourseRepresentation>>` or `Mono<ResponseEntity<CourseRepresentation>>` or `Mono<ResponseEntity<Flux<CourseRepresentation>>>` ? See [Spring WebFlux documentation](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html#webflux-ann-responseentity)
