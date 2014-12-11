package org.undertow.forge;

import io.undertow.Undertow;
import io.undertow.Undertow.Builder;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.forge.furnace.container.cdi.events.Local;
import org.jboss.forge.furnace.event.PostStartup;
import org.jboss.forge.furnace.event.PreShutdown;
import org.jboss.forge.furnace.services.Imported;

@ApplicationScoped
public class UndertowBootstrap {

	@Inject
	private Imported<UndertowEnricher> enrichers;

	private Undertow undertow;

	public void initializeUndertow(@Observes @Local PostStartup postStartup) {
		Builder builder = Undertow.builder().addHttpListener(8080, "localhost");
		for (UndertowEnricher undertowEnricher : enrichers) {
			undertowEnricher.enrich(builder);
		}
		this.undertow = builder.build();
		this.undertow.start();
	}

	public void destroy(@Observes @Local PreShutdown preShutdown) {
		if (undertow != null)
			undertow.stop();
	}
}
