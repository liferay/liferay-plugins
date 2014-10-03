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

package com.liferay.sharing.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class SharingEntryPK implements Comparable<SharingEntryPK>, Serializable {
	public long classNameId;
	public long classPK;
	public long sharingClassNameId;
	public long sharingClassPK;

	public SharingEntryPK() {
	}

	public SharingEntryPK(long classNameId, long classPK,
		long sharingClassNameId, long sharingClassPK) {
		this.classNameId = classNameId;
		this.classPK = classPK;
		this.sharingClassNameId = sharingClassNameId;
		this.sharingClassPK = sharingClassPK;
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

	public long getSharingClassNameId() {
		return sharingClassNameId;
	}

	public void setSharingClassNameId(long sharingClassNameId) {
		this.sharingClassNameId = sharingClassNameId;
	}

	public long getSharingClassPK() {
		return sharingClassPK;
	}

	public void setSharingClassPK(long sharingClassPK) {
		this.sharingClassPK = sharingClassPK;
	}

	@Override
	public int compareTo(SharingEntryPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

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

		if (sharingClassNameId < pk.sharingClassNameId) {
			value = -1;
		}
		else if (sharingClassNameId > pk.sharingClassNameId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (sharingClassPK < pk.sharingClassPK) {
			value = -1;
		}
		else if (sharingClassPK > pk.sharingClassPK) {
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

		if (!(obj instanceof SharingEntryPK)) {
			return false;
		}

		SharingEntryPK pk = (SharingEntryPK)obj;

		if ((classNameId == pk.classNameId) && (classPK == pk.classPK) &&
				(sharingClassNameId == pk.sharingClassNameId) &&
				(sharingClassPK == pk.sharingClassPK)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (String.valueOf(classNameId) + String.valueOf(classPK) +
		String.valueOf(sharingClassNameId) + String.valueOf(sharingClassPK)).hashCode();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(20);

		sb.append(StringPool.OPEN_CURLY_BRACE);

		sb.append("classNameId");
		sb.append(StringPool.EQUAL);
		sb.append(classNameId);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("classPK");
		sb.append(StringPool.EQUAL);
		sb.append(classPK);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("sharingClassNameId");
		sb.append(StringPool.EQUAL);
		sb.append(sharingClassNameId);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("sharingClassPK");
		sb.append(StringPool.EQUAL);
		sb.append(sharingClassPK);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}