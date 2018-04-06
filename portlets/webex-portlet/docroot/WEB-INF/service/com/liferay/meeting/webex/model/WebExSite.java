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

package com.liferay.meeting.webex.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the WebExSite service. Represents a row in the &quot;WebEx_WebExSite&quot; database table, with each column mapped to a property of this class.
 *
 * @author Anant Singh
 * @see WebExSiteModel
 * @see com.liferay.meeting.webex.model.impl.WebExSiteImpl
 * @see com.liferay.meeting.webex.model.impl.WebExSiteModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.meeting.webex.model.impl.WebExSiteImpl")
@ProviderType
public interface WebExSite extends WebExSiteModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.meeting.webex.model.impl.WebExSiteImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<WebExSite, Long> WEB_EX_SITE_ID_ACCESSOR = new Accessor<WebExSite, Long>() {
			@Override
			public Long get(WebExSite webExSite) {
				return webExSite.getWebExSiteId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<WebExSite> getTypeClass() {
				return WebExSite.class;
			}
		};

	public java.util.List<com.liferay.meeting.webex.model.WebExAccount> getWebExAccounts();
}