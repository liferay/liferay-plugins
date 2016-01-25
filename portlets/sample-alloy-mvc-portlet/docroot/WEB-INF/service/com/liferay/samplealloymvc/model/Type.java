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

package com.liferay.samplealloymvc.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Type service. Represents a row in the &quot;SAMVC_Type&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see TypeModel
 * @see com.liferay.samplealloymvc.model.impl.TypeImpl
 * @see com.liferay.samplealloymvc.model.impl.TypeModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.samplealloymvc.model.impl.TypeImpl")
@ProviderType
public interface Type extends TypeModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.samplealloymvc.model.impl.TypeImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Type, Long> TYPE_ID_ACCESSOR = new Accessor<Type, Long>() {
			@Override
			public Long get(Type type) {
				return type.getTypeId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Type> getTypeClass() {
				return Type.class;
			}
		};
}