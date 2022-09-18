package api.micasa.com;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
@DisplayName("Synchronous API Tests")
class IntegrationTest {

    private MockMvc mockMvc;

    @Inject
    WebApplicationContext context;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    @DisplayName("Get Courses List")
    void coursesList() throws Exception {
        mockMvc
            .perform(get("/sync/courses?departmentCode={code}", "POL"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$", hasSize(75)));
    }

    @Test
    @DisplayName("Unknown department")
    void noCourses() throws Exception {
        mockMvc
                .perform(get("/sync/courses?departmentCode={code}", "LOP"))
                .andExpect(status().isNoContent());
    }
}
