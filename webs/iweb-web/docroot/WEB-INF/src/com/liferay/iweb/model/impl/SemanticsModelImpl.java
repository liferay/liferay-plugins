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

package com.liferay.iweb.model.impl;

import com.liferay.iweb.model.Semantics;
import com.liferay.iweb.model.SemanticsSoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="SemanticsModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SemanticsModelImpl extends BaseModelImpl {
	public static final String TABLE_NAME = "IWEB_Semantics";
	public static final Object[][] TABLE_COLUMNS = {
			{ "semanticsName", new Integer(Types.VARCHAR) },
			

			{ "semanticsURI", new Integer(Types.VARCHAR) },
			

			{ "createdInCommunity", new Integer(Types.BIGINT) }
		};
	public static final String TABLE_SQL_CREATE = "create table IWEB_Semantics (semanticsName VARCHAR(75) null,semanticsURI VARCHAR(75) not null primary key,createdInCommunity LONG)";
	public static final String TABLE_SQL_DROP = "drop table IWEB_Semantics";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.iweb.model.Semantics"),
			true);

	public static Semantics toModel(SemanticsSoap soapModel) {
		Semantics model = new SemanticsImpl();

		model.setSemanticsName(soapModel.getSemanticsName());
		model.setSemanticsURI(soapModel.getSemanticsURI());
		model.setCreatedInCommunity(soapModel.getCreatedInCommunity());

		return model;
	}

	public static List<Semantics> toModels(SemanticsSoap[] soapModels) {
		List<Semantics> models = new ArrayList<Semantics>(soapModels.length);

		for (SemanticsSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final boolean CACHE_ENABLED_COMMUNITIES_SEMANTICS = com.liferay.iweb.model.impl.CommunityModelImpl.CACHE_ENABLED_COMMUNITIES_SEMANTICS;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.iweb.model.Semantics"));

	public SemanticsModelImpl() {
	}

	public String getPrimaryKey() {
		return _semanticsURI;
	}

	public void setPrimaryKey(String pk) {
		setSemanticsURI(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return _semanticsURI;
	}

	public String getSemanticsName() {
		return GetterUtil.getString(_semanticsName);
	}

	public void setSemanticsName(String semanticsName) {
		if (((semanticsName == null) && (_semanticsName != null)) ||
				((semanticsName != null) && (_semanticsName == null)) ||
				((semanticsName != null) && (_semanticsName != null) &&
				!semanticsName.equals(_semanticsName))) {
			_semanticsName = semanticsName;
		}
	}

	public String getSemanticsURI() {
		return GetterUtil.getString(_semanticsURI);
	}

	public void setSemanticsURI(String semanticsURI) {
		if (((semanticsURI == null) && (_semanticsURI != null)) ||
				((semanticsURI != null) && (_semanticsURI == null)) ||
				((semanticsURI != null) && (_semanticsURI != null) &&
				!semanticsURI.equals(_semanticsURI))) {
			_semanticsURI = semanticsURI;
		}
	}

	public long getCreatedInCommunity() {
		return _createdInCommunity;
	}

	public void setCreatedInCommunity(long createdInCommunity) {
		if (createdInCommunity != _createdInCommunity) {
			_createdInCommunity = createdInCommunity;
		}
	}

	public Semantics toEscapedModel() {
		if (isEscapedModel()) {
			return (Semantics)this;
		}
		else {
			Semantics model = new SemanticsImpl();

			model.setEscapedModel(true);

			model.setSemanticsName(HtmlUtil.escape(getSemanticsName()));
			model.setSemanticsURI(HtmlUtil.escape(getSemanticsURI()));
			model.setCreatedInCommunity(getCreatedInCommunity());

			model = (Semantics)Proxy.newProxyInstance(Semantics.class.getClassLoader(),
					new Class[] { Semantics.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public Object clone() {
		SemanticsImpl clone = new SemanticsImpl();

		clone.setSemanticsName(getSemanticsName());
		clone.setSemanticsURI(getSemanticsURI());
		clone.setCreatedInCommunity(getCreatedInCommunity());

		return clone;
	}

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		SemanticsImpl semantics = (SemanticsImpl)obj;

		String pk = semantics.getPrimaryKey();

		return getPrimaryKey().compareTo(pk);
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		SemanticsImpl semantics = null;

		try {
			semantics = (SemanticsImpl)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		String pk = semantics.getPrimaryKey();

		if (getPrimaryKey().equals(pk)) {
			return true;
		}
		else {
			return false;
		}
	}

	public int hashCode() {
		return getPrimaryKey().hashCode();
	}

	private String _semanticsName;
	private String _semanticsURI;
	private long _createdInCommunity;
}