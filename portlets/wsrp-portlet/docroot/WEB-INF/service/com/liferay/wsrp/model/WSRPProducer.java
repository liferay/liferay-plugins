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
 * The extended model interface for the WSRPProducer service. Represents a row in the &quot;WSRP_WSRPProducer&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see WSRPProducerModel
 * @see com.liferay.wsrp.model.impl.WSRPProducerImpl
 * @see com.liferay.wsrp.model.impl.WSRPProducerModelImpl
 * @generated
 */
@ProviderType
public interface WSRPProducer extends WSRPProducerModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.wsrp.model.impl.WSRPProducerImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<WSRPProducer, Long> WSRP_PRODUCER_ID_ACCESSOR = new Accessor<WSRPProducer, Long>() {
			@Override
			public Long get(WSRPProducer wsrpProducer) {
				return wsrpProducer.getWsrpProducerId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<WSRPProducer> getTypeClass() {
				return WSRPProducer.class;
			}
		};

	public java.lang.String getURL(java.lang.String portalURL);
}