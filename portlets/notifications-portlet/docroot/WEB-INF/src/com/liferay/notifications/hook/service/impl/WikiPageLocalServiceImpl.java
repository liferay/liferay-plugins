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

package com.liferay.notifications.hook.service.impl;

import com.liferay.compat.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.notifications.util.NotificationsUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.asset.AssetRendererFactoryRegistryUtil;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.AssetRendererFactory;
import com.liferay.portlet.wiki.model.WikiNode;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.service.WikiPageLocalService;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lin Cui
 */
public class WikiPageLocalServiceImpl extends WikiPageLocalServiceWrapper {

	public WikiPageLocalServiceImpl(WikiPageLocalService wikiPageLocalService) {
		super(wikiPageLocalService);
	}

	@Override
	public WikiPage updateStatus(
			long userId, WikiPage page, int status,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		WikiPage wikiPage = super.updateStatus(
			userId, page, status, serviceContext);

		int notificationType =
			UserNotificationDefinition.NOTIFICATION_TYPE_ADD_ENTRY;

		String subscriptionClassName = _WIKI_NODE_CLASS_NAME;
		long subscriptionClassPK = wikiPage.getNodeId();

		if (serviceContext.isCommandUpdate()) {
			notificationType =
				UserNotificationDefinition.NOTIFICATION_TYPE_UPDATE_ENTRY;

			subscriptionClassName = _WIKI_PAGE_CLASS_NAME;
			subscriptionClassPK = wikiPage.getResourcePrimKey();
		}

		AssetRenderer assetRenderer = _assetRendererFactory.getAssetRenderer(
			wikiPage.getPageId());

		String entryURL = NotificationsUtil.getEntryURL(
			assetRenderer, PortletKeys.WIKI, serviceContext);

		if (Validator.isNotNull(entryURL)) {
			NotificationsUtil.sendNotificationEvent(
				wikiPage.getCompanyId(), PortletKeys.WIKI,
				_WIKI_PAGE_CLASS_NAME, wikiPage.getPageId(),
				assetRenderer.getTitle(serviceContext.getLocale()), entryURL,
				notificationType,
				getSubscribersOVPs(
					wikiPage, subscriptionClassName, subscriptionClassPK),
				userId);
		}

		return wikiPage;
	}

	protected List<ObjectValuePair<String, Long>> getSubscribersOVPs(
			WikiPage wikiPage, String subscriptionClassName,
			long subscriptionClassPK)
		throws SystemException {

		List<ObjectValuePair<String, Long>> subscribersOVPs =
			new ArrayList<ObjectValuePair<String, Long>>();

		subscribersOVPs.add(
			new ObjectValuePair<String, Long>(
				subscriptionClassName, subscriptionClassPK));

		return subscribersOVPs;
	}

	protected AssetRendererFactory _assetRendererFactory =
		AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
			_WIKI_PAGE_CLASS_NAME);

	private static final String _WIKI_NODE_CLASS_NAME =
		WikiNode.class.getName();

	private static final String _WIKI_PAGE_CLASS_NAME =
		WikiPage.class.getName();

}