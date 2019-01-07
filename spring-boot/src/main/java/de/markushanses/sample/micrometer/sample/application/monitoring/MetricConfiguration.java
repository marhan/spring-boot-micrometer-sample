package de.markushanses.sample.micrometer.sample.application.monitoring;

import io.github.mweirauch.micrometer.jvm.extras.ProcessMemoryMetrics;
import io.github.mweirauch.micrometer.jvm.extras.ProcessThreadMetrics;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricConfiguration {


	@Bean
	public MeterBinder processMemoryMetrics() {
		return new ProcessMemoryMetrics();
	}

	@Bean
	public MeterBinder processThreadMetrics() {
		return new ProcessThreadMetrics();
	}


}
