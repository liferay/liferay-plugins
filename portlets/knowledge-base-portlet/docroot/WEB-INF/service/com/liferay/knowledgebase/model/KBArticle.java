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

package com.liferay.knowledgebase.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.Accessor;
import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the KBArticle service. Represents a row in the &quot;KBArticle&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see KBArticleModel
 * @see com.liferay.knowledgebase.model.impl.KBArticleImpl
 * @see com.liferay.knowledgebase.model.impl.KBArticleModelImpl
 * @generated
 */
@ProviderType
public interface KBArticle extends KBArticleModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.knowledgebase.model.impl.KBArticleImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<KBArticle, Long> KB_ARTICLE_ID_ACCESSOR = new Accessor<KBArticle, Long>() {
			@Override
			public Long get(KBArticle kbArticle) {
				return kbArticle.getKbArticleId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<KBArticle> getTypeClass() {
				return KBArticle.class;
			}
		};

	public java.util.List<java.lang.Long> getAncestorResourcePrimaryKeys()
		throws com.liferay.portal.kernel.exception.PortalException;

	public java.util.List<com.liferay.portal.kernel.repository.model.FileEntry> getAttachmentsFileEntries()
		throws com.liferay.portal.kernel.exception.PortalException;

	public long getAttachmentsFolderId()
		throws com.liferay.portal.kernel.exception.PortalException;

	public long getClassNameId();

	public long getClassPK();

	public com.liferay.knowledgebase.model.KBArticle getParentKBArticle()
		throws com.liferay.portal.kernel.exception.PortalException;

	public java.lang.String getParentTitle(java.util.Locale locale, int status)
		throws com.liferay.portal.kernel.exception.PortalException;

	public boolean isFirstVersion();

	public boolean isRoot();
}