package commands

import com.defimak47.grooshella.services.console.web.GroovyScriptService;

class groovys {

    @Usage("executes script from the command line.")
    @Command
    void main(InvocationContext context,
              @Usage("The script to execute") @Required @Argument String script) {
        def result = [:]
        def scriptService = context.attributes['spring.beanfactory'].getBean(GroovyScriptService.class)
        result = scriptService.executeScript(script);
        out.println "Start time: " + result.startTime
        if (result.error) { 
            out.println "Error : " + result.error
        }  else {
            if (result.result) { 
                out.println "Result : " + result.result
            }
            out.println "Output : "
            out.println result.output
        }
        out.println "End time  : " + result.endTime
    }

}
