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

package com.liferay.mail.service.persistence;

import com.liferay.mail.model.Attachment;
import com.liferay.mail.service.AttachmentLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;

/**
 * @author Brian Wing Shun Chan
 * @deprecated As of 7.0.0, replaced by {@link AttachmentLocalServiceUtil#getActionableDynamicQuery()}
 * @generated
 */
@Deprecated
public abstract class AttachmentActionableDynamicQuery
	extends DefaultActionableDynamicQuery {
	public AttachmentActionableDynamicQuery() {
		setBaseLocalService(AttachmentLocalServiceUtil.getService());

		setClassLoader(com.liferay.mail.service.ClpSerializer.class.getClassLoader());

		setModelClass(Attachment.class);

		setPrimaryKeyPropertyName("attachmentId");
	}
}