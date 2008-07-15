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

 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.sun.com/cddl/cddl.html and
 * legal/CDDLv1.0.txt. See the License for the specific language governing
 * permission and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at legal/CDDLv1.0.txt.
 *
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Copyright 2008 Sun Microsystems Inc. All rights reserved.
 **/

package com.liferay.ruon.model.impl;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.liferay.ruon.model.PresenceStatuses;
import com.liferay.ruon.model.PresenceStatusesSoap;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="PresenceStatusesModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class PresenceStatusesModelImpl extends BaseModelImpl {
	public static final String TABLE_NAME = "Ruon_PresenceStatuses";
	public static final Object[][] TABLE_COLUMNS = {
			{ "presenceStatusId", new Integer(Types.BIGINT) },
			

			{ "presenceStatusMessage", new Integer(Types.VARCHAR) }
		};
	public static final String TABLE_SQL_CREATE = "create table Ruon_PresenceStatuses (presenceStatusId LONG not null primary key,presenceStatusMessage VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table Ruon_PresenceStatuses";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.ruon.model.PresenceStatuses"),
			true);

	public static PresenceStatuses toModel(PresenceStatusesSoap soapModel) {
		PresenceStatuses model = new PresenceStatusesImpl();

		model.setPresenceStatusId(soapModel.getPresenceStatusId());
		model.setPresenceStatusMessage(soapModel.getPresenceStatusMessage());

		return model;
	}

	public static List<PresenceStatuses> toModels(
		PresenceStatusesSoap[] soapModels) {
		List<PresenceStatuses> models = new ArrayList<PresenceStatuses>(soapModels.length);

		for (PresenceStatusesSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.ruon.model.PresenceStatuses"));

	public PresenceStatusesModelImpl() {
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

	public PresenceStatuses toEscapedModel() {
		if (isEscapedModel()) {
			return (PresenceStatuses)this;
		}
		else {
			PresenceStatuses model = new PresenceStatusesImpl();

			model.setEscapedModel(true);

			model.setPresenceStatusId(getPresenceStatusId());
			model.setPresenceStatusMessage(HtmlUtil.escape(
					getPresenceStatusMessage()));

			model = (PresenceStatuses)Proxy.newProxyInstance(PresenceStatuses.class.getClassLoader(),
					new Class[] { PresenceStatuses.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public Object clone() {
		PresenceStatusesImpl clone = new PresenceStatusesImpl();

		clone.setPresenceStatusId(getPresenceStatusId());
		clone.setPresenceStatusMessage(getPresenceStatusMessage());

		return clone;
	}

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		PresenceStatusesImpl presenceStatuses = (PresenceStatusesImpl)obj;

		long pk = presenceStatuses.getPrimaryKey();

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

		PresenceStatusesImpl presenceStatuses = null;

		try {
			presenceStatuses = (PresenceStatusesImpl)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = presenceStatuses.getPrimaryKey();

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