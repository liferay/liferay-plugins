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

package com.liferay.ruon.model.impl;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.model.impl.ExpandoBridgeImpl;

import com.liferay.ruon.model.Network;
import com.liferay.ruon.model.NetworkSoap;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="NetworkModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class NetworkModelImpl extends BaseModelImpl {
	public static final String TABLE_NAME = "Ruon_Network";
	public static final Object[][] TABLE_COLUMNS = {
			{ "networkId", new Integer(Types.BIGINT) },
			

			{ "name", new Integer(Types.VARCHAR) },
			

			{ "ttl", new Integer(Types.BIGINT) }
		};
	public static final String TABLE_SQL_CREATE = "create table Ruon_Network (networkId LONG not null primary key,name VARCHAR(75) null,ttl LONG)";
	public static final String TABLE_SQL_DROP = "drop table Ruon_Network";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.ruon.model.Network"),
			true);

	public static Network toModel(NetworkSoap soapModel) {
		Network model = new NetworkImpl();

		model.setNetworkId(soapModel.getNetworkId());
		model.setName(soapModel.getName());
		model.setTtl(soapModel.getTtl());

		return model;
	}

	public static List<Network> toModels(NetworkSoap[] soapModels) {
		List<Network> models = new ArrayList<Network>(soapModels.length);

		for (NetworkSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.ruon.model.Network"));

	public NetworkModelImpl() {
	}

	public long getPrimaryKey() {
		return _networkId;
	}

	public void setPrimaryKey(long pk) {
		setNetworkId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_networkId);
	}

	public long getNetworkId() {
		return _networkId;
	}

	public void setNetworkId(long networkId) {
		if (networkId != _networkId) {
			_networkId = networkId;
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

	public long getTtl() {
		return _ttl;
	}

	public void setTtl(long ttl) {
		if (ttl != _ttl) {
			_ttl = ttl;
		}
	}

	public Network toEscapedModel() {
		if (isEscapedModel()) {
			return (Network)this;
		}
		else {
			Network model = new NetworkImpl();

			model.setNew(isNew());
			model.setEscapedModel(true);

			model.setNetworkId(getNetworkId());
			model.setName(HtmlUtil.escape(getName()));
			model.setTtl(getTtl());

			model = (Network)Proxy.newProxyInstance(Network.class.getClassLoader(),
					new Class[] { Network.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = new ExpandoBridgeImpl(Network.class.getName(),
					getPrimaryKey());
		}

		return _expandoBridge;
	}

	public Object clone() {
		NetworkImpl clone = new NetworkImpl();

		clone.setNetworkId(getNetworkId());
		clone.setName(getName());
		clone.setTtl(getTtl());

		return clone;
	}

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		NetworkImpl network = (NetworkImpl)obj;

		long pk = network.getPrimaryKey();

		if (getPrimaryKey() < pk) {
			return -1;
		}
		else if (getPrimaryKey() > pk) {
			return 1;
		}
		else {
			return 0;
		}
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		NetworkImpl network = null;

		try {
			network = (NetworkImpl)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = network.getPrimaryKey();

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

	private long _networkId;
	private String _name;
	private long _ttl;
	private ExpandoBridge _expandoBridge;
}