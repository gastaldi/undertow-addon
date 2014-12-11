package org.undertow.forge;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;

import org.jboss.forge.furnace.addons.AddonRegistry;
import org.jboss.forge.furnace.services.Imported;

public class ForgeHttpHandler implements HttpHandler {

	private final AddonRegistry addonRegistry;

	public ForgeHttpHandler(AddonRegistry addonRegistry) {
		super();
		this.addonRegistry = addonRegistry;
	}

	@Override
	public void handleRequest(HttpServerExchange exchange) throws Exception {
		Imported<HttpHandler> services = addonRegistry
				.getServices(HttpHandler.class);
		for (HttpHandler httpHandler : services) {
			httpHandler.handleRequest(exchange);
		}
	}
}
