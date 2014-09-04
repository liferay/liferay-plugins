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
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBCategoryConstants;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBThread;
import com.liferay.portlet.messageboards.service.MBMessageLocalService;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lin Cui
 */
public class MBMessageLocalServiceImpl extends MBMessageLocalServiceWrapper {

	public MBMessageLocalServiceImpl(
		MBMessageLocalService mbMessagePageLocalService) {

		super(mbMessagePageLocalService);
	}

	@Override
	public MBMessage updateStatus(
			long userId, long messageId, int status,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		MBMessage mbMessage = super.updateStatus(
			userId, messageId, status, serviceContext);

		if (mbMessage.getCategoryId() ==
				MBCategoryConstants.DISCUSSION_CATEGORY_ID) {

			return mbMessage;
		}

		int notificationType =
			UserNotificationDefinition.NOTIFICATION_TYPE_ADD_ENTRY;

		if (serviceContext.isCommandUpdate()) {
			notificationType =
				UserNotificationDefinition.NOTIFICATION_TYPE_UPDATE_ENTRY;
		}

		AssetRenderer assetRenderer = _assetRendererFactory.getAssetRenderer(
			mbMessage.getMessageId());

		String entryURL = NotificationsUtil.getEntryURL(
			assetRenderer, PortletKeys.MESSAGE_BOARDS, serviceContext);

		if (Validator.isNotNull(entryURL)) {
			NotificationsUtil.sendNotificationEvent(
				mbMessage.getCompanyId(), PortletKeys.MESSAGE_BOARDS,
				_MB_MESSAGE_CLASS_NAME, mbMessage.getMessageId(),
				assetRenderer.getTitle(serviceContext.getLocale()), entryURL,
				notificationType, getSubscribersOVPs(mbMessage), userId);
		}

		return mbMessage;
	}

	protected List<ObjectValuePair<String, Long>> getSubscribersOVPs(
			MBMessage mbMessage)
		throws PortalException, SystemException {

		List<ObjectValuePair<String, Long>> subscribersOVPs =
			new ArrayList<ObjectValuePair<String, Long>>();

		subscribersOVPs.add(
			new ObjectValuePair<String, Long>(
				_MB_THREAD_CLASS_NAME, mbMessage.getThreadId()));

		long categoryId = mbMessage.getCategoryId();

		if (categoryId <= 0) {
			categoryId = mbMessage.getGroupId();
		}

		List<Long> categoryIds = new ArrayList<Long>();

		categoryIds.add(categoryId);

		MBCategory category = mbMessage.getCategory();

		if (categoryId != MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) {
			categoryIds.addAll(category.getAncestorCategoryIds());
		}

		for (long curCategoryId : categoryIds) {
			subscribersOVPs.add(
				new ObjectValuePair<String, Long>(
					_MB_CATEGORY_CLASS_NAME, curCategoryId));
		}

		return subscribersOVPs;
	}

	protected AssetRendererFactory _assetRendererFactory =
		AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
			_MB_MESSAGE_CLASS_NAME);

	private static final String _MB_CATEGORY_CLASS_NAME =
		MBCategory.class.getName();

	private static final String _MB_MESSAGE_CLASS_NAME =
		MBMessage.class.getName();

	private static final String _MB_THREAD_CLASS_NAME =
		MBThread.class.getName();

}