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

package com.liferay.ams.model.impl;

import com.liferay.ams.model.AssetType;
import com.liferay.ams.model.AssetTypeSoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.model.impl.ExpandoBridgeImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="AssetTypeModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class AssetTypeModelImpl extends BaseModelImpl {
	public static final String TABLE_NAME = "AMS_AssetType";
	public static final Object[][] TABLE_COLUMNS = {
			{ "assetTypeId", new Integer(Types.BIGINT) },
			

			{ "groupId", new Integer(Types.BIGINT) },
			

			{ "name", new Integer(Types.VARCHAR) }
		};
	public static final String TABLE_SQL_CREATE = "create table AMS_AssetType (assetTypeId LONG not null primary key,groupId LONG,name VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table AMS_AssetType";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.ams.model.AssetType"),
			true);

	public static AssetType toModel(AssetTypeSoap soapModel) {
		AssetType model = new AssetTypeImpl();

		model.setAssetTypeId(soapModel.getAssetTypeId());
		model.setGroupId(soapModel.getGroupId());
		model.setName(soapModel.getName());

		return model;
	}

	public static List<AssetType> toModels(AssetTypeSoap[] soapModels) {
		List<AssetType> models = new ArrayList<AssetType>(soapModels.length);

		for (AssetTypeSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.ams.model.AssetType"));

	public AssetTypeModelImpl() {
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
		if (assetTypeId != _assetTypeId) {
			_assetTypeId = assetTypeId;
		}
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		if (groupId != _groupId) {
			_groupId = groupId;
		}
	}

	public String getName() {
		return GetterUtil.getString(_name);
	}

	public void setName(String name) {
		if (((name == null) && (_name != null)) ||
				((name != null) && (_name == null)) ||
				((name != null) && (_name != null) && !name.equals(_name))) {
			_name = name;
		}
	}

	public AssetType toEscapedModel() {
		if (isEscapedModel()) {
			return (AssetType)this;
		}
		else {
			AssetType model = new AssetTypeImpl();

			model.setNew(isNew());
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

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = new ExpandoBridgeImpl(AssetType.class.getName(),
					getPrimaryKey());
		}

		return _expandoBridge;
	}

	public Object clone() {
		AssetTypeImpl clone = new AssetTypeImpl();

		clone.setAssetTypeId(getAssetTypeId());
		clone.setGroupId(getGroupId());
		clone.setName(getName());

		return clone;
	}

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		AssetTypeImpl assetType = (AssetTypeImpl)obj;

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

		AssetTypeImpl assetType = null;

		try {
			assetType = (AssetTypeImpl)obj;
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
	private transient ExpandoBridge _expandoBridge;
}