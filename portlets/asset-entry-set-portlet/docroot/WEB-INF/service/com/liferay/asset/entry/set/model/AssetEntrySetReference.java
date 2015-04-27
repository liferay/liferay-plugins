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

package com.liferay.asset.entry.set.model;

import java.io.Serializable;

/**
 * @author Matthew Kong
 */
public class AssetEntrySetReference implements Serializable {

	public AssetEntrySetReference(
		long sharedToClassNameId, long sharedToClassPK) {

		_sharedToClassNameId = sharedToClassNameId;
		_sharedToClassPK = sharedToClassPK;
	}

	public long getSharedToClassNameId() {
		return _sharedToClassNameId;
	}

	public long getSharedToClassPK() {
		return _sharedToClassPK;
	}

	public void setSharedToClassNameId(long sharedToClassNameId) {
		_sharedToClassNameId = sharedToClassNameId;
	}

	public void setSharedToClassPK(long sharedToClassPK) {
		_sharedToClassPK = sharedToClassPK;
	}

	private long _sharedToClassNameId;
	private long _sharedToClassPK;

}