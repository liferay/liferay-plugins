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

package com.liferay.knowledgebase.model;

/**
 * The model interface for the Article service. Represents a row in the &quot;KB_Article&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Never modify this interface directly. Add methods to {@link com.liferay.knowledgebase.model.impl.ArticleImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
 * </p>
 *
 * <p>
 * Never reference this interface directly. All methods that expect a article model instance should use the {@link Article} interface instead.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ArticleModel
 * @see com.liferay.knowledgebase.model.impl.ArticleImpl
 * @see com.liferay.knowledgebase.model.impl.ArticleModelImpl
 * @generated
 */
public interface Article extends ArticleModel {
	public java.lang.String getAttachmentsDirName();

	public java.lang.String[] getAttachmentsFileNames()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;
}