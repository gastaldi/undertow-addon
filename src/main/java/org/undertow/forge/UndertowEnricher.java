package org.undertow.forge;

import io.undertow.Undertow;

/**
 * Enrichs the {@link Undertow.Builder} instance
 * 
 * @author George Gastaldi
 *
 */
public interface UndertowEnricher {

	/**
	 * Called in
	 * {@link UndertowBootstrap#initializeUndertow(org.jboss.forge.furnace.event.PostStartup)}
	 */
	void enrich(Undertow.Builder builder);

}
