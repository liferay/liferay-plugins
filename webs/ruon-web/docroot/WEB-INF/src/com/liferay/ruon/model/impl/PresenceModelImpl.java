/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.model.impl.BaseModelImpl;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.model.impl.ExpandoBridgeImpl;

import com.liferay.ruon.model.Presence;
import com.liferay.ruon.model.PresenceSoap;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="PresenceModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class PresenceModelImpl extends BaseModelImpl {
	public static final String TABLE_NAME = "Ruon_Presence";
	public static final Object[][] TABLE_COLUMNS = {
			{ "presenceId", new Integer(Types.BIGINT) },
			

			{ "userId", new Integer(Types.BIGINT) },
			

			{ "modifiedDate", new Integer(Types.BIGINT) },
			

			{ "networkId", new Integer(Types.BIGINT) },
			

			{ "online_", new Integer(Types.BOOLEAN) }
		};
	public static final String TABLE_SQL_CREATE = "create table Ruon_Presence (presenceId LONG not null primary key,userId LONG,modifiedDate LONG,networkId LONG,online_ BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table Ruon_Presence";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.ruon.model.Presence"),
			true);

	public static Presence toModel(PresenceSoap soapModel) {
		Presence model = new PresenceImpl();

		model.setPresenceId(soapModel.getPresenceId());
		model.setUserId(soapModel.getUserId());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setNetworkId(soapModel.getNetworkId());
		model.setOnline(soapModel.getOnline());

		return model;
	}

	public static List<Presence> toModels(PresenceSoap[] soapModels) {
		List<Presence> models = new ArrayList<Presence>(soapModels.length);

		for (PresenceSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.ruon.model.Presence"));

	public PresenceModelImpl() {
	}

	public long getPrimaryKey() {
		return _presenceId;
	}

	public void setPrimaryKey(long pk) {
		setPresenceId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_presenceId);
	}

	public long getPresenceId() {
		return _presenceId;
	}

	public void setPresenceId(long presenceId) {
		if (presenceId != _presenceId) {
			_presenceId = presenceId;
		}
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		if (userId != _userId) {
			_userId = userId;
		}
	}

	public long getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(long modifiedDate) {
		if (modifiedDate != _modifiedDate) {
			_modifiedDate = modifiedDate;
		}
	}

	public long getNetworkId() {
		return _networkId;
	}

	public void setNetworkId(long networkId) {
		if (networkId != _networkId) {
			_networkId = networkId;
		}
	}

	public boolean getOnline() {
		return _online;
	}

	public boolean isOnline() {
		return _online;
	}

	public void setOnline(boolean online) {
		if (online != _online) {
			_online = online;
		}
	}

	public Presence toEscapedModel() {
		if (isEscapedModel()) {
			return (Presence)this;
		}
		else {
			Presence model = new PresenceImpl();

			model.setNew(isNew());
			model.setEscapedModel(true);

			model.setPresenceId(getPresenceId());
			model.setUserId(getUserId());
			model.setModifiedDate(getModifiedDate());
			model.setNetworkId(getNetworkId());
			model.setOnline(getOnline());

			model = (Presence)Proxy.newProxyInstance(Presence.class.getClassLoader(),
					new Class[] { Presence.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = new ExpandoBridgeImpl(Presence.class.getName(),
					getPrimaryKey());
		}

		return _expandoBridge;
	}

	public Object clone() {
		PresenceImpl clone = new PresenceImpl();

		clone.setPresenceId(getPresenceId());
		clone.setUserId(getUserId());
		clone.setModifiedDate(getModifiedDate());
		clone.setNetworkId(getNetworkId());
		clone.setOnline(getOnline());

		return clone;
	}

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		PresenceImpl presence = (PresenceImpl)obj;

		long pk = presence.getPrimaryKey();

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

		PresenceImpl presence = null;

		try {
			presence = (PresenceImpl)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = presence.getPrimaryKey();

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

	private long _presenceId;
	private long _userId;
	private long _modifiedDate;
	private long _networkId;
	private boolean _online;
	private ExpandoBridge _expandoBridge;
}