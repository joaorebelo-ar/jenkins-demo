package com.jrebelo.jenkins.demo;

import java.time.Duration;
import java.util.stream.Stream;

import org.reactivestreams.Publisher;

import reactor.core.publisher.Flux;

public class DemoImpl implements DemoInterface {

    private final Publisher<?> startPublisher;
    private final long valueLimit;
    private final Duration delay;

    public DemoImpl(Publisher<?> startPublisher, long valueLimit) {
        this.startPublisher = startPublisher;
        this.valueLimit = valueLimit;
        this.delay = Duration.ofSeconds(1);
    }

    public DemoImpl(Publisher<?> startPublisher, long valueLimit, Duration delay) {
        this.startPublisher = startPublisher;
        this.valueLimit = valueLimit;
        this.delay = delay;
    }

    @Override
    public Stream<Integer> stream() {
        return Flux.range(0, Integer.MAX_VALUE)
                   .delaySubscription(startPublisher)
                   .takeWhile( val -> val < valueLimit)
                   .delayElements(delay)
                   .toStream()
                   ;
    }

}