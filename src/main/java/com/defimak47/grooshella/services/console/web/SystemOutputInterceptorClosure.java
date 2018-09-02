package com.defimak47.grooshella.services.console.web;

import java.util.Arrays;
import groovy.lang.Closure;

/**
 * Interceptor that captures execution output console from the grovy script.
 */
public final class SystemOutputInterceptorClosure extends Closure<Boolean> {

    private static final long serialVersionUID = 1L;

    /**
     * Ouput data buffer.
     */
    StringBuilder stringBuilder = new StringBuilder();

    /**
     * Interceptor constructor.
     *
     * @param owner the owner of the interceptor.
     */
    public SystemOutputInterceptorClosure(Object owner) {
        super(owner);
    }

    /**
     * Add parameters to the operation buffer.
     *
     * @param params Capture the standard output.
     * @return boolean result.
     */
    @Override
    public Boolean call(Object params) {
        stringBuilder.append(String.valueOf(params));
        return false;
    }

    /**
     * Add the parameter to the output buffer.
     *
     * @param args Array of standard output messages.
     * @return boolean result.
     */
    @Override
    public Boolean call (Object... args) {
        if (null != args && 2 == args.length) {
            stringBuilder.append(String.valueOf(args[1]));
        } else {
            stringBuilder.append(Arrays.toString(args));
        }
        return false;
    }

    /**
     * Gets the buffer where it's captured the System.out printing.
     * <p>
     * Init the buffer once the content its mapped.
     *
     * @return String with the output content.
     */
    public String getStringBuffer() {
        String result = this.stringBuilder.toString();
        this.stringBuilder.setLength(0);
        return result;
    }
}
