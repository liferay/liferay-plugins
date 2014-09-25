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

package com.liferay.share.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class EntitySocialFeedPK implements Comparable<EntitySocialFeedPK>,
	Serializable {
	public long classNameId;
	public long classPK;
	public long feedClassNameId;
	public long feedClassPK;

	public EntitySocialFeedPK() {
	}

	public EntitySocialFeedPK(long classNameId, long classPK,
		long feedClassNameId, long feedClassPK) {
		this.classNameId = classNameId;
		this.classPK = classPK;
		this.feedClassNameId = feedClassNameId;
		this.feedClassPK = feedClassPK;
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

	public long getFeedClassNameId() {
		return feedClassNameId;
	}

	public void setFeedClassNameId(long feedClassNameId) {
		this.feedClassNameId = feedClassNameId;
	}

	public long getFeedClassPK() {
		return feedClassPK;
	}

	public void setFeedClassPK(long feedClassPK) {
		this.feedClassPK = feedClassPK;
	}

	@Override
	public int compareTo(EntitySocialFeedPK pk) {
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

		if (feedClassNameId < pk.feedClassNameId) {
			value = -1;
		}
		else if (feedClassNameId > pk.feedClassNameId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (feedClassPK < pk.feedClassPK) {
			value = -1;
		}
		else if (feedClassPK > pk.feedClassPK) {
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

		if (!(obj instanceof EntitySocialFeedPK)) {
			return false;
		}

		EntitySocialFeedPK pk = (EntitySocialFeedPK)obj;

		if ((classNameId == pk.classNameId) && (classPK == pk.classPK) &&
				(feedClassNameId == pk.feedClassNameId) &&
				(feedClassPK == pk.feedClassPK)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (String.valueOf(classNameId) + String.valueOf(classPK) +
		String.valueOf(feedClassNameId) + String.valueOf(feedClassPK)).hashCode();
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
		sb.append("feedClassNameId");
		sb.append(StringPool.EQUAL);
		sb.append(feedClassNameId);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("feedClassPK");
		sb.append(StringPool.EQUAL);
		sb.append(feedClassPK);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}