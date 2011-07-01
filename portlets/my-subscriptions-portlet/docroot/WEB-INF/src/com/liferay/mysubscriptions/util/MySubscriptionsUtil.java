/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.mysubscriptions.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.security.permission.ResourceActionsUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.asset.AssetRendererFactoryRegistryUtil;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.AssetRendererFactory;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBThread;
import com.liferay.portlet.messageboards.service.MBThreadLocalServiceUtil;

import java.util.Locale;

/**
 * @author Peter Shin
 * @author Jonathan Lee
 */
public class MySubscriptionsUtil {

	public static AssetRenderer getAssetRenderer(
		String className, long classPK) {

		try {
			if (className.equals(MBThread.class.getName())) {
				className = MBMessage.class.getName();

				MBThread mbThread = null;

				mbThread = MBThreadLocalServiceUtil.getThread(classPK);

				classPK = mbThread.getRootMessageId();
			}

			AssetRendererFactory assetRendererFactory =
				AssetRendererFactoryRegistryUtil.
					getAssetRendererFactoryByClassName(
						className);

			return assetRendererFactory.getAssetRenderer(classPK);
		}
		catch (Exception e) {
		}

		return null;
	}

	public static String getAssetURLViewInContext(
			String className, long classPK)
		throws PortalException, SystemException {

		if (className.equals(BlogsEntry.class.getName())) {
			return PortalUtil.getLayoutFullURL(classPK, PortletKeys.BLOGS);
		}

		if (className.equals(MBCategory.class.getName())) {
			return PortalUtil.getLayoutFullURL(
				classPK, PortletKeys.MESSAGE_BOARDS);
		}

		return null;
	}

	public static String getClassNameIdText(long classNameId, Locale locale) {
		String className = PortalUtil.getClassName(classNameId);

		return ResourceActionsUtil.getModelResource(locale, className);
	}

	public static String getTitleText(
		String className, long classPK, String title) {

		if (Validator.isNotNull(title)) {
			return title;
		}

		if (className.equals(BlogsEntry.class.getName())) {
			title = "Blog at ";
		}

		if (className.equals(MBCategory.class.getName())) {
			title = "Message Board at ";
		}

		title += getGroupTitle(className, classPK);

		if (Validator.isNull(title)) {
			title = String.valueOf(classPK);
		}

		return title;
	}

	protected static String getGroupTitle(String className, long classPK) {
		String groupTitle = null;

		try {
			Group group = GroupLocalServiceUtil.getGroup(classPK);
			groupTitle = group.getDescriptiveName();

		}
		catch (Exception e) {
		}

		return groupTitle;
	}

}