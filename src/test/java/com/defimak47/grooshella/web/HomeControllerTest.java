package com.defimak47.grooshella.web;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HomeControllerTest {

    
    private MockMvc mockMvc;

    @Autowired
    private ApplicationContext applicationContext;

    @Before
    public void setUp () throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup((WebApplicationContext) applicationContext).build();
    }

    @Test
    public void testPage () {
        ResultActions result = null;
        _given: {
        }

        _then: try {
            result = mockMvc.perform(get("/index.html"));
        } catch (Exception e) {
            fail(e.getMessage());
        }

        _expect: try {
            result.andExpect(status().isOk()).andExpect(view().name("index"));
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }

}
