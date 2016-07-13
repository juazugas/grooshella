package com.defimak47.grooshella.services.console.web;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroovyScriptServiceTest {

    @Autowired
    private GroovyScriptService groovyScriptService;

    @Test
    public void testExecuteScript() {
        Map<String,Object> result = null;
        final String script = "println \"hello world!\"";
        _given: {
        }

        _then: {
            result = groovyScriptService.executeScript(script);
        }

        _expect: {
            assertNotNull(result);
        }
    
    }

}
