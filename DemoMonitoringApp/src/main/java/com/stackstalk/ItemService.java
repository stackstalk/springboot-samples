package com.stackstalk;

import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;

@Component
public class ItemService {

	private static int bookOrderId = 0;
	private static int movieOrderId = 0;
	private Counter bookCounter = null;
	private Counter movieCounter = null;
	
	public ItemService(CompositeMeterRegistry meterRegistry) {		
		bookCounter = meterRegistry.counter("order.books");
		movieCounter = meterRegistry.counter("order.movies");
	}
	
	public String orderBook() {		
		bookOrderId += 1;
		bookCounter.increment();
		return new String("Ordered Book with id = " + bookOrderId);
	}
	
	public String orderMovie() {		
		movieOrderId += 1;
		movieCounter.increment();
		return new String("Ordered Movie with id = " + movieOrderId);
	}
}

