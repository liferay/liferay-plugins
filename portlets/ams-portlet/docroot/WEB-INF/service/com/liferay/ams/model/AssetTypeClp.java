/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.ams.model;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

/**
 * <a href="AssetTypeClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class AssetTypeClp extends BaseModelImpl implements AssetType {
	public AssetTypeClp() {
	}

	public long getPrimaryKey() {
		return _assetTypeId;
	}

	public void setPrimaryKey(long pk) {
		setAssetTypeId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_assetTypeId);
	}

	public long getAssetTypeId() {
		return _assetTypeId;
	}

	public void setAssetTypeId(long assetTypeId) {
		_assetTypeId = assetTypeId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public AssetType toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			AssetType model = new AssetTypeClp();

			model.setEscapedModel(true);

			model.setAssetTypeId(getAssetTypeId());
			model.setGroupId(getGroupId());
			model.setName(HtmlUtil.escape(getName()));

			model = (AssetType)Proxy.newProxyInstance(AssetType.class.getClassLoader(),
					new Class[] { AssetType.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public Object clone() {
		AssetTypeClp clone = new AssetTypeClp();

		clone.setAssetTypeId(getAssetTypeId());
		clone.setGroupId(getGroupId());
		clone.setName(getName());

		return clone;
	}

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		AssetTypeClp assetType = (AssetTypeClp)obj;

		int value = 0;

		value = getName().compareTo(assetType.getName());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		AssetTypeClp assetType = null;

		try {
			assetType = (AssetTypeClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = assetType.getPrimaryKey();

		if (getPrimaryKey() == pk) {
			return true;
		}
		else {
			return false;
		}
	}

	public int hashCode() {
		return (int)getPrimaryKey();
	}

	private long _assetTypeId;
	private long _groupId;
	private String _name;
}