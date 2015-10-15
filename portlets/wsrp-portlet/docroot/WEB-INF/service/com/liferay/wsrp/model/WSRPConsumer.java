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

package com.liferay.wsrp.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.Accessor;
import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the WSRPConsumer service. Represents a row in the &quot;WSRP_WSRPConsumer&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see WSRPConsumerModel
 * @see com.liferay.wsrp.model.impl.WSRPConsumerImpl
 * @see com.liferay.wsrp.model.impl.WSRPConsumerModelImpl
 * @generated
 */
@ProviderType
public interface WSRPConsumer extends WSRPConsumerModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.wsrp.model.impl.WSRPConsumerImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<WSRPConsumer, Long> WSRP_CONSUMER_ID_ACCESSOR = new Accessor<WSRPConsumer, Long>() {
			@Override
			public Long get(WSRPConsumer wsrpConsumer) {
				return wsrpConsumer.getWsrpConsumerId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<WSRPConsumer> getTypeClass() {
				return WSRPConsumer.class;
			}
		};

	public oasis.names.tc.wsrp.v2.types.RegistrationContext getRegistrationContext();

	public com.liferay.portal.kernel.util.UnicodeProperties getRegistrationProperties();

	public void setRegistrationContext(
		oasis.names.tc.wsrp.v2.types.RegistrationContext registrationContext);

	public void setRegistrationProperties(
		com.liferay.portal.kernel.util.UnicodeProperties registrationProperties);
}