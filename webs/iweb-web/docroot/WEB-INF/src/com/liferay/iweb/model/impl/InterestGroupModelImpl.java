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

package com.liferay.iweb.model.impl;

import com.liferay.iweb.model.InterestGroup;
import com.liferay.iweb.model.InterestGroupSoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="InterestGroupModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class InterestGroupModelImpl extends BaseModelImpl {
	public static final String TABLE_NAME = "IWEB_InterestGroup";
	public static final Object[][] TABLE_COLUMNS = {
			{ "cid", new Integer(Types.BIGINT) },
			

			{ "type_", new Integer(Types.INTEGER) }
		};
	public static final String TABLE_SQL_CREATE = "create table IWEB_InterestGroup (cid LONG not null primary key,type_ INTEGER)";
	public static final String TABLE_SQL_DROP = "drop table IWEB_InterestGroup";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.iweb.model.InterestGroup"),
			true);

	public static InterestGroup toModel(InterestGroupSoap soapModel) {
		InterestGroup model = new InterestGroupImpl();

		model.setCid(soapModel.getCid());
		model.setType(soapModel.getType());

		return model;
	}

	public static List<InterestGroup> toModels(InterestGroupSoap[] soapModels) {
		List<InterestGroup> models = new ArrayList<InterestGroup>(soapModels.length);

		for (InterestGroupSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final boolean CACHE_ENABLED_INTERESTGROUPS_SEMANTICSFILES = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.InterestGroups_SemanticsFiles"),
			true);
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.iweb.model.InterestGroup"));

	public InterestGroupModelImpl() {
	}

	public long getPrimaryKey() {
		return _cid;
	}

	public void setPrimaryKey(long pk) {
		setCid(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_cid);
	}

	public long getCid() {
		return _cid;
	}

	public void setCid(long cid) {
		if (cid != _cid) {
			_cid = cid;
		}
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		if (type != _type) {
			_type = type;
		}
	}

	public InterestGroup toEscapedModel() {
		if (isEscapedModel()) {
			return (InterestGroup)this;
		}
		else {
			InterestGroup model = new InterestGroupImpl();

			model.setEscapedModel(true);

			model.setCid(getCid());
			model.setType(getType());

			model = (InterestGroup)Proxy.newProxyInstance(InterestGroup.class.getClassLoader(),
					new Class[] { InterestGroup.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public Object clone() {
		InterestGroupImpl clone = new InterestGroupImpl();

		clone.setCid(getCid());
		clone.setType(getType());

		return clone;
	}

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		InterestGroupImpl interestGroup = (InterestGroupImpl)obj;

		long pk = interestGroup.getPrimaryKey();

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

		InterestGroupImpl interestGroup = null;

		try {
			interestGroup = (InterestGroupImpl)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = interestGroup.getPrimaryKey();

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

	private long _cid;
	private int _type;
}