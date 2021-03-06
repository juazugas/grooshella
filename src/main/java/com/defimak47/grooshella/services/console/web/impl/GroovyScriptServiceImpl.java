package com.defimak47.grooshella.services.console.web.impl;

import com.defimak47.grooshella.services.console.web.GroovyScriptService;
import com.defimak47.grooshella.services.console.web.SystemOutputInterceptorClosure;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.ui.SystemOutputInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Service implementation for running groovy scripts.
 * <p>
 * Adds to the execution context : Spring application context and the logger.
 */
@Service(GroovyScriptService.NAME)
public class GroovyScriptServiceImpl implements GroovyScriptService, ApplicationContextAware {

    private static final String ISO_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    /**
     * Spring application context.
     */
    ApplicationContext applicationContext;

    /**
     * The Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GroovyScriptServiceImpl.class);

    /**
     * Groovy execution script method.
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> executeScript(String script) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Executing Script: " + script);
        }
        if (StringUtils.isEmpty(script)) {
            return null;
        }
        return eval(script);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Script evaluation passed to the Groovy interpreter.
     *
     * @param script the groovy script.
     * @return the map with the results.
     */
    protected Map<String, Object> eval(String script) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("script", script);
        resultMap.put("startTime", getNowDt());

        // Capture output and error console 
        SystemOutputInterceptorClosure outputCollector = new SystemOutputInterceptorClosure(null);
        SystemOutputInterceptor systemOutputInterceptor = new SystemOutputInterceptor(outputCollector);
        try {
            systemOutputInterceptor.start();
            Map<String, Object> bindingValues = new HashMap<>();
            resultMap.put("result", eval(script, bindingValues));
        } catch (RuntimeException t) {
            LOGGER.error("GroovyScriptServiceImpl eval :", t);
            resultMap.put("error", t.getMessage());
        } finally {
            systemOutputInterceptor.stop();
        }
        try {
            systemOutputInterceptor.close();
        } catch (IOException e) {
            LOGGER.error("Error closing interceptor.", e);
        }

        resultMap.put("output", outputCollector.getStringBuffer().trim());
        resultMap.put("endTime", getNowDt());
        return resultMap;
    }


    /**
     * Call Groovyshell interpreter.
     *
     * @param script        the groovy script.
     * @param bindingValues binding variables to the execution context.
     * @return Scripting result.
     */
    public String eval(String script, Map<String, Object> bindingValues) {
        GroovyShell shell = createShell(bindingValues);
        Object result = shell.evaluate(script);
        String resultString = result == null ? "null" : result.toString();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("GroovyScriptServiceImpl", "eval result: " + resultString);
        }
        return resultString;
    }

    /**
     * Create the groovy shell interpreter.
     *
     * @param bindingValues variables bound to the context.
     * @return the groovy shell interpreter.
     */
    private GroovyShell createShell(Map<String, Object> bindingValues) {
        bindingValues.put("context", applicationContext);
        bindingValues.put("log", LOGGER);
        return new GroovyShell(Thread.currentThread().getContextClassLoader(), new Binding(bindingValues));
    }


    /**
     * Gets the date time formated.
     */
    private String getNowDt() {
        SimpleDateFormat df = new SimpleDateFormat(ISO_DATE_FORMAT);
        return df.format(new Date());
    }
}
