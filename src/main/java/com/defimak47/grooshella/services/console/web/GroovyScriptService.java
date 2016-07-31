package com.defimak47.grooshella.services.console.web;

import java.util.Map;

/**
 * Interface call to execute groovy script from web console.
 */
public interface GroovyScriptService {

    String NAME = "GroovyScriptService";

    /**
     * Service method to execute the script from the web console.
     *
     * @param script the grroovy script.
     * @return Map with the results of the execution.
     */
    Map<String, Object> executeScript(String script);

}
