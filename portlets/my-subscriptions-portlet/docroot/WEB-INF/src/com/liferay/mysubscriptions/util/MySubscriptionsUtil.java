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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.security.permission.ResourceActionsUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.AssetRendererFactoryRegistryUtil;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.AssetRendererFactory;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBThread;
import com.liferay.portlet.messageboards.service.MBCategoryLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBThreadLocalServiceUtil;

import java.util.Locale;

/**
 * @author Peter Shin
 */
public class MySubscriptionsUtil {

	public static AssetRenderer getAssetRenderer(
		String className, long classPK) {

		AssetRendererFactory assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
				className);

		try {
			return assetRendererFactory.getAssetRenderer(classPK);
		}
		catch (Exception e) {
		}

		return null;
	}

	public static String getClassNameIdText(long classNameId, Locale locale) {
		String className = PortalUtil.getClassName(classNameId);

		StringBundler sb = new StringBundler(5);

		sb.append(classNameId);
		sb.append(StringPool.SPACE);
		sb.append(StringPool.OPEN_PARENTHESIS);
		sb.append(ResourceActionsUtil.getModelResource(locale, className));
		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}

	public static String getTitleText(
			String className, long classPK, String title, Locale locale)
		throws PortalException, SystemException {

		if (Validator.isNull(title)) {
			title = getMBCategoryTitle(className, classPK);
		}

		if (Validator.isNull(title)) {
			title = getMBThreadTitle(className, classPK);
		}

		if (Validator.isNull(title)) {
			title = getGroupTitle(className, classPK, locale);
		}

		if (Validator.isNull(title)) {
			return String.valueOf(classPK);
		}

		StringBundler sb = new StringBundler(5);

		sb.append(classPK);
		sb.append(StringPool.SPACE);
		sb.append(StringPool.OPEN_PARENTHESIS);
		sb.append(title);
		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}

	protected static String getGroupTitle(
			String className, long classPK, Locale locale)
		throws PortalException, SystemException {

		Group group = null;

		try {
			group = GroupLocalServiceUtil.getGroup(classPK);
		}
		catch (Exception e) {
		}

		if (group == null) {
			return null;
		}

		return group.getDescriptiveName();
	}

	protected static String getMBCategoryTitle(String className, long classPK) {
		if (!className.equals(MBCategory.class.getName())) {
			return null;
		}

		MBCategory mbCategory = null;

		try {
			mbCategory = MBCategoryLocalServiceUtil.getCategory(classPK);
		}
		catch (Exception e) {
		}

		if (mbCategory == null) {
			return null;
		}

		return mbCategory.getName();
	}

	protected static String getMBThreadTitle(String className, long classPK) {
		if (!className.equals(MBThread.class.getName())) {
			return null;
		}

		MBThread mbThread = null;

		try {
			mbThread = MBThreadLocalServiceUtil.getThread(classPK);
		}
		catch (Exception e) {
		}

		if (mbThread == null) {
			return null;
		}

		MBMessage mbMessage = null;

		try {
			mbMessage = MBMessageLocalServiceUtil.getMessage(
				mbThread.getRootMessageId());
		}
		catch (Exception e) {
		}

		if (mbMessage == null) {
			return null;
		}

		return mbMessage.getSubject();
	}

}