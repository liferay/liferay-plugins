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

package com.liferay.share.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.share.model.EntitySocialFeed;
import com.liferay.share.service.base.EntitySocialFeedLocalServiceBaseImpl;
import com.liferay.share.service.persistence.EntitySocialFeedPK;

import java.util.List;
import java.util.Map;

/**
 * @author Sherry Yang
 */
@ProviderType
public class EntitySocialFeedLocalServiceImpl
	extends EntitySocialFeedLocalServiceBaseImpl {

	public void addEntitySocialFeed(
		long classNameId, long classPK, long feedClassNameId,
		long feedClassPK) {

		EntitySocialFeedPK pk = new EntitySocialFeedPK(
			classNameId, classPK, feedClassNameId, feedClassPK);

		EntitySocialFeed EntitySocialFeed =
			entitySocialFeedPersistence.fetchByPrimaryKey(pk);

		if (EntitySocialFeed == null) {
			EntitySocialFeed = entitySocialFeedPersistence.create(pk);

			entitySocialFeedPersistence.update(EntitySocialFeed);
		}
	}

	public void addEntitySocialFeeds(
		long classNameId, long classPK,
		Map<Long, List<Long>> feedClassNameClassPKs) {

			for (Long feedClassNameId : feedClassNameClassPKs.keySet()) {
				List<Long> feedClassPKs = feedClassNameClassPKs.get(
					feedClassNameId);

				if ((feedClassPKs == null) || feedClassPKs.isEmpty()) {
					continue;
				}

				for (Long feedClassPK : feedClassPKs) {
					addEntitySocialFeed(
						classNameId, classPK, feedClassNameId, feedClassPK);
				}
		}
	}

	public void deleteEntitySocialFeeds(long classNameId, long classPK) {
		entitySocialFeedPersistence.removeByC_C(classNameId, classPK);
	}

	public List<EntitySocialFeed> getEntitySocialFeeds(
		long classNameId, long classPK) {

		return entitySocialFeedPersistence.findByC_C(classNameId, classPK);
	}

}