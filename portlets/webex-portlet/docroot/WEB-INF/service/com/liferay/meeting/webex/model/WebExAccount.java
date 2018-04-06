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
 * The extended model interface for the WebExAccount service. Represents a row in the &quot;WebEx_WebExAccount&quot; database table, with each column mapped to a property of this class.
 *
 * @author Anant Singh
 * @see WebExAccountModel
 * @see com.liferay.meeting.webex.model.impl.WebExAccountImpl
 * @see com.liferay.meeting.webex.model.impl.WebExAccountModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.meeting.webex.model.impl.WebExAccountImpl")
@ProviderType
public interface WebExAccount extends WebExAccountModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.meeting.webex.model.impl.WebExAccountImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<WebExAccount, Long> WEB_EX_ACCOUNT_ID_ACCESSOR = new Accessor<WebExAccount, Long>() {
			@Override
			public Long get(WebExAccount webExAccount) {
				return webExAccount.getWebExAccountId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<WebExAccount> getTypeClass() {
				return WebExAccount.class;
			}
		};

	public com.liferay.meeting.MeetingContext getMeetingContext()
		throws com.liferay.portal.kernel.exception.PortalException;

	public com.liferay.meeting.webex.model.WebExSite getWebExSite()
		throws com.liferay.portal.kernel.exception.PortalException;
}