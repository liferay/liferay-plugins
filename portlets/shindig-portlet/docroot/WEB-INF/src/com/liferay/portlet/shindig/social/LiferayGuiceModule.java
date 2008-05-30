/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portlet.shindig.social;

import com.google.inject.AbstractModule;
import com.google.inject.CreationException;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Scopes;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import com.google.inject.spi.Message;

import com.liferay.portlet.shindig.social.service.LiferayActivitiesService;
import com.liferay.portlet.shindig.social.service.LiferayDataService;
import com.liferay.portlet.shindig.social.service.LiferayPeopleService;

import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.shindig.gadgets.BasicGadgetBlacklist;
import org.apache.shindig.gadgets.BasicGadgetTokenDecoder;
import org.apache.shindig.gadgets.BasicRemoteContentFetcher;
import org.apache.shindig.gadgets.CachedContentFetcher;
import org.apache.shindig.gadgets.GadgetBlacklist;
import org.apache.shindig.gadgets.GadgetFeatureRegistry;
import org.apache.shindig.gadgets.GadgetServer;
import org.apache.shindig.gadgets.GadgetSpecFetcher;
import org.apache.shindig.gadgets.GadgetTokenDecoder;
import org.apache.shindig.gadgets.MessageBundleFetcher;
import org.apache.shindig.gadgets.RemoteContentFetcher;
import org.apache.shindig.gadgets.SigningFetcherFactory;
import org.apache.shindig.gadgets.SyndicatorConfig;
import org.apache.shindig.gadgets.http.GadgetRenderer;
import org.apache.shindig.gadgets.http.JsonRpcHandler;
import org.apache.shindig.gadgets.http.ProxyHandler;
import org.apache.shindig.gadgets.http.UrlGenerator;
import org.apache.shindig.gadgets.oauth.OAuthFetcherFactory;
import org.apache.shindig.social.GadgetDataHandler;
import org.apache.shindig.social.opensocial.ActivitiesService;
import org.apache.shindig.social.opensocial.DataService;
import org.apache.shindig.social.opensocial.OpenSocialDataHandler;
import org.apache.shindig.social.opensocial.PeopleService;
import org.apache.shindig.social.samplecontainer.StateFileDataHandler;
import org.apache.shindig.util.ResourceLoader;

/**
 * <a href="LiferayGuiceModule.java.html"><b><i>View Source</i></b></a>
 *
 * @author Raymond Augé
 *
 */
public class LiferayGuiceModule extends AbstractModule {
	  private final Properties properties;
	  private final static String DEFAULT_PROPERTIES = "gadgets.properties";

	/** {@inheritDoc} */
	@Override
	protected void configure() {
	    Names.bindProperties(this.binder(), properties);

	    bind(RemoteContentFetcher.class).to(BasicRemoteContentFetcher.class);

	    bind(RemoteContentFetcher.class)
	        .annotatedWith(GadgetSpecFetcher.class)
	        .to(CachedContentFetcher.class);
	    bind(RemoteContentFetcher.class)
	        .annotatedWith(MessageBundleFetcher.class)
	        .to(CachedContentFetcher.class);

	    bind(GadgetBlacklist.class).to(BasicGadgetBlacklist.class);
	    bind(GadgetTokenDecoder.class).to(BasicGadgetTokenDecoder.class);
	    bind(SigningFetcherFactory.class);
	    bind(OAuthFetcherFactory.class);
	    bind(Executor.class).toInstance(Executors.newCachedThreadPool());

	    bind(SyndicatorConfig.class).in(Scopes.SINGLETON);
	    bind(GadgetFeatureRegistry.class).in(Scopes.SINGLETON);
	    bind(GadgetServer.class).in(Scopes.SINGLETON);

		bind(PeopleService.class).to(LiferayPeopleService.class);
		bind(DataService.class).to(LiferayDataService.class);
		bind(ActivitiesService.class).to(LiferayActivitiesService.class);

	    bind(new TypeLiteral<List<GadgetDataHandler>>() {})
	        .toProvider(GadgetDataHandlersProvider.class);

	    bind(ProxyHandler.class).in(Scopes.SINGLETON);
	    bind(GadgetRenderer.class).in(Scopes.SINGLETON);
	    bind(JsonRpcHandler.class).in(Scopes.SINGLETON);
	    bind(UrlGenerator.class).in(Scopes.SINGLETON);
	}

	  public LiferayGuiceModule(Properties properties) {
	    this.properties = properties;
	  }

	  /**
	   * Creates module with standard properties.
	   */
	  public LiferayGuiceModule() {
	    Properties properties = null;
	    try {
	      InputStream is = ResourceLoader.openResource(DEFAULT_PROPERTIES);
	      properties = new Properties();
	      properties.load(is);
	    } catch (IOException e) {
	      throw new CreationException(Arrays.asList(
	          new Message("Unable to load properties: " + DEFAULT_PROPERTIES)));
	    }
	    this.properties = properties;
	  }

	  public static class GadgetDataHandlersProvider
	      implements Provider<List<GadgetDataHandler>> {
	    List<GadgetDataHandler> handlers;

	    @Inject
	    public GadgetDataHandlersProvider(OpenSocialDataHandler
	        openSocialDataHandler, StateFileDataHandler stateFileHandler) {
	      handlers = new ArrayList<GadgetDataHandler>();
	      handlers.add(openSocialDataHandler);
	      handlers.add(stateFileHandler);
	    }

	    public List<GadgetDataHandler> get() {
	      return handlers;
	    }
	  }
}