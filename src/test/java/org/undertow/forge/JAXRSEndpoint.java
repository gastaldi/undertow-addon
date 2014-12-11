package org.undertow.forge;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/testme")
public class JAXRSEndpoint {
	@GET
	public String hello() {
		return "Hello JAXRS World";
	}
}
