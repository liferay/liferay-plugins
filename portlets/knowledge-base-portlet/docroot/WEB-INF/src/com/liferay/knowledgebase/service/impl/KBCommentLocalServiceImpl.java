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

package com.liferay.knowledgebase.service.impl;

import com.liferay.knowledgebase.KBCommentContentException;
import com.liferay.knowledgebase.admin.social.AdminActivityKeys;
import com.liferay.knowledgebase.model.KBComment;
import com.liferay.knowledgebase.service.base.KBCommentLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.model.AssetEntry;

import java.util.Date;
import java.util.List;

/**
 * @author Peter Shin
 */
public class KBCommentLocalServiceImpl extends KBCommentLocalServiceBaseImpl {

	public KBComment addKBComment(
			long userId, long classNameId, long classPK, String content,
			boolean helpful, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// KB comment

		User user = userPersistence.findByPrimaryKey(userId);
		long groupId = serviceContext.getScopeGroupId();
		Date now = new Date();

		validate(content);

		long kbCommentId = counterLocalService.increment();

		KBComment kbComment = kbCommentPersistence.create(kbCommentId);

		kbComment.setUuid(serviceContext.getUuid());
		kbComment.setGroupId(groupId);
		kbComment.setCompanyId(user.getCompanyId());
		kbComment.setUserId(user.getUserId());
		kbComment.setUserName(user.getFullName());
		kbComment.setCreateDate(serviceContext.getCreateDate(now));
		kbComment.setModifiedDate(serviceContext.getModifiedDate(now));
		kbComment.setClassNameId(classNameId);
		kbComment.setClassPK(classPK);
		kbComment.setContent(content);
		kbComment.setHelpful(helpful);

		kbCommentPersistence.update(kbComment, false);

		// Social

		socialActivityLocalService.addActivity(
			userId, kbComment.getGroupId(), KBComment.class.getName(),
			kbCommentId, AdminActivityKeys.ADD_KB_COMMENT, StringPool.BLANK, 0);

		return kbComment;
	}

	@Override
	public void deleteKBComment (KBComment kbComment)
		throws PortalException, SystemException {

		// KB comment

		kbCommentPersistence.remove(kbComment);

		// Social

		AssetEntry assetEntry = assetEntryLocalService.getEntry(
			KBComment.class.getName(), kbComment.getKbCommentId());

		socialActivityLocalService.deleteActivities(assetEntry);
	}

	@Override
	public void deleteKBComment (long kbCommentId)
		throws PortalException, SystemException {

		KBComment kbComment = kbCommentPersistence.findByPrimaryKey(
			kbCommentId);

		deleteKBComment(kbComment);
	}

	public void deleteKBComments (String className, long classPK)
		throws PortalException, SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		List<KBComment> kbComments = kbCommentPersistence.findByC_C(
			classNameId, classPK);

		for (KBComment kbComment : kbComments) {
			deleteKBComment(kbComment);
		}
	}

	public KBComment getKBComment(long userId, String className, long classPK)
		throws PortalException, SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return kbCommentPersistence.findByU_C_C(userId, classNameId, classPK);
	}

	public List<KBComment> getKBComments(
			String className, long classPK, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return kbCommentPersistence.findByC_C(
			classNameId, classPK, start, end, orderByComparator);
	}

	public int getKBCommentsCount(String className, long classPK)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return kbCommentPersistence.countByC_C(classNameId, classPK);
	}

	public KBComment updateKBComment(
			long kbCommentId, long classNameId, long classPK, String content,
			boolean helpful, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// KB comment

		validate(content);

		KBComment kbComment = kbCommentPersistence.findByPrimaryKey(
			kbCommentId);

		kbComment.setModifiedDate(serviceContext.getModifiedDate(null));
		kbComment.setClassNameId(classNameId);
		kbComment.setClassPK(classPK);
		kbComment.setContent(content);
		kbComment.setHelpful(helpful);

		kbCommentPersistence.update(kbComment, false);

		// Social

		socialActivityLocalService.addActivity(
			kbComment.getUserId(), kbComment.getGroupId(),
			KBComment.class.getName(), kbCommentId,
			AdminActivityKeys.UPDATE_KB_COMMENT, StringPool.BLANK, 0);

		return kbComment;
	}

	protected void validate(String content) throws PortalException {
		if (Validator.isNull(content)) {
			throw new KBCommentContentException();
		}
	}

}