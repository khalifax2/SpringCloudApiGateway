package com.sxi.photoapp.api.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;

import reactor.core.publisher.Mono;

@Component
public class GlobalFiltersConfiguration {
	
	final Logger logger = LoggerFactory.getLogger(GlobalFiltersConfiguration.class);
	
	@Order(1)
	@Bean
	public GlobalFilter secondPreFilter() {
		
		return (exchange, chain) -> {
			logger.info("Second Global Pre-filter executed.....");
			
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				
				logger.info("Third Global Post-filter executed.....");
				
			}));
		};
	}
	
	@Order(2)
	@Bean
	public GlobalFilter thirdPreFilter() {
		
		return (exchange, chain) -> {
			logger.info("Third Global Pre-filter executed.....");
			
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				
				logger.info("Second Global Post-filter executed.....");
				
			}));
		};
	}
	
	@Order(3)
	@Bean
	public GlobalFilter fourthPreFilter() {
		
		return (exchange, chain) -> {
			logger.info("Fourth Global Pre-filter executed.....");
			
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				
				logger.info("First Global Post-filter executed.....");
				
			}));
		};
	}
}
