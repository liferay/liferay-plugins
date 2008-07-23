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

import com.liferay.iweb.model.SemanticsElement;
import com.liferay.iweb.model.SemanticsElementSoap;

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
 * <a href="SemanticsElementModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SemanticsElementModelImpl extends BaseModelImpl {
	public static final String TABLE_NAME = "IWEB_SemanticsElement";
	public static final Object[][] TABLE_COLUMNS = {
			{ "elementURI", new Integer(Types.VARCHAR) },
			

			{ "semanticsURI", new Integer(Types.VARCHAR) }
		};
	public static final String TABLE_SQL_CREATE = "create table IWEB_SemanticsElement (elementURI VARCHAR(75) not null primary key,semanticsURI VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table IWEB_SemanticsElement";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.iweb.model.SemanticsElement"),
			true);

	public static SemanticsElement toModel(SemanticsElementSoap soapModel) {
		SemanticsElement model = new SemanticsElementImpl();

		model.setElementURI(soapModel.getElementURI());
		model.setSemanticsURI(soapModel.getSemanticsURI());

		return model;
	}

	public static List<SemanticsElement> toModels(
		SemanticsElementSoap[] soapModels) {
		List<SemanticsElement> models = new ArrayList<SemanticsElement>(soapModels.length);

		for (SemanticsElementSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final boolean CACHE_ENABLED_POSTENTRIES_SEMANTICSELEMENTS = com.liferay.iweb.model.impl.PostEntryModelImpl.CACHE_ENABLED_POSTENTRIES_SEMANTICSELEMENTS;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.iweb.model.SemanticsElement"));

	public SemanticsElementModelImpl() {
	}

	public String getPrimaryKey() {
		return _elementURI;
	}

	public void setPrimaryKey(String pk) {
		setElementURI(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return _elementURI;
	}

	public String getElementURI() {
		return GetterUtil.getString(_elementURI);
	}

	public void setElementURI(String elementURI) {
		if (((elementURI == null) && (_elementURI != null)) ||
				((elementURI != null) && (_elementURI == null)) ||
				((elementURI != null) && (_elementURI != null) &&
				!elementURI.equals(_elementURI))) {
			_elementURI = elementURI;
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

	public SemanticsElement toEscapedModel() {
		if (isEscapedModel()) {
			return (SemanticsElement)this;
		}
		else {
			SemanticsElement model = new SemanticsElementImpl();

			model.setEscapedModel(true);

			model.setElementURI(HtmlUtil.escape(getElementURI()));
			model.setSemanticsURI(HtmlUtil.escape(getSemanticsURI()));

			model = (SemanticsElement)Proxy.newProxyInstance(SemanticsElement.class.getClassLoader(),
					new Class[] { SemanticsElement.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public Object clone() {
		SemanticsElementImpl clone = new SemanticsElementImpl();

		clone.setElementURI(getElementURI());
		clone.setSemanticsURI(getSemanticsURI());

		return clone;
	}

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		SemanticsElementImpl semanticsElement = (SemanticsElementImpl)obj;

		String pk = semanticsElement.getPrimaryKey();

		return getPrimaryKey().compareTo(pk);
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		SemanticsElementImpl semanticsElement = null;

		try {
			semanticsElement = (SemanticsElementImpl)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		String pk = semanticsElement.getPrimaryKey();

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

	private String _elementURI;
	private String _semanticsURI;
}