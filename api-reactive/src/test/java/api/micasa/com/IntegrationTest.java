package api.micasa.com;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import javax.inject.Inject;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Reactive API Tests")
class IntegrationTest {

    @Inject
    private WebTestClient webTestClient;

    @Test
    @DisplayName("Get Courses List")
    void coursesList() {
        webTestClient
                .get()
                .uri("/reactive/courses?departmentCode=POL")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type", "application/json")
//                .expectBodyList(CourseRepresentation.class).hasSize(75)
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$.length()").isEqualTo(75);
    }

    @Test
    @DisplayName("Unknown department")
    void noCourses() {
        webTestClient
                .get()
                .uri("/reactive/courses?departmentCode=LOP")
                .exchange()
//                .expectStatus().isNoContent();
                .expectStatus().isOk();
    }

}
