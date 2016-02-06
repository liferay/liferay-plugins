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

package com.liferay.akismet.moderation.messaging;

import com.liferay.akismet.util.AkismetConstants;
import com.liferay.akismet.util.AkismetUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.wiki.model.WikiPage;
import com.liferay.wiki.service.WikiPageLocalServiceUtil;

import java.util.List;

/**
 * @author Peter Shin
 */
public class UpdateWikiPagesListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		long[] companyIds = PortalUtil.getCompanyIds();

		for (long companyId : companyIds) {
			updateWikiPages(companyId);
		}
	}

	protected void updateWikiPages(long companyId) {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			WikiPage.class, PortalClassLoaderUtil.getClassLoader());

		Property summaryProperty = PropertyFactoryUtil.forName("summary");

		dynamicQuery.add(
			summaryProperty.eq(AkismetConstants.WIKI_PAGE_PENDING_APPROVAL));

		Property modifiedDateProperty = PropertyFactoryUtil.forName(
			"modifiedDate");

		dynamicQuery.add(
			modifiedDateProperty.lt(AkismetUtil.getRetainSpamTime()));

		List<WikiPage> wikiPages = WikiPageLocalServiceUtil.dynamicQuery(
			dynamicQuery);

		for (WikiPage wikiPage : wikiPages) {
			wikiPage.setSummary(AkismetConstants.WIKI_PAGE_MARKED_AS_SPAM);

			WikiPageLocalServiceUtil.updateWikiPage(wikiPage);
		}
	}

}