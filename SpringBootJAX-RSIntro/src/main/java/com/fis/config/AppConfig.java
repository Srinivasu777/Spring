package com.fis.config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
@ApplicationPath("/api")
public class AppConfig extends ResourceConfig {

	public AppConfig() {
		packages("com.fis.resource");
	}
}
