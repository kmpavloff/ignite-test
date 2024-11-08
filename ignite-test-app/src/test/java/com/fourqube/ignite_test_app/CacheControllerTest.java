package com.fourqube.ignite_test_app;

import jakarta.servlet.ServletContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled("Для успешного прохождения требует рабочий кластер Ignite")
@SpringBootTest
class CacheControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void givenWac_whenServletContext_thenItProvidesCacheController() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(webApplicationContext.getBean(CacheController.class));
    }

    @Test
    public void put_and_get_cache_value() throws Exception {
        this.mockMvc.perform(post("/api/cache/testKey").content("testValue")).andDo(print())
                .andExpect(status().isOk()).andExpect(content()
                        .contentType("text/plain;charset=UTF-8"))
                .andExpect(content()
                        .string("OK"));

        this.mockMvc.perform(get("/api/cache/testKey")).andDo(print())
                .andExpect(status().isOk()).andExpect(content()
                        .contentType("text/plain;charset=UTF-8"))
                .andExpect(content()
                        .string("testValue"));
    }
}