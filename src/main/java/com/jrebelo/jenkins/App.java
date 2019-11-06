package com.jrebelo.jenkins;

import com.jrebelo.jenkins.config.DemoConfiguration;
import com.jrebelo.jenkins.demo.DemoImpl;
import com.jrebelo.jenkins.demo.DemoInterface;
import com.jrebelo.jenkins.demo.DemoResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import reactor.core.publisher.DirectProcessor;

public class App extends Application<DemoConfiguration> {


    

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(java.lang.invoke.MethodHandles.lookup().lookupClass());
    public static void main(String[] args) {
        LOGGER.info("Demo App is Starting");
        new App().run(args);
        LOGGER.info("Demo App is Stopping");
    }


    @Override
    public String getName() {
        return "jenkins-demo";
    }

    @Override
    public void initialize(Bootstrap<DemoConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(DemoConfiguration configuration, Environment environment) {
        final DemoResource resource = new DemoResource(configuration.getDefaultValue());
        environment.jersey().register(resource);
    }
}