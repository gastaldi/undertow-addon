package org.undertow.forge;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import org.jboss.forge.furnace.container.cdi.events.Local;
import org.jboss.forge.furnace.event.PostStartup;
import org.jboss.forge.furnace.event.PreShutdown;

@ApplicationScoped
public class UndertowBootstrap {

	private Undertow undertow;

	public void initializeUndertow(@Observes @Local PostStartup postStartup) {
		this.undertow = Undertow.builder().addHttpListener(8080, "localhost")
				.setHandler(new HttpHandler() {
					@Override
					public void handleRequest(final HttpServerExchange exchange)
							throws Exception {
						exchange.getResponseHeaders().put(Headers.CONTENT_TYPE,
								"text/plain");
						exchange.getResponseSender().send("Hello World");
					}
				}).build();
		undertow.start();
	}

	public void destroy(@Observes @Local PreShutdown preShutdown) {
		if (undertow != null)
			undertow.stop();
	}

	// @Produces
	// public Undertow getUndertow() {
	// return undertow;
	// }
}
