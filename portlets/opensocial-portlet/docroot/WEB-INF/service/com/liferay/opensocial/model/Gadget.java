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

package com.liferay.opensocial.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Gadget service. Represents a row in the &quot;OpenSocial_Gadget&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see GadgetModel
 * @see com.liferay.opensocial.model.impl.GadgetImpl
 * @see com.liferay.opensocial.model.impl.GadgetModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.opensocial.model.impl.GadgetImpl")
@ProviderType
public interface Gadget extends GadgetModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.opensocial.model.impl.GadgetImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Gadget, Long> GADGET_ID_ACCESSOR = new Accessor<Gadget, Long>() {
			@Override
			public Long get(Gadget gadget) {
				return gadget.getGadgetId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Gadget> getTypeClass() {
				return Gadget.class;
			}
		};
}