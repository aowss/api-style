package api.micasa.com;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = api.micasa.com.App.class)
@AutoConfigureMockMvc
@DisplayName("Asynchronous API Tests")
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
        MvcResult result = mockMvc
                .perform(get("/async/courses?departmentCode={code}", "POL"))
                .andReturn();

        mockMvc
                .perform(asyncDispatch(result))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(75)));
    }

    @Test
    @DisplayName("Unknown department")
    void noCourses() throws Exception {
        MvcResult result = mockMvc
                .perform(get("/async/courses?departmentCode={code}", "LOP"))
                .andReturn();

        mockMvc
                .perform(asyncDispatch(result))
                .andExpect(status().isNoContent());
    }
}
