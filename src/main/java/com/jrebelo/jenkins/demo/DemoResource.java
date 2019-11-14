package com.jrebelo.jenkins.demo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.Duration;

import reactor.core.publisher.DirectProcessor;

@Path("/jenkins-demo")
@Produces(MediaType.APPLICATION_JSON)
public class DemoResource {

    private final DemoInterface demoInterface;
    private final DirectProcessor<Object> processor;
    public DemoResource(int defaultValue) {
        this.processor = DirectProcessor.create();
        this.demoInterface = new DemoImpl(processor, defaultValue, Duration.ZERO);
    }
    

    @GET
    public long getValue() {
        if(!processor.hasCompleted()){
            processor.sink().next(new Object());
            processor.sink().complete();
        }

        return demoInterface
            .stream()
            .reduce(0, (val, acc) -> val + acc);
    }

}