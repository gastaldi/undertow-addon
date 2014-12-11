package org.undertow.forge.jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/testme")
public class JAXRSEndpoint {
	@GET
	public String hello() {
		return "Hello JAXRS World";
	}
}
