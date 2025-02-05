package com.restful_spring.rest_interceptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

/**
 * Assists with the creation of a {@link RestInterceptor}.
 *
 * @author Dh3356
 * @since 0.1
 */
public class RestInterceptorRegistration {

    private final RestInterceptor restInterceptor;
    private final InterceptorRegistration registration;

    /**
     * Creates a new instance of {@link RestInterceptorRegistration}.
     */
    RestInterceptorRegistration(RestInterceptor restInterceptor, InterceptorRegistry registry) {
        this.restInterceptor = restInterceptor;
        this.registration = registry.addInterceptor(restInterceptor);
    }

    /**
     * Add RestfulPatterns the interceptor should be included in.
     */
    public RestInterceptorRegistration addRestfulPatterns(RestfulPattern... restfulPatterns) {
        return addRestfulPatterns(Arrays.asList(restfulPatterns));
    }

    /**
     * Collection-based variant of {@link #addRestfulPatterns(RestfulPattern...)}.
     * <p> Call InterceptorRegistration's addPathPatterns method with the paths of the RestfulPatterns.
     */
    public RestInterceptorRegistration addRestfulPatterns(Collection<RestfulPattern> restfulPatterns) {
        restInterceptor.restfulPatterns = new ArrayList<>(restfulPatterns);
        registration.addPathPatterns(restfulPatterns.stream()
                .map(RestfulPattern::getPath)
                .toList()
        );
        return this;
    }

    /**
     * Specify an order position to be used. Default is 0.
     */
    public RestInterceptorRegistration order(int order) {
        registration.order(order);
        return this;
    }
}
