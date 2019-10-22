package com.jrebelo.jenkins.demo;

import java.time.Duration;
import java.util.stream.Stream;

import org.reactivestreams.Publisher;

import reactor.core.publisher.Flux;

public class DemoImpl implements DemoInterface {

    private final Publisher<?> startPublisher;
    private int valueLimit;

    public DemoImpl(Publisher<?> startPublisher, int valueLimit) {
        this.startPublisher = startPublisher;
        this.valueLimit = valueLimit;
    }

    @Override
    public Stream<Integer> stream() {
        return Flux.range(1, Integer.MAX_VALUE)
                   .delaySubscription(startPublisher)
                   .takeWhile( val -> val < valueLimit)
                   .delayElements(Duration.ofSeconds(1))
                   .toStream()
                   ;
    }

}