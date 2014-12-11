package org.undertow.forge;

import io.undertow.Undertow.Builder;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

public class HelloWorldEnricher implements UndertowEnricher {

	@Override
	public void enrich(Builder builder) {
		builder.setHandler(new HttpHandler() {
			@Override
			public void handleRequest(final HttpServerExchange exchange)
					throws Exception {
				exchange.getResponseHeaders().put(Headers.CONTENT_TYPE,
						"text/plain");
				exchange.getResponseSender().send("Hello World");
			}
		});
	}
}
