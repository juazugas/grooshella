package com.defimak47.grooshella.web;

import com.defimak47.grooshella.services.console.web.GroovyScriptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Controller for manage the Groovy Web console
 *
 * @author jzuriaga
 */
@Controller
@RequestMapping(value = "/console")
public class WebConsoleController {

    /**
     * Scripting service.
     */
    @Autowired
    private GroovyScriptService groovyScriptService;

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WebConsoleController.class);

    /**
     * Web method to run the groovy script.
     * <p>
     * NOTE. It needs a valid user.
     *
     * @param request Web request object.
     * @param script  the groovy script.
     * @return JSON object with the response.
     */
    @RequestMapping(value = "/script")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseBody
    public Map<String, Object> groovyConsole(HttpServletRequest request,
                                             @RequestParam(value = "script", required = true) String script) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("WebConsoleController", "entering to groovyConsole " + request);
        }

        return groovyScriptService.executeScript(script);
    }

}
