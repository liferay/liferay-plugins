/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
			return doGetAssetRenderer(className, classPK);
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

	public static String getTitleText(
		Locale locale, String className, long classPK, String title) {

		if (Validator.isNotNull(title)) {
			return title;
		}

		if (className.equals(BlogsEntry.class.getName())) {
			title = "Blog at ";
		}

		if (className.equals(MBCategory.class.getName())) {
			title = "Message Board at ";
		}

		try {
			Group group = GroupLocalServiceUtil.getGroup(classPK);

			title += group.getDescriptiveName(locale);
		}
		catch (Exception e) {
		}

		if (Validator.isNull(title)) {
			title = String.valueOf(classPK);
		}

		return title;
	}

	protected static AssetRenderer doGetAssetRenderer(
			String className, long classPK)
		throws Exception {

		if (className.equals(MBThread.class.getName())) {
			className = MBMessage.class.getName();

			MBThread mbThread = MBThreadLocalServiceUtil.getThread(classPK);

			classPK = mbThread.getRootMessageId();
		}

		AssetRendererFactory assetRendererFactory =
			AssetRendererFactoryRegistryUtil.
				getAssetRendererFactoryByClassName(className);

		return assetRendererFactory.getAssetRenderer(classPK);
	}

}