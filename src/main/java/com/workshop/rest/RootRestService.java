package com.workshop.rest;

import io.swagger.jaxrs.config.BeanConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is responsible for injecting RESTful WebService implementation
 */

// @ApplicationPath is mandatory.
// servlet.mapping.prefix defined in web.xml and URL has to be match with @ApplicationPath value
@ApplicationPath("/rest")
public class RootRestService extends Application {

    // Swagger configuration
    public RootRestService() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/workshop_rest/rest");
        beanConfig.setResourcePackage("com.workshop.rest");
        beanConfig.setScan(true);
    }

    @Override
    public Set<Class<?>> getClasses() {
        // Don't define class type, so we can inject more classes
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(SimpleStudentService.class);


        // Swagger registration
        classes.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        classes.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
        return classes;
    }
}
