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

package com.liferay.compat.hook.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.ExportImportThreadLocal;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.persistence.UserUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.persistence.AssetEntryUtil;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.service.SocialActivityLocalService;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import com.liferay.portlet.social.service.SocialActivityLocalServiceWrapper;
import com.liferay.portlet.social.service.persistence.SocialActivityUtil;
import com.liferay.portlet.social.util.SocialActivityHierarchyEntry;
import com.liferay.portlet.social.util.SocialActivityHierarchyEntryThreadLocal;

import java.util.Date;

/**
 * @author Matthew Kong
 */
public class CompatSocialActivityLocalServiceImpl
	extends SocialActivityLocalServiceWrapper {

	public CompatSocialActivityLocalServiceImpl(
		SocialActivityLocalService socialActivityLocalService) {

		super(socialActivityLocalService);
	}

	@Override
	public void addActivity(
			long userId, long groupId, Date createDate, String className,
			long classPK, int type, String extraData, long receiverUserId)
		throws PortalException, SystemException {

		if (ExportImportThreadLocal.isImportInProcess()) {
			return;
		}

		User user = UserUtil.findByPrimaryKey(userId);
		long classNameId = PortalUtil.getClassNameId(className);

		if (groupId > 0) {
			Group group = GroupLocalServiceUtil.getGroup(groupId);

			if (group.isLayout()) {
				Layout layout = LayoutLocalServiceUtil.getLayout(
					group.getClassPK());

				groupId = layout.getGroupId();
			}
		}

		SocialActivity activity = SocialActivityUtil.create(0);

		activity.setGroupId(groupId);
		activity.setCompanyId(user.getCompanyId());
		activity.setUserId(user.getUserId());
		activity.setCreateDate(createDate.getTime());
		activity.setMirrorActivityId(0);
		activity.setClassNameId(classNameId);
		activity.setClassPK(classPK);

		SocialActivityHierarchyEntry activityHierarchyEntry =
			SocialActivityHierarchyEntryThreadLocal.peek();

		if (activityHierarchyEntry != null) {
			activity.setParentClassNameId(
				activityHierarchyEntry.getClassNameId());
			activity.setParentClassPK(activityHierarchyEntry.getClassPK());
		}

		activity.setType(type);
		activity.setExtraData(extraData);
		activity.setReceiverUserId(receiverUserId);

		AssetEntry assetEntry = AssetEntryUtil.fetchByC_C(classNameId, classPK);

		activity.setAssetEntry(assetEntry);

		SocialActivity mirrorActivity = null;

		if ((receiverUserId > 0) && (userId != receiverUserId)) {
			mirrorActivity = SocialActivityUtil.create(0);

			mirrorActivity.setGroupId(groupId);
			mirrorActivity.setCompanyId(user.getCompanyId());
			mirrorActivity.setUserId(receiverUserId);
			mirrorActivity.setCreateDate(createDate.getTime());
			mirrorActivity.setClassNameId(classNameId);
			mirrorActivity.setClassPK(classPK);

			if (activityHierarchyEntry != null) {
				mirrorActivity.setParentClassNameId(
					activityHierarchyEntry.getClassNameId());
				mirrorActivity.setParentClassPK(
					activityHierarchyEntry.getClassPK());
			}

			mirrorActivity.setType(type);
			mirrorActivity.setExtraData(extraData);
			mirrorActivity.setReceiverUserId(user.getUserId());
			mirrorActivity.setAssetEntry(assetEntry);
		}

		SocialActivityLocalServiceUtil.addActivity(activity, mirrorActivity);
	}

	@Override
	public void addActivity(
			long userId, long groupId, String className, long classPK, int type,
			String extraData, long receiverUserId)
		throws PortalException, SystemException {

		if (ExportImportThreadLocal.isImportInProcess()) {
			return;
		}

		Date createDate = new Date();

		long classNameId = PortalUtil.getClassNameId(className);

		while (true) {
			SocialActivity socialActivity =
				SocialActivityUtil.fetchByG_U_CD_C_C_T_R(
					groupId, userId, createDate.getTime(), classNameId, classPK,
					type, receiverUserId);

			if (socialActivity != null) {
				createDate = new Date(createDate.getTime() + 1);
			}
			else {
				break;
			}
		}

		addActivity(
			userId, groupId, createDate, className, classPK, type, extraData,
			receiverUserId);
	}

	@Override
	public void addUniqueActivity(
			long userId, long groupId, Date createDate, String className,
			long classPK, int type, String extraData, long receiverUserId)
		throws PortalException, SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		SocialActivity socialActivity =
			SocialActivityUtil.fetchByG_U_CD_C_C_T_R(
				groupId, userId, createDate.getTime(), classNameId, classPK,
				type, receiverUserId);

		if (socialActivity != null) {
			return;
		}

		addActivity(
			userId, groupId, createDate, className, classPK, type, extraData,
			receiverUserId);
	}

	@Override
	public void addUniqueActivity(
			long userId, long groupId, String className, long classPK, int type,
			String extraData, long receiverUserId)
		throws PortalException, SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		int count = SocialActivityUtil.countByG_U_C_C_T_R(
			groupId, userId, classNameId, classPK, type, receiverUserId);

		if (count > 0) {
			return;
		}

		addActivity(
			userId, groupId, new Date(), className, classPK, type, extraData,
			receiverUserId);
	}

}