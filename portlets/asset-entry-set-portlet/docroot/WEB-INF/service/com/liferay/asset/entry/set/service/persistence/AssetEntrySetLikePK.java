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

package com.liferay.asset.entry.set.service.persistence;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AssetEntrySetLikePK implements Comparable<AssetEntrySetLikePK>,
	Serializable {
	public long assetEntrySetId;
	public long classNameId;
	public long classPK;

	public AssetEntrySetLikePK() {
	}

	public AssetEntrySetLikePK(long assetEntrySetId, long classNameId,
		long classPK) {
		this.assetEntrySetId = assetEntrySetId;
		this.classNameId = classNameId;
		this.classPK = classPK;
	}

	public long getAssetEntrySetId() {
		return assetEntrySetId;
	}

	public void setAssetEntrySetId(long assetEntrySetId) {
		this.assetEntrySetId = assetEntrySetId;
	}

	public long getClassNameId() {
		return classNameId;
	}

	public void setClassNameId(long classNameId) {
		this.classNameId = classNameId;
	}

	public long getClassPK() {
		return classPK;
	}

	public void setClassPK(long classPK) {
		this.classPK = classPK;
	}

	@Override
	public int compareTo(AssetEntrySetLikePK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (assetEntrySetId < pk.assetEntrySetId) {
			value = -1;
		}
		else if (assetEntrySetId > pk.assetEntrySetId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (classNameId < pk.classNameId) {
			value = -1;
		}
		else if (classNameId > pk.classNameId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (classPK < pk.classPK) {
			value = -1;
		}
		else if (classPK > pk.classPK) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetEntrySetLikePK)) {
			return false;
		}

		AssetEntrySetLikePK pk = (AssetEntrySetLikePK)obj;

		if ((assetEntrySetId == pk.assetEntrySetId) &&
				(classNameId == pk.classNameId) && (classPK == pk.classPK)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (String.valueOf(assetEntrySetId) + String.valueOf(classNameId) +
		String.valueOf(classPK)).hashCode();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append(StringPool.OPEN_CURLY_BRACE);

		sb.append("assetEntrySetId");
		sb.append(StringPool.EQUAL);
		sb.append(assetEntrySetId);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("classNameId");
		sb.append(StringPool.EQUAL);
		sb.append(classNameId);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("classPK");
		sb.append(StringPool.EQUAL);
		sb.append(classPK);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}