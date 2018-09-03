package com.defimak47.grooshella.web;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Test class for request made to home controller.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WebConsoleControllerTest {

    /** MVC request performer.
     */
    private MockMvc mockMvc;

    /**
     * Spring application context.
     */
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * Prepare webapp context for run the tests.
     */
    @Before
    public void setUp () throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup((WebApplicationContext) applicationContext).build();
    }

    /**
     * Test the console template caller.
     *
     * @see HomeController#page(String)
     */
    @Test
    public void testConsolePage () {
        ResultActions result = null;
        final String CONSOLE_PAGE = "console";
        _given: {
        }

        _then: try {
            result = mockMvc.perform(get(String.format("/%s.html", CONSOLE_PAGE)));
        } catch (Exception e) {
            fail(e.getMessage());
        }

        _expect: try {
            result.andExpect(status().isOk()).andExpect(view().name(CONSOLE_PAGE));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     * Test the Groovy script execution.
     *
     * @see WebConsoleControllerTest#groovyConsole(javax.servlet.http.HttpServletRequest, String)
     */
    @Test
    public void testGroovyConsole () {
        ResultActions result = null;
        final String SCRIPT_STR = "println \"hello\"";
        _given: {
        }

        _then: try {
            result = mockMvc.perform(post("/console/script")
                                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                    .param("script", SCRIPT_STR));
        } catch (Exception e) {
            fail(e.getMessage());
        }

        _expect: try {
            result.andExpect(status().isOk())
                  .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                  .andExpect(jsonPath("$['script']").value(SCRIPT_STR))
                  .andExpect(jsonPath("$['startTime']").exists())
                  .andExpect(jsonPath("$['endTime']").exists())
                  .andExpect(jsonPath("$['error']").doesNotExist())
                  .andExpect(jsonPath("$['result']").exists())
                    .andExpect(jsonPath("$['output']").value("hello"));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}
