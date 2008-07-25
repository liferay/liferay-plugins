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

import com.liferay.ruon.model.PresenceStatus;
import com.liferay.ruon.model.PresenceStatusSoap;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="PresenceStatusModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class PresenceStatusModelImpl extends BaseModelImpl {
	public static final String TABLE_NAME = "Ruon_PresenceStatus";
	public static final Object[][] TABLE_COLUMNS = {
			{ "presenceStatusId", new Integer(Types.BIGINT) },
			

			{ "presenceStatusMessage", new Integer(Types.VARCHAR) }
		};
	public static final String TABLE_SQL_CREATE = "create table Ruon_PresenceStatus (presenceStatusId LONG not null primary key,presenceStatusMessage VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table Ruon_PresenceStatus";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.ruon.model.PresenceStatus"),
			true);

	public static PresenceStatus toModel(PresenceStatusSoap soapModel) {
		PresenceStatus model = new PresenceStatusImpl();

		model.setPresenceStatusId(soapModel.getPresenceStatusId());
		model.setPresenceStatusMessage(soapModel.getPresenceStatusMessage());

		return model;
	}

	public static List<PresenceStatus> toModels(PresenceStatusSoap[] soapModels) {
		List<PresenceStatus> models = new ArrayList<PresenceStatus>(soapModels.length);

		for (PresenceStatusSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.ruon.model.PresenceStatus"));

	public PresenceStatusModelImpl() {
	}

	public long getPrimaryKey() {
		return _presenceStatusId;
	}

	public void setPrimaryKey(long pk) {
		setPresenceStatusId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_presenceStatusId);
	}

	public long getPresenceStatusId() {
		return _presenceStatusId;
	}

	public void setPresenceStatusId(long presenceStatusId) {
		if (presenceStatusId != _presenceStatusId) {
			_presenceStatusId = presenceStatusId;
		}
	}

	public String getPresenceStatusMessage() {
		return GetterUtil.getString(_presenceStatusMessage);
	}

	public void setPresenceStatusMessage(String presenceStatusMessage) {
		if (((presenceStatusMessage == null) &&
				(_presenceStatusMessage != null)) ||
				((presenceStatusMessage != null) &&
				(_presenceStatusMessage == null)) ||
				((presenceStatusMessage != null) &&
				(_presenceStatusMessage != null) &&
				!presenceStatusMessage.equals(_presenceStatusMessage))) {
			_presenceStatusMessage = presenceStatusMessage;
		}
	}

	public PresenceStatus toEscapedModel() {
		if (isEscapedModel()) {
			return (PresenceStatus)this;
		}
		else {
			PresenceStatus model = new PresenceStatusImpl();

			model.setEscapedModel(true);

			model.setPresenceStatusId(getPresenceStatusId());
			model.setPresenceStatusMessage(HtmlUtil.escape(
					getPresenceStatusMessage()));

			model = (PresenceStatus)Proxy.newProxyInstance(PresenceStatus.class.getClassLoader(),
					new Class[] { PresenceStatus.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public Object clone() {
		PresenceStatusImpl clone = new PresenceStatusImpl();

		clone.setPresenceStatusId(getPresenceStatusId());
		clone.setPresenceStatusMessage(getPresenceStatusMessage());

		return clone;
	}

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		PresenceStatusImpl presenceStatus = (PresenceStatusImpl)obj;

		long pk = presenceStatus.getPrimaryKey();

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

		PresenceStatusImpl presenceStatus = null;

		try {
			presenceStatus = (PresenceStatusImpl)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = presenceStatus.getPrimaryKey();

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

	private long _presenceStatusId;
	private String _presenceStatusMessage;
}