/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

import com.liferay.knowledgebase.CommentContentException;
import com.liferay.knowledgebase.model.Comment;
import com.liferay.knowledgebase.service.base.CommentLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;

import java.util.Date;
import java.util.List;

/**
 * The implementation of the comment local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.knowledgebase.service.CommentLocalService} interface.
 * </p>
 *
 * <p>
 * Never reference this interface directly. Always use {@link com.liferay.knowledgebase.service.CommentLocalServiceUtil} to access the comment local service.
 * </p>
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.knowledgebase.service.base.CommentLocalServiceBaseImpl
 * @see com.liferay.knowledgebase.service.CommentLocalServiceUtil
 */
public class CommentLocalServiceImpl extends CommentLocalServiceBaseImpl {

	public Comment addComment(
			long userId, long classNameId, long classPK, String content,
			boolean helpful, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Comment

		User user = userPersistence.findByPrimaryKey(userId);
		long groupId = serviceContext.getScopeGroupId();
		Date now = new Date();

		validate(content);

		long commentId = counterLocalService.increment();

		Comment comment = commentPersistence.create(commentId);

		comment.setUuid(serviceContext.getUuid());
		comment.setGroupId(groupId);
		comment.setCompanyId(user.getCompanyId());
		comment.setUserId(user.getUserId());
		comment.setUserName(user.getFullName());
		comment.setCreateDate(serviceContext.getCreateDate(now));
		comment.setModifiedDate(serviceContext.getModifiedDate(now));
		comment.setClassNameId(classNameId);
		comment.setClassPK(classPK);
		comment.setContent(content);
		comment.setHelpful(helpful);

		commentPersistence.update(comment, false);

		return comment;
	}

	public void deleteComment (long commentId)
		throws PortalException, SystemException {

		// Comment

		commentPersistence.remove(commentId);
	}

	public Comment getComment(long userId, String className, long classPK)
		throws PortalException, SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return commentPersistence.findByU_C_C(userId, classNameId, classPK);
	}

	public List<Comment> getComments(
			String className, long classPK, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return commentPersistence.findByC_C(
			classNameId, classPK, start, end, orderByComparator);
	}

	public int getCommentsCount(String className, long classPK)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return commentPersistence.countByC_C(classNameId, classPK);
	}

	public Comment updateComment(
			long commentId, long classNameId, long classPK, String content,
			boolean helpful, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Comment

		validate(content);

		Comment comment = commentPersistence.findByPrimaryKey(commentId);

		comment.setModifiedDate(serviceContext.getModifiedDate(null));
		comment.setClassNameId(classNameId);
		comment.setClassPK(classPK);
		comment.setContent(content);
		comment.setHelpful(helpful);

		commentPersistence.update(comment, false);

		return comment;
	}

	protected void validate(String content) throws PortalException {
		if (Validator.isNull(content)) {
			throw new CommentContentException();
		}
	}

}