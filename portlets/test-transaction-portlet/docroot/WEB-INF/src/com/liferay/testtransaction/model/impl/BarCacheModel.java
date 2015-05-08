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

package com.liferay.testtransaction.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.testtransaction.model.Bar;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Bar in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Bar
 * @generated
 */
@ProviderType
public class BarCacheModel implements CacheModel<Bar>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BarCacheModel)) {
			return false;
		}

		BarCacheModel barCacheModel = (BarCacheModel)obj;

		if (barId == barCacheModel.barId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, barId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{barId=");
		sb.append(barId);
		sb.append(", text=");
		sb.append(text);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Bar toEntityModel() {
		BarImpl barImpl = new BarImpl();

		barImpl.setBarId(barId);

		if (text == null) {
			barImpl.setText(StringPool.BLANK);
		}
		else {
			barImpl.setText(text);
		}

		barImpl.resetOriginalValues();

		return barImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		barId = objectInput.readLong();
		text = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(barId);

		if (text == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(text);
		}
	}

	public long barId;
	public String text;
}