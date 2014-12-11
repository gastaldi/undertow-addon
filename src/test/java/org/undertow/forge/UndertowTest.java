package org.undertow.forge;

import java.net.URL;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.forge.addon.resource.Resource;
import org.jboss.forge.addon.resource.ResourceFactory;
import org.jboss.forge.arquillian.AddonDependency;
import org.jboss.forge.arquillian.Dependencies;
import org.jboss.forge.arquillian.archive.ForgeArchive;
import org.jboss.forge.furnace.repositories.AddonDependencyEntry;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class UndertowTest {

	@Deployment
	@Dependencies({
			@AddonDependency(name = "org.jboss.forge.furnace.container:cdi"),
			@AddonDependency(name = "org.jboss.forge.addon:resources"),
			@AddonDependency(name = "org.undertow.forge:undertow-addon") })
	public static ForgeArchive getDeployment() {
		ForgeArchive archive = ShrinkWrap
				.create(ForgeArchive.class)
				.addBeansXML()
				.addClasses(HelloWorldHandler.class, JAXRSEndpoint.class)
				.addAsAddonDependencies(
						AddonDependencyEntry
								.create("org.jboss.forge.furnace.container:cdi"),
						AddonDependencyEntry
								.create("org.jboss.forge.addon:resources"),
						AddonDependencyEntry
								.create("org.undertow.forge:undertow-addon"));
		return archive;
	}

	@Inject
	private ResourceFactory resourceFactory;

	@Test
	public void testUndertowInjection() throws Exception {
		Resource<URL> resource = resourceFactory.create(new URL(
				"http://localhost:8080"));
		Assert.assertNotNull(resource);
		Assert.assertTrue(resource.exists());
		Assert.assertEquals("Hello World", resource.getContents());
	}

	@Test
	public void testResteasySupport() throws Exception {
		Resource<URL> resource = resourceFactory.create(new URL(
				"http://localhost:8080/rest/testme"));
		Assert.assertNotNull(resource);
		Assert.assertTrue(resource.exists());
		Assert.assertEquals("Hello JAXRS World", resource.getContents());
	}
}