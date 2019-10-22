package com.jrebelo.jenkins;

import com.jrebelo.jenkins.demo.DemoImpl;
import com.jrebelo.jenkins.demo.DemoInterface;

import reactor.core.publisher.DirectProcessor;

public final class App {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(java.lang.invoke.MethodHandles.lookup().lookupClass());
    public static void main(String[] args) {
        LOGGER.info("Demo App is Starting");
        DirectProcessor<Object> processor = DirectProcessor.create();

        DemoInterface demo = new DemoImpl(processor, 20);

        var integerStream = demo
            .stream();

        processor.sink().next(new Object());

        integerStream.forEach( val -> LOGGER.info("Printing value received {}", val));

        LOGGER.info("Demo App is Stopping");
    }
}