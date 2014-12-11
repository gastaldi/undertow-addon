package org.undertow.forge.jaxrs;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.jboss.resteasy.test.TestPortProvider;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class RestEasyTest {
	private static UndertowJaxrsServer server;

	@BeforeClass
	public static void init() throws Exception {
		server = new UndertowJaxrsServer().start();
	}

	@AfterClass
	public static void stop() throws Exception {
		server.stop();
	}

	@Test
	public void testApplicationPath() throws Exception {
		server.deploy(TestApplication.class);
		Client client = ClientBuilder.newClient();
		String val = client.target(TestPortProvider.generateURL("/rest/testme"))
				.request().get(String.class);
		Assert.assertEquals("Hello JAXRS World", val);
		client.close();
	}
}
