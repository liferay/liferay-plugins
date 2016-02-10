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

package com.liferay.sync.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the SyncDevice service. Represents a row in the &quot;SyncDevice&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see SyncDeviceModel
 * @see com.liferay.sync.model.impl.SyncDeviceImpl
 * @see com.liferay.sync.model.impl.SyncDeviceModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.sync.model.impl.SyncDeviceImpl")
@ProviderType
public interface SyncDevice extends SyncDeviceModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.sync.model.impl.SyncDeviceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<SyncDevice, Long> SYNC_DEVICE_ID_ACCESSOR = new Accessor<SyncDevice, Long>() {
			@Override
			public Long get(SyncDevice syncDevice) {
				return syncDevice.getSyncDeviceId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<SyncDevice> getTypeClass() {
				return SyncDevice.class;
			}
		};

	public void checkStatus()
		throws com.liferay.portal.kernel.exception.PortalException;

	public boolean hasSetModifiedDate();

	public boolean isSupported();

	public boolean supports(int featureSet);
}