package com.vvi.todo.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class Prometheus {
	private final MeterRegistry registry;
	private final Map<String, Counter> metrics;

	public Prometheus(final MeterRegistry registry) {
		this.registry = registry;
		this.metrics = new HashMap<>();
	}

	@PostConstruct
	protected void setUp() {
		log.info("Setting up metrics");
		Counter c1 = Counter.builder("boards.created").register(registry);
		Counter c2 = Counter.builder("tasks.created").register(registry);
		Counter c3 = Counter.builder("user.http-error").register(registry);

		metrics.put("boards.created", c1);
		metrics.put("tasks.created", c2);
		metrics.put("user.http-error", c3);

	}
}
