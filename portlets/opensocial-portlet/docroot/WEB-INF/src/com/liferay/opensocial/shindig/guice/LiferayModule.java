/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.opensocial.shindig.guice;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import com.liferay.opensocial.shindig.config.LiferayJsonContainerConfig;
import com.liferay.opensocial.shindig.service.LiferayActivityService;
import com.liferay.opensocial.shindig.service.LiferayAlbumService;
import com.liferay.opensocial.shindig.service.LiferayAppDataService;
import com.liferay.opensocial.shindig.service.LiferayMediaItemService;
import com.liferay.opensocial.shindig.service.LiferayPersonService;
import com.liferay.opensocial.shindig.util.ShindigUtil;

import org.apache.shindig.config.ContainerConfig;
import org.apache.shindig.social.opensocial.oauth.OAuthDataStore;
import org.apache.shindig.social.opensocial.spi.ActivityService;
import org.apache.shindig.social.opensocial.spi.AlbumService;
import org.apache.shindig.social.opensocial.spi.AppDataService;
import org.apache.shindig.social.opensocial.spi.MediaItemService;
import org.apache.shindig.social.opensocial.spi.MessageService;
import org.apache.shindig.social.opensocial.spi.MessageService.NotImplementedMessageService;
import org.apache.shindig.social.opensocial.spi.PersonService;
import org.apache.shindig.social.sample.oauth.SampleOAuthDataStore;

/**
 * @author Michael Young
 */
public class LiferayModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(String.class).annotatedWith(
			Names.named("shindig.canonical.json.db")).toInstance(
				"sampledata/canonicaldb.json");

		bind(ActivityService.class).to(LiferayActivityService.class);
		bind(AlbumService.class).to(LiferayAlbumService.class);
		bind(AppDataService.class).to(LiferayAppDataService.class);
		bind(ContainerConfig.class).to(LiferayJsonContainerConfig.class);
		bind(MediaItemService.class).to(LiferayMediaItemService.class);
		bind(MessageService.class).to(NotImplementedMessageService.class);
		bind(OAuthDataStore.class).to(SampleOAuthDataStore.class);
		bind(PersonService.class).to(LiferayPersonService.class);

		requestStaticInjection(ShindigUtil.class);
	}

}