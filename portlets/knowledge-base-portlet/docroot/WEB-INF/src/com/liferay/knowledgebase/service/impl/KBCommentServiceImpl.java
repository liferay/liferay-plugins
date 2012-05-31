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

package com.liferay.knowledgebase.service.impl;

import com.liferay.knowledgebase.model.KBComment;
import com.liferay.knowledgebase.service.base.KBCommentServiceBaseImpl;
import com.liferay.knowledgebase.service.permission.KBCommentPermission;
import com.liferay.knowledgebase.util.ActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the k b comment remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.knowledgebase.service.KBCommentService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.knowledgebase.service.base.KBCommentServiceBaseImpl
 * @see com.liferay.knowledgebase.service.KBCommentServiceUtil
 */
public class KBCommentServiceImpl extends KBCommentServiceBaseImpl {

	public KBComment deleteKBComment(KBComment kbComment)
		throws SystemException, PortalException {

		KBCommentPermission.check(
			getPermissionChecker(), kbComment, ActionKeys.DELETE);

		return kbCommentLocalService.deleteKBComment(kbComment);
	}

	public KBComment deleteKBComment(long kbCommentId)
		throws PortalException, SystemException {

		KBComment kbComment = kbCommentPersistence.findByPrimaryKey(
			kbCommentId);

		return deleteKBComment(kbComment);
	}

}