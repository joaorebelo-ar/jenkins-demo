package com.jrebelo.jenkins.demo;

import java.time.Duration;

import org.junit.Test;

import junit.framework.Assert;
import reactor.core.publisher.DirectProcessor;

public class DemoImplTest {

    @Test
    public void testDoesntExceedLimit(){
        DirectProcessor<Object> testProcessor = DirectProcessor.create();
        final long numberOfExpectedElements = 10;
        DemoInterface demo = new DemoImpl(testProcessor, numberOfExpectedElements, Duration.ZERO);
        
        var stream = demo.stream();
        testProcessor.sink().next(new Object());
        
        final long numberOfElements = stream.count();
        Assert.assertEquals("Number of Elements is different than expected", 
                            numberOfExpectedElements, numberOfElements);

        

    }
}