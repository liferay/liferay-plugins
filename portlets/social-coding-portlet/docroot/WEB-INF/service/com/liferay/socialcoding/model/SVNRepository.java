/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.socialcoding.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the SVNRepository service. Represents a row in the &quot;SC_SVNRepository&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see SVNRepositoryModel
 * @see com.liferay.socialcoding.model.impl.SVNRepositoryImpl
 * @see com.liferay.socialcoding.model.impl.SVNRepositoryModelImpl
 * @generated
 */
public interface SVNRepository extends SVNRepositoryModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.socialcoding.model.impl.SVNRepositoryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public java.lang.String getName();

	public java.lang.String getShortURL();
}